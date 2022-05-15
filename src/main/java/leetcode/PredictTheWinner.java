package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/predict-the-winner/
public class PredictTheWinner {

    public int getDeepSum(int[] ar,
                          final int index,
                          final int endIndex,
                          final int[][] memo) {
        if (memo[index][endIndex] != 0) {
            return memo[index][endIndex];
        }
        int sum = 0;
        int step = endIndex >= index ? 1 : -1;
        int steps = Math.max(index, endIndex) - Math.min(index, endIndex);
        for (int i = 0; i <= steps; i++) {
            int sideMultiplier = ((i % 2 == 0) ? 1 : -1);
            int indexToGetValue = index + i * step;
            int toSum = sideMultiplier * ar[indexToGetValue];
            sum += toSum;
            memo[index][indexToGetValue] = sum;
        }
        return sum;
    }

    public boolean chooseLeft(final int[] ar,
                              final int l,
                              final int r,
                              int level,
                              final int[][] memo
    ) {
        boolean out;
        if (level < 1) {
            out = ar[l] >= ar[r];
        } else {
            int lSum = getDeepSum(ar, l, l + level, memo);
            int rSum = getDeepSum(ar, r, r - level, memo);
            if (lSum == rSum) {
                out = chooseLeft(ar, l, r, level - 1, memo);
            } else {
                out = lSum > rSum;
            }
        }
        return out;
    }

    public boolean PredictTheWinner(int[] a) {
        if (a.length < 3) {
            return true;
        }
        int l = 0, r = a.length - 1;
        int asum = 0, bsum = 0;
        int[][] memo = new int[a.length][a.length];
        for (int i = 0; i < a.length - 1; i++) {

            int level = (r - l) / 2;
            int res;
            if (chooseLeft(a, l, r, level, memo)) {
                res = a[l++];
            } else {
                res = a[r--];
            }

            if (i % 2 == 0) {
                asum += res;
            } else {
                bsum += res;
            }
        }
        return asum >= bsum;
    }

    @Test
    public void getDeepSumTest() {
        int[][] m = new int[10][10];
        assertEquals(1 - 2 + 3, getDeepSum(new int[]{1, 2, 3, 4}, 0, 2, m));
        assertEquals(1 - 2 + 3 - 4, getDeepSum(new int[]{1, 2, 3, 4}, 0, 3, m));
        assertEquals(4 - 3 + 2, getDeepSum(new int[]{1, 2, 3, 4}, 3, 1, m));
        assertEquals(4 - 3 + 2 - 1, getDeepSum(new int[]{1, 2, 3, 4}, 3, 0, m));
    }


    @Test
    public void test() {
        Assertions.assertTrue(PredictTheWinner(new int[]{1, 5}));
        Assertions.assertFalse(PredictTheWinner(new int[]{1, 5, 2}));
        Assertions.assertTrue(PredictTheWinner(new int[]{1, 5, 233, 7}));
        Assertions.assertFalse(PredictTheWinner(new int[]{1, 1, 1, 233, 1}));
        Assertions.assertTrue(PredictTheWinner(new int[]{1, 1, 1, 233, 1, 1}));
        Assertions.assertTrue(PredictTheWinner(new int[]{1, 1, 1, 1, 233, 1}));

        Assertions.assertTrue(PredictTheWinner(new int[]{1, 1, 1, 0, 1}));
        Assertions.assertTrue(PredictTheWinner(new int[]{1, 1, 1, 0, 0, 1}));
        Assertions.assertTrue(PredictTheWinner(new int[]{1, 1, 1, 0, 0, 1, 1}));

        Assertions.assertFalse(PredictTheWinner(new int[]{3606449, 6, 5, 9, 452429, 7, 9580316, 9857582, 8514433, 9, 6, 6614512, 753594, 5474165, 4, 2697293, 8, 7, 1}));

    }
}
