package leetcode.concurency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://leetcode.com/problems/print-zero-even-odd/

Suppose you are given the following code:

class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // constructor
  public void zero(printNumber) { ... }  // only output 0's
  public void even(printNumber) { ... }  // only output even numbers
  public void odd(printNumber) { ... }   // only output odd numbers
}
The same instance of ZeroEvenOdd will be passed to three different threads:

Thread A will call zero() which should only output 0's.
Thread B will call even() which should only ouput even numbers.
Thread C will call odd() which should only output odd numbers.
Each of the threads is given a printNumber method to output an integer.
Modify the given program to output the series 010203040506... where the length of the series must be 2n.



Example 1:
Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.

Example 2:
Input: n = 5
Output: "0102030405"

 */
public class PrintZeroEvenOdd {

    @Test
    public void testOnSemaphores() {
        assertTest(ZeroEvenOdd::new, 2);
        assertTest(ZeroEvenOdd::new, 3);
        assertTest(ZeroEvenOdd::new, 4);
        assertTest(ZeroEvenOdd::new, 5);
        assertTest(ZeroEvenOdd::new, 1000);
    }

    void assertTest(Function<Integer, ZeroEvenOddInterface> sutClassSupplier, int n) {
        String expected = IntStream.rangeClosed(1, n).mapToObj(i -> "0" + i).collect(Collectors.joining());

        ZeroEvenOddInterface z = sutClassSupplier.apply(n);
        StringBuffer sb = new StringBuffer();
        Future<?> fut0 = Executors.newSingleThreadExecutor().submit(() -> {
            try {
                z.zero(sb::append);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Future<?> fut1 = Executors.newSingleThreadExecutor().submit(() -> {
            try {
                z.even(sb::append);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Future<?> fut2 = Executors.newSingleThreadExecutor().submit(() -> {
            try {
                z.odd(sb::append);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            fut0.get(1, TimeUnit.SECONDS);
            fut1.get(1, TimeUnit.MILLISECONDS);
            fut2.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
        Assertions.assertEquals(expected, sb.toString());
    }

    @Test
    public void testOnPrimitives() {
        assertTest(ZeroEvenOddOnPrimitives::new, 2);
        assertTest(ZeroEvenOddOnPrimitives::new, 3);
        assertTest(ZeroEvenOddOnPrimitives::new, 4);
        assertTest(ZeroEvenOddOnPrimitives::new, 5);
    }

    interface ZeroEvenOddInterface {
        void zero(IntConsumer printNumber) throws InterruptedException;

        void even(IntConsumer printNumber) throws InterruptedException;

        void odd(IntConsumer printNumber) throws InterruptedException;
    }

    class ZeroEvenOdd implements ZeroEvenOddInterface {
        private final int n;
        private final Semaphore sem0 = new Semaphore(1);
        private final Semaphore sem1 = new Semaphore(0);
        private final Semaphore sem2 = new Semaphore(0);

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                sem0.acquire();
                printNumber.accept(0);
                if (i % 2 == 0) {
                    sem2.release();
                } else {
                    sem1.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                sem1.acquire();
                printNumber.accept(i);
                sem0.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                sem2.acquire();
                printNumber.accept(i);
                sem0.release();
            }
        }
    }

    class ZeroEvenOddOnPrimitives implements ZeroEvenOddInterface {
        private final int n;
        private volatile int state = 0;

        public ZeroEvenOddOnPrimitives(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (state != 0) {
                    wait();
                }
                printNumber.accept(0);
                if (i % 2 == 0) {
                    state = 1;
                } else {
                    state = 2;
                }
                this.notifyAll();
            }
        }

        public synchronized void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                while (state != 2) {
                    wait();
                }
                printNumber.accept(i);
                state = 0;
                this.notifyAll();
            }
        }

        public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                while (state != 1) {
                    wait();
                }
                printNumber.accept(i);
                state = 0;
                this.notifyAll();
            }
        }
    }
}
