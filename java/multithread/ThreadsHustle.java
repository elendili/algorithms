package multithread;

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

public class ThreadsHustle {
    /* task ?
     fill shared int array from diff threads and
     in result there should be sorted array.
     Every thread has its own random value;
    *
    * */
    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    @Test
    public void arraycopyTest() {
        int[] a = new int[]{1, 2, 3, 4};
        int j = 1;
        System.arraycopy(a, j, a, j + 1, a.length - j - 1);
        Arrays.stream(a).mapToObj(i -> i + " ").forEach(System.out::print);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 3}, a);
    }

    @Test
    public void getLogBase2Test() {
        Assertions.assertEquals(0, getLogBase2(1));
        Assertions.assertEquals(1, getLogBase2(2));
        Assertions.assertEquals(2, getLogBase2(4));
        Assertions.assertEquals(3, getLogBase2(8));
        Assertions.assertEquals(3, getLogBase2(10));
    }

    static void sleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void wait(Object o, long timeout) {
        try {
            o.wait(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void join(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int getLogBase2(int n) {
        int out = -1;
        while (n > 0) {
            n = n >> 1;
            out++;
        }
        return out;
    }

    @Test
    public void fillArrayToSort() {
        final int threadCount = 10;
        final int[] a = new int[threadCount];
        Collection<Thread> threads = new ArrayList<>(threadCount);
        final int sortAttempts = threadCount * getLogBase2(threadCount);
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
        final int size = 10;
        AtomicInteger data = new AtomicInteger(0);

        Runnable supplier = () -> {
            for (int i = 0; i < 10; i++) {
                int s = data.incrementAndGet();
                synchronized (list) {
                    while (list.size() >= size) {
                        wait(list, 0);
                    }
                    list.add(s);
                    list.notifyAll();
                }
                System.out.println(Thread.currentThread().getName() + "->" + s);
                sleep(1);
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 10; i++) {
                int s;
                synchronized (list) {
                    while (list.isEmpty()) {
                        wait(list, 0);
                    }
                    s = list.poll();
                    list.notifyAll();
                }
                System.out.println(Thread.currentThread().getName() + "<-" + s);
                sleep(1);
            }
        };

        List<Thread> threads = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> i % 2 == 0 ? new Thread(supplier, "s " + i)
                        : new Thread(consumer, "c " + i))
                .peek(Thread::start)
                .collect(Collectors.toList());
        threads.forEach(ThreadsHustle::join);
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
        threads.forEach(ThreadsHustle::join);
    }

    @Test
    public void producerConsumerOnReentrantLockWithConditions() {
        final LinkedList<Integer> q = new LinkedList<>();
        int size = 10;
        AtomicInteger data = new AtomicInteger(0);
        ReentrantLock lock = new ReentrantLock();
        Condition consumed = lock.newCondition();
        Condition produced = lock.newCondition();

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
//                sleep(1);
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
//                sleep(1);
            }
        };

        List<Thread> threads = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> i % 2 == 0 ? new Thread(supplier, "s " + i)
                        : new Thread(consumer, "c " + i))
                .peek(Thread::start)
                .collect(Collectors.toList());
        threads.forEach(ThreadsHustle::join);
    }

    static void barrierAwait(CyclicBarrier c) {
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
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
    @Timeout(2)
    public void deadlockAndCatchIt() throws ExecutionException, InterruptedException {
        String o1 = "custom-lock-1", o2 = "custom-lock-2";
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("Barrier is tripped"));
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // Create DeadLock
        Future<?> f1 = executorService.submit(() -> {
            barrierAwait(cyclicBarrier);
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + ". Acquired lock on o1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + ". Acquired lock on o2");
                }
            }
        });

        Future<?> f2 = executorService.submit(() -> {
            barrierAwait(cyclicBarrier);
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
