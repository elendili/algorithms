package yandex.Feb52021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
given an array of integers,
find minimum possible multiplication result of number pair from array

in linear time
 */
public class MinPossibleProduct {
    int minPossibleProduct(int[] a) {
        assert a != null && a.length > 1 : "array should be bigger";
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        for (int v : a) {
            if (v < min1) {
                min2 = min1;
                min1 = v;
            } else if (v < min2) {
                min2 = v;
            }
            if (v > max1) {
                max2 = max1;
                max1 = v;
            } else if (v > max2) {
                max2 = v;
            }
        }
        if (min1 < 0) {
            if (max1 >= 0) {
                return min1 * max1;
            } else {
                return max1 * max2;
            }
        }
        return min1 * min2;
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, minPossibleProduct(new int[]{1, 2, 3, 4}));
        Assertions.assertEquals(-12, minPossibleProduct(new int[]{1, 2, -3, 4}));
        Assertions.assertEquals(-4, minPossibleProduct(new int[]{-2, -1, 2, 1}));
        Assertions.assertEquals(6, minPossibleProduct(new int[]{-2, -3, -4}));
        Assertions.assertEquals(0, minPossibleProduct(new int[]{-2, 0, -4}));
        Assertions.assertEquals(-8, minPossibleProduct(new int[]{-2, 0, 4}));
        Assertions.assertEquals(0, minPossibleProduct(new int[]{0, 0}));
    }
}
