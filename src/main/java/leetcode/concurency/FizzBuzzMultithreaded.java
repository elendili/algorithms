package leetcode.concurency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;

// https://leetcode.com/problems/fizz-buzz-multithreaded/
public class FizzBuzzMultithreaded {
    class FizzBuzz {
        private int n;
        final AtomicInteger x = new AtomicInteger(1);

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            IntUnaryOperator o = i-> {
                if (i<=n && i % 5 != 0 && i % 3 == 0) {
                    printFizz.run();
                    return i+1;
                }
                return i;
            };
            while (x.getAndUpdate(o) < n);
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            IntUnaryOperator o = i-> {
                if (i<=n && i % 5 == 0 && i % 3 != 0) {
                    printBuzz.run();
                    return i+1;
                }
                return i;
            };
            while (x.getAndUpdate(o) < n);
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            IntUnaryOperator o = i-> {
                if (i<=n && i % 5 == 0 && i % 3 == 0) {
                    printFizzBuzz.run();
                    return i+1;
                }
                return i;
            };
            while (x.getAndUpdate(o) < n);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            IntUnaryOperator o = i-> {
                if (i<=n && i % 5 != 0 && i % 3 != 0) {
                    printNumber.accept(i);
                    return i+1;
                }
                return i;
            };
            while (x.getAndUpdate(o) < n);
        }
    }

    String process(FizzBuzz fizzBuzz){
        ExecutorService e = Executors.newFixedThreadPool(4);
        final LinkedList<Future<?>> futures = new LinkedList<>();
        final CopyOnWriteArrayList<String> out = new CopyOnWriteArrayList<>();

        futures.add(e.submit(() -> {
            try {
                fizzBuzz.fizz(() -> out.add("fizz"));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }));
        futures.add(e.submit(() -> {
            try {
                fizzBuzz.buzz(() -> out.add("buzz"));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }));
        futures.add(e.submit(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> out.add("fizzbuzz"));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }));
        futures.add(e.submit(() -> {
            try {
                fizzBuzz.number(x->out.add(""+x));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }));

        for(Future<?> f:futures){
            try {
                f.get(300,TimeUnit.MILLISECONDS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            } catch (TimeoutException ex) {
                ex.printStackTrace();
            }
        }
        return out.toString();
    }
    @Test
    public void test() throws InterruptedException, TimeoutException, ExecutionException {
        String exp = "[1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz]";
        Assertions.assertEquals(exp,process(new FizzBuzz(15)));
        exp = "[1, 2, fizz, 4, buzz]";
        Assertions.assertEquals(exp,process(new FizzBuzz(5)));
    }
}
