package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/ugly-number/
 */
public class UglyNumber_263 {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }

        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }

        return n == 1;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(true, isUgly(6));
        assertEquals(true, isUgly(1));
        assertEquals(false, isUgly(14));
        assertEquals(false, isUgly(8128)); // 8128 = 2^6 * 127
        assertEquals(false, isUgly(0));
        assertEquals(false, isUgly(-6));
    }
}
