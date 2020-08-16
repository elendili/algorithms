package multithread;

import helpers.Helpers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static helpers.Helpers.sleep;

public class ThreadsHustle {
    /* task ?
     fill shared int array from diff threads and
     in result there should be sorted array.
     Every thread has its own random value;
    *
    * */

    @Test
    public void fillArrayToSort() {
        final int threadCount = 10;
        final int[] a = new int[threadCount];
        Collection<Thread> threads = new ArrayList<>(threadCount);
        for (int i = threadCount - 1; i > -1; i--) {
            // define index about which thread will care
            final int fi = i;
            final int value = 1 + new Random().nextInt(100);
            System.out.print(value + " ");
            Runnable r = () -> {
                // define random value to put in array by given thread
                synchronized (a) {
                    int j;
                    for (j = 0; j < a.length; j++) {
                        if (a[j] == 0) {
                            if (j == 0 || a[j - 1] <= value) {
                                a[j] = value;
                            } else if (a[j - 1] > value) {
                                // insertion sort
                                int k = j - 1;
                                for (; k > -1 && a[k] >= value; k--) ;
                                k++;
                                System.arraycopy(a, k, a, k + 1, a.length - k - 1);
                                a[k] = value;
                            }

                            break;
                        }
                    }
                }
            };
            threads.add(new Thread(r));
        }
        System.out.println();
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(Arrays.stream(a).mapToObj(i -> i + "").collect(Collectors.joining(" ")));
        for (int i = 1; i < a.length; i++) {
            Assertions.assertTrue(a[i - 1] <= a[i]);
        }
    }

    @Test
    public void producerConsumerOnBasicSynchronization() {
        final LinkedList<Integer> list = new LinkedList<>();
        final int size = 10; // size of queue after which it will be blocked for adding
        final AtomicInteger data = new AtomicInteger(0); // counter for uniq data generating
        final int howMuchProduceConsume = 100;
        final int threadsCount = 10; // consumer/producer threads count

        Runnable supplier = () -> {
            for (int i = 0; i < howMuchProduceConsume; i++) {
                int s = data.incrementAndGet();
                synchronized (list) {
                    while (list.size() >= size) { // wait till place element is ready for consumption
                        Helpers.wait(list, 0);
                    }
                    list.add(s);
                    list.notifyAll(); // element is ready for consumption
                }
                System.out.println(Thread.currentThread().getName() + "->" + s);
                sleep(1);
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < howMuchProduceConsume; i++) {
                int s;
                synchronized (list) {
                    while (list.isEmpty()) {
                        Helpers.wait(list, 0); // wait till
                    }
                    s = list.poll();
                    list.notifyAll();  // list is (probably) empty, notify producer
                }
                System.out.println(Thread.currentThread().getName() + "<-" + s);
                sleep(1);
            }
        };

        List<Thread> threads = Stream.concat(
                IntStream.rangeClosed(1, threadsCount).mapToObj(i -> new Thread(supplier, "s " + i)),
                IntStream.rangeClosed(1, threadsCount).mapToObj(i -> new Thread(consumer, "c " + i))
        )
                .peek(Thread::start)
                .collect(Collectors.toList());

        threads.forEach(Helpers::join);
    }

    @Test
    public void producerConsumerOnBlockingQueue() {
        final LinkedBlockingQueue<Integer> q = new LinkedBlockingQueue<>(10);
        AtomicInteger data = new AtomicInteger(0);

        Runnable supplier = () -> {
            for (int i = 0; i < 10; i++) {
                int s = data.incrementAndGet();
                while (!q.offer(s)) ;
                System.out.println(Thread.currentThread().getName() + "->" + s);
                sleep(1);
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 10; i++) {
                Integer s;
                while ((s = q.poll()) == null) ;
                System.out.println(Thread.currentThread().getName() + "<-" + s);
                sleep(1);
            }
        };

        List<Thread> threads = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> i % 2 == 0 ? new Thread(supplier, "s " + i)
                        : new Thread(consumer, "c " + i))
                .peek(Thread::start)
                .collect(Collectors.toList());
        threads.forEach(Helpers::join);
    }

    @Test
    public void producerConsumerOnReentrantLockWithConditions() {
        final LinkedList<Integer> q = new LinkedList<>();
        int size = 10;
        AtomicInteger data = new AtomicInteger(0);
        ReentrantLock lock = new ReentrantLock();
        Condition consumed = lock.newCondition(); // signal on consumption, waited by producer when queue is full
        Condition produced = lock.newCondition(); // signal on producing, waited by consumer when queue is empty

        Runnable supplier = () -> {
            for (int i = 0; i < 10; i++) {
                int s = data.incrementAndGet();
                lock.lock();
                try {
                    while (q.size() >= size) {
                        try {
                            consumed.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    q.add(s);
                    produced.signalAll();
                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "<-" + s);
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 10; i++) {
                int s;
                lock.lock();
                try {
                    while (q.isEmpty()) {
                        try {
                            produced.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    s = q.remove();
                    consumed.signalAll();
                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "->" + s);
            }
        };

        List<Thread> threads = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> i % 2 == 0 ? new Thread(supplier, "s " + i)
                        : new Thread(consumer, "c " + i))
                .peek(Thread::start)
                .collect(Collectors.toList());
        threads.forEach(Helpers::join);
    }


    public Thread findThreadById(long threadId) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getId() == threadId) {
                return t;
            }
        }
        return null;
    }

    @Test
    @Timeout(1)
    public void deadlockAndCatchIt() throws ExecutionException, InterruptedException {
        String o1 = "custom-lock-1", o2 = "custom-lock-2";
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("Barrier is tripped"));
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // Create DeadLock
        Future<?> f1 = executorService.submit(() -> {
            Helpers.barrierAwait(cyclicBarrier);
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + ". Acquired lock on o1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + ". Acquired lock on o2");
                }
            }
        });

        Future<?> f2 = executorService.submit(() -> {
            Helpers.barrierAwait(cyclicBarrier);
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + ". Acquired lock on o2");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + ". Acquired lock on o1");
                }
            }
        });

        // create deadlock catcher
        Future<?> f3 = executorService.submit(() -> {
            ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
            long[] deadlockedIds = mbean.findDeadlockedThreads();

            if (deadlockedIds != null) {
                System.err.println("Deadlock detected");
                ThreadInfo[] threadInfos =
                        mbean.getThreadInfo(deadlockedIds);
                for (ThreadInfo ti : threadInfos) {
                    System.err.println("Thread: " + ti.getThreadName() + "---------");
                    System.err.println("Lock Name: " + ti.getLockName());
                    System.err.println("Lock Owner Name: " + ti.getLockOwnerName());
                }
                System.err.println("Try to kill deadlocked threads");
                for (long tid : deadlockedIds) {
                    Thread t = findThreadById(tid);
                    if (t != null) {
                        System.err.println("Found thread " + t.getName() + ". Attempt to stop");
                        t.stop(); // doesn't work and should not. :-)
                    }
                }
            }
        });

        // stand up
        f3.get();
        f1.get();
        f2.get();
    }
}
