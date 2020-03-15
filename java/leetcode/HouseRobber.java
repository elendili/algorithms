package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/house-robber/
public class HouseRobber {
    public int rob(int[] a) {
        int accumulatedToMinus1 = 0;
        int accumulatedToMinus2 = 0;
        int accumulatedCur = 0;
        for (int i = 0; i < a.length; i++) {
            if (accumulatedToMinus1 > accumulatedToMinus2 + a[i]) {
                accumulatedCur = accumulatedToMinus1;
            } else {
                accumulatedCur = accumulatedToMinus2 + a[i];
            }
            // shift
            accumulatedToMinus2 = accumulatedToMinus1;
            accumulatedToMinus1 = accumulatedCur;
        }
        return accumulatedCur;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0, rob(new int[]{}));
        Assertions.assertEquals(9, rob(new int[]{9}));
        Assertions.assertEquals(9, rob(new int[]{1, 9}));
        Assertions.assertEquals(9, rob(new int[]{1, 9, 1}));
        Assertions.assertEquals(9, rob(new int[]{4, 1, 5}));
        Assertions.assertEquals(9, rob(new int[]{4, 1, 5, 1}));
        Assertions.assertEquals(9, rob(new int[]{4, 1, 1, 5}));
        Assertions.assertEquals(9, rob(new int[]{1, 4, 1, 5}));
        Assertions.assertEquals(9, rob(new int[]{1, 4, 1, 5, 1}));
        Assertions.assertEquals(9, rob(new int[]{4, 1, 1, 5, 1}));
        Assertions.assertEquals(9, rob(new int[]{4, 1, 0, 1, 5}));
        Assertions.assertEquals(9, rob(new int[]{4, 1, 0, 1, 1, 4}));
    }
}
