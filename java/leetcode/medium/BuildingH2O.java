package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
https://leetcode.com/problems/building-h2o/
There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules.
There is a barrier where each thread has to wait until a complete molecule can be formed.
Hydrogen and oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier.
These threads should pass the barrier in groups of three, and they must be able to immediately bond with each other to form a water molecule.
You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.

In other words:

If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
We don’t have to worry about matching the threads up explicitly;
that is, the threads do not necessarily know which other threads they are paired up with.
The key is just that threads pass the barrier in complete sets;
thus, if we examine the sequence of threads that bond and divide them into groups of three,
each group should contain one oxygen and two hydrogen threads.

Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.



Example 1:

Input: "HOH"
Output: "HHO"
Explanation: "HOH" and "OHH" are also valid answers.
Example 2:

Input: "OOHHHH"
Output: "HHOHHO"
Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.


Constraints:

Total length of input string will be 3n, where 1 ≤ n ≤ 20.
Total number of H will be 2n in the input string.
Total number of O will be n in the input string.
 */
public class BuildingH2O {

    interface H2OInterface {
        void hydrogen(Runnable releaseHydrogen) throws InterruptedException;

        void oxygen(Runnable releaseOxygen) throws InterruptedException;
    }

    static class H2OOnPrimitives implements H2OInterface {
        int c = 1;

        public H2OOnPrimitives() {
        }

        public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            while (c % 3 == 0) {
                wait();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            c++;
            notifyAll();

        }

        public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
            while (c % 3 != 0) {
                wait();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            c++;
            notifyAll();
        }
    }

    static class H2OOnSemaphores implements H2OInterface {
        Semaphore semH = new Semaphore(2);
        Semaphore semO = new Semaphore(0);

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            semH.acquire();
            releaseHydrogen.run();
            semO.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            semO.acquire(2);
            releaseOxygen.run();
            semH.release(2);
        }
    }

    static class Testing {

        @Test
        public void testWithPrimitives() {
            check(new H2OOnPrimitives(), 1);
            check(new H2OOnPrimitives(), 2);
            check(new H2OOnPrimitives(), 3);
            check(new H2OOnPrimitives(), 4);
        }

        @Test
        public void testWithSemaphores() {
            check(new H2OOnSemaphores(), 1);
            check(new H2OOnSemaphores(), 2);
            check(new H2OOnSemaphores(), 3);
            check(new H2OOnSemaphores(), 4);
        }

        final ThreadLocalRandom random = ThreadLocalRandom.current();

        private void check(H2OInterface h2o, int oN) {
            // gen input
            StringBuilder inputB = new StringBuilder();
            int hN = 2 * oN;
            while (hN > 0 || oN > 0) {
                if (oN > 0 && random.nextInt(3) == 0) {
                    oN--;
                    inputB.append('O');
                }
                if (hN > 0 && random.nextInt(3) != 0) {
                    hN--;
                    inputB.append('H');
                }
            }
            String input = inputB.toString();
            System.out.println(">" + input);

            // prepare platform
            List<Character> out = new CopyOnWriteArrayList<>();
            Runnable releaseHydrogen = () -> out.add('H');
            Runnable releaseOxygen = () -> out.add('O');
            ExecutorService pool = Executors.newFixedThreadPool(input.length());
            List<Future<?>> futures = new ArrayList<>();
            // submit tasks
            for (char c : input.toString().toCharArray()) {
                if (c == 'H') {
                    futures.add(pool.submit(() -> {
                        try {
                            h2o.hydrogen(releaseHydrogen);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }));
                } else {
                    futures.add(pool.submit(() -> {
                        try {
                            h2o.oxygen(releaseOxygen);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }));
                }
            }
            // wait finish
            futures.forEach(f -> {
                try {
                    f.get(30, TimeUnit.MILLISECONDS);
                } catch (InterruptedException | TimeoutException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

            // ----
            System.out.println(out);
            for (int i = 1, h2 = 0, o = 0; i <= out.size(); i++) {
                if (out.get(i - 1) == 'H') {
                    h2++;
                } else {
                    o++;
                }
                if (i > 1 && i % 3 == 0) {
                    Assertions.assertEquals(2, h2, "hydrogen mismatch");
                    Assertions.assertEquals(1, o, "oxygen mismatch");
                    h2 = 0;
                    o = 0;
                }
            }
        }

    }

}
