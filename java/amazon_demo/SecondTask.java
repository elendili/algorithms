package amazon_demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SecondTask {

    private static int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }

    public int generalizedGCD(int num, int[] arr) {
        int result = arr[0];
        for (int i = 1; i < num; i++)
            result = gcd(arr[i], result);

        return result;
    }

    @Test
    public void test1() {
        int actual = generalizedGCD(5, new int[]{2, 3, 4, 5, 6});
        assertEquals(1, actual);
    }

    @Test
    public void test2() {
        int actual = generalizedGCD(5, new int[]{10, 8, 6, 4, 2});
        assertEquals(2, actual);
    }
}
