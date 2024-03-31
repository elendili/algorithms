package helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ConcurrencyHelpers {
    public static void barrierAwait(CyclicBarrier c) {
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void wait(Object o, long timeout) {
        try {
            o.wait(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void join(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String currentThreadName() {
        String n = Thread.currentThread().getName();
        return n;
    }

    @Test
    public void arraycopyTest() {
        int[] a = new int[]{1, 2, 3, 4};
        int j = 1;
        System.arraycopy(a, j, a, j + 1, a.length - j - 1);
        Arrays.stream(a).mapToObj(i -> i + " ").forEach(System.out::print);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 3}, a);
    }

}
