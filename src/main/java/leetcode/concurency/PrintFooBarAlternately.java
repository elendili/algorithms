package leetcode.concurency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*

https://leetcode.com/problems/print-foobar-alternately/

Suppose you are given the following code:

class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
The same instance of FooBar will be passed to two different threads.
Thread A will call foo() while thread B will call bar().
Modify the given program to output "foobar" n times.



Example 1:
Input: n = 1
Output: "foobar"
Explanation: There are two threads being fired asynchronously.
One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
Example 2:

Input: n = 2
Output: "foobarfoobar"
Explanation: "foobar" is being output 2 times.

 */
public class PrintFooBarAlternately {

    interface FooBarInterface {
        void foo(Runnable printFoo) throws InterruptedException;

        void bar(Runnable printBar) throws InterruptedException;
    }

    static class FooBar extends FooBarBusySpin {
        public FooBar(int n) {
            super(n);
        }
    }

    static class FooBarBusySpin implements FooBarInterface {
        private int n;
        AtomicInteger counter = new AtomicInteger();

        public FooBarBusySpin(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (counter.get() % 2 != 0) ;
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                counter.getAndIncrement();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (counter.get() % 2 == 0) ;
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                counter.getAndIncrement();
            }
        }
    }

    static class FooBarPrimitiveMutex implements FooBarInterface {
        private int n;
        AtomicInteger counter = new AtomicInteger();

        public FooBarPrimitiveMutex(int n) {
            this.n = n;
        }

        public synchronized void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (counter.get() % 2 != 0) {
                    wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                counter.getAndIncrement();
                notifyAll();
            }
        }

        public synchronized void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (counter.get() % 2 == 0) {
                    wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                counter.getAndIncrement();
                notifyAll();
            }
        }
    }

    static class FooBarSemaphores implements FooBarInterface {
        private int n;
        Semaphore s = new Semaphore(0);
        Semaphore s2 = new Semaphore(1);

        public FooBarSemaphores(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                s2.acquire();
                printFoo.run();
                s.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                s.acquire(); // always blocked because semaphore is initiated with 0 permits, and need to call release() first
                printBar.run();
                s2.release();
            }
        }
    }

    static class FooBarReentrantLock implements FooBarInterface {
        private int n;
        ReentrantLock lock = new ReentrantLock();
        Condition conditionFooPrinted = lock.newCondition();
        Condition conditionBarPrinted = lock.newCondition();
        private boolean fooPrintedFlag = false;

        public FooBarReentrantLock(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    while (fooPrintedFlag) {
                        conditionBarPrinted.await();
                    }
                    printFoo.run();
                    fooPrintedFlag = true;
                    conditionFooPrinted.signalAll();
                } finally {
                    lock.unlock();
                }

            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    while (!fooPrintedFlag) {
                        conditionFooPrinted.await();
                    }
                    printBar.run();
                    fooPrintedFlag = false;
                    conditionBarPrinted.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    public static Stream<Arguments> predicateStream() {
        return Stream.of(
                Arguments.arguments("FooBarBusySpin", (Function<Integer, FooBarInterface>) FooBarBusySpin::new),
                Arguments.arguments("FooBarPrimitiveMutex", (Function<Integer, FooBarInterface>) FooBarPrimitiveMutex::new),
                Arguments.arguments("FooBarSemaphores", (Function<Integer, FooBarInterface>) FooBarSemaphores::new),
                Arguments.arguments("FooBarReentrantLock", (Function<Integer, FooBarInterface>) FooBarReentrantLock::new)
        );
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void test(String name, Function<Integer, FooBarInterface> sutCreator) {
        assertTest(sutCreator, 1);
        assertTest(sutCreator, 2);
        assertTest(sutCreator, 3);
        assertTest(sutCreator, 100);
        assertTest(sutCreator, 1001);
    }


    void assertTest(Function<Integer, FooBarInterface> sutClassSupplier, int n) {
        String expected = IntStream.rangeClosed(1, n).mapToObj(i -> "foobar").collect(Collectors.joining());

        FooBarInterface z = sutClassSupplier.apply(n);
        StringBuffer sb = new StringBuffer();
        Future<?> fut0 = Executors.newSingleThreadExecutor().submit(() -> {
            try {
                z.foo(() -> sb.append("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Future<?> fut1 = Executors.newSingleThreadExecutor().submit(() -> {
            try {
                z.bar(() -> sb.append("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            fut0.get(1, TimeUnit.SECONDS);
            fut1.get(1, TimeUnit.MILLISECONDS);
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

}
