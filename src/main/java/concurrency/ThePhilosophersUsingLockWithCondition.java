package concurrency;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static helpers.Strings.f;
import static java.lang.Thread.sleep;

/*
The code partially copied from "Seven Concurrency Models in Seven Weeks"
 */
public class ThePhilosophersUsingLockWithCondition {

    static class Philosopher implements Runnable {

        private final ReentrantLock table;
        private final Condition condition;
        private final int eatingCountLimit;
        private Philosopher left;
        private Philosopher right;
        private boolean eating;
        private int eatingCount;

        private String name;

        public Philosopher(String name, int eatingCountLimit, ReentrantLock table) {
            this.name = Objects.requireNonNull(name);
            this.table = Objects.requireNonNull(table);
            this.condition = table.newCondition();
            this.eatingCountLimit=eatingCountLimit;
        }

        public Philosopher setLeft(Philosopher left) {
            this.left = left;
            return this;
        }

        public Philosopher setRight(Philosopher right) {
            this.right = right;
            return this;
        }

        @Override
        public void run() {
            try {
                while (eatingCount < eatingCountLimit) {
                    think();
                    eat();
                }
            } catch (Exception e) {
                System.out.println(f("Exception in {}. {}",name,e));
            } finally {
                System.out.println(f("{} finished with eatingCount={} .",name, eatingCount));
                stoppedEating();
            }
        }
        private void stoppedEating(){
            table.lock();
            try {
                eating = false;
                left.condition.signal();
                right.condition.signal();
            } finally {
                table.unlock();
            }
        }

        public void think() throws InterruptedException {
            stoppedEating();
            longAct("think");
        }

        public void eat() throws InterruptedException {
            table.lock();
            try {
                while (left.eating || right.eating) {
                    condition.await();
                }
                eating = true;
                eatingCount++;
            } finally {
                table.unlock();
            }
            longAct("eat");
        }
        private void longAct(String action) throws InterruptedException {
            sleep(ThreadLocalRandom.current().nextInt(1, 50));
            System.out.println(f("{} {}s in {}", name,action, Thread.currentThread().getName()));
            sleep(ThreadLocalRandom.current().nextInt(1, 50));
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Test
    public void test(){
        ReentrantLock table = new ReentrantLock();
        Philosopher ph1 = new Philosopher("ph1",10, table);
        Philosopher ph2 = new Philosopher("ph2",10, table).setLeft(ph1);
        ph1.setRight(ph2);
        Philosopher ph3 = new Philosopher("ph3",10,table).setLeft(ph2);
        ph2.setRight(ph3);
        Philosopher ph4 = new Philosopher("ph4",10,table).setLeft(ph3);
        ph3.setRight(ph4);
        Philosopher ph5 = new Philosopher("ph5",10,table).setLeft(ph4).setRight(ph1);
        ph4.setRight(ph5);
        ph1.setLeft(ph5);

        AtomicInteger threadCounter = new AtomicInteger();
        ThreadFactory tf = r -> {
            Thread out = new Thread(r, f("thread {}", threadCounter.incrementAndGet()));
            out.setUncaughtExceptionHandler((thread, exception)-> System.out.println(f("In {} raised exception {}", thread.getName(), exception)));
            return out;
        };
        ExecutorService threadPool = Executors.newCachedThreadPool(tf);
        List<Future<?>> futures = new ArrayList<>();
        futures.add(threadPool.submit(ph1));
        futures.add(threadPool.submit(ph2));
        futures.add(threadPool.submit(ph3));
        futures.add(threadPool.submit(ph4));
        futures.add(threadPool.submit(ph5));

        futures.forEach(f-> {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
