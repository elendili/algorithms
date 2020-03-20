package helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Helpers {
    public static void barrierAwait(CyclicBarrier c) {
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
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

    public static int getLogBase2(int n) {
        int out = -1;
        while (n > 0) {
            n = n >> 1;
            out++;
        }
        return out;
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
}
