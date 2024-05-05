package leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/description/
 */
public class MinimumDifficultyOfJobSchedule_1335 {
    // [1,2,3,4,5], d=2. => [1][2,3,4,5]=6
    // [1,2,3,4,5], d=3. => [1][2][3,4,5]=8
    // [1,2,3,4,5], d=1. => [1,2,3,4,5]=5

    // [0,1,0,1,0], d=2. => [0,1,0,1][0]=1
    // [0,1,0,1,0], d=3. => [0][1,0,1][0]=1
    // [0,1,0,1,0], d=4. => [0][1,0,1][0]=1

    // [1,2,3,1,2,3], d=2. => [1][2,3,1,2,3]=4
    // [1,2,3,1,2,3], d=3. => [1][2,3][1,2,3]=7

    // [3,2,1,3,2,1], d=2. => [3][2,1,3,2,1]=6
    // [3,2,1,3,2,1], d=3. => [3,2][1][3,2,1]=7
    // [3,2,1,3,2,1], d=4. => [3,2][1][3,2][1]=8

    // need to fill days with minimum possible before growing

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (d > n || d <= 0) {
            return -1;
        }
        Integer[][] dp = new Integer[d + 1][n];
        return minDifficultyRecursive(jobDifficulty, 0, d, dp);
    }

    public int minDifficultyRecursive(final int[] jobDifficulty, final int jobIndex, final int d, final Integer[][] dp) {
        if (dp[d][jobIndex] == null) {
            System.out.println("calc");
            int curMax = 0;
            int curTotalMin = Integer.MAX_VALUE;
            for (int i = jobIndex; i < jobDifficulty.length - (d - 1); i++) {
                int cv = jobDifficulty[i];
                curMax = Math.max(curMax, cv);
                if (d > 1) {
                    int minFromNextDays = minDifficultyRecursive(jobDifficulty, i + 1, d - 1, dp);
                    curTotalMin = Math.min(curTotalMin, curMax + minFromNextDays);
                } else {
                    curTotalMin = curMax;
                }
            }
            dp[d][jobIndex] = curTotalMin;
        }
        return dp[d][jobIndex];
    }

    @Test
    public void test1() {
        int[] a = new int[]{1};
        assertEquals(-1, minDifficulty(a, 0));
        assertEquals(1, minDifficulty(a, 1));
        assertEquals(-1, minDifficulty(a, 2));
    }

    @Test
    public void test() {
        int[] a = new int[]{1, 2, 3, 4, 5};
        assertEquals(5, minDifficulty(a, 1));
        assertEquals(6, minDifficulty(a, 2));
        assertEquals(8, minDifficulty(a, 3));
        assertEquals(11, minDifficulty(a, 4));
        assertEquals(15, minDifficulty(a, 5));
        assertEquals(-1, minDifficulty(a, 6));
    }

    @Test
    public void test10() {
        int[] a = new int[]{0, 1, 0, 1, 0};
        assertEquals(1, minDifficulty(a, 1));
        assertEquals(1, minDifficulty(a, 2));
        assertEquals(1, minDifficulty(a, 3));
        assertEquals(2, minDifficulty(a, 4));
        assertEquals(2, minDifficulty(a, 5));
        assertEquals(-1, minDifficulty(a, 6));
    }

    @Test
    public void test321321() {
        int[] a = new int[]{3, 2, 1, 3, 2, 1};
        assertEquals(3, minDifficulty(a, 1));
        assertEquals(4, minDifficulty(a, 2));
        assertEquals(6, minDifficulty(a, 3));
        assertEquals(8, minDifficulty(a, 4));
    }

    @Test
    public void test123123() {
        int[] a = new int[]{1, 2, 3, 1, 2, 3};
        assertEquals(3, minDifficulty(a, 1));
        assertEquals(4, minDifficulty(a, 2));
        assertEquals(6, minDifficulty(a, 3));
        assertEquals(8, minDifficulty(a, 4));
    }

    @Test
    public void test123123123123() {
        int[] a = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3};
        assertEquals(3, minDifficulty(a, 1));
        assertEquals(4, minDifficulty(a, 2));
        assertEquals(6, minDifficulty(a, 3));
        assertEquals(8, minDifficulty(a, 4));
    }
}
