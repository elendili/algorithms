package leetcode.top_interview_questions.medium.dynamicProgramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * You can assume that you can always reach the last index.
 */
public class JumpGame2 {
    public int jump(int[] a) {
        int[] dp = new int[a.length];
        final int lastIndex = a.length - 1;
        for (int i = lastIndex - 1; i > -1; i--) {
            int lastJumpIndex = i + a[i];
            if (lastJumpIndex >= lastIndex) {
                dp[i] = 1;
            } else if (lastJumpIndex == i) {
                dp[i] = a.length;
            } else {
                int curMin = lastIndex;
                for (int j = lastJumpIndex; j > i; j--) {
                    curMin = Math.min(curMin, dp[j] + 1);
                }
                dp[i] = curMin;
            }
        }
        return dp[0];
    }

    @Test
    public void testImpossible() {
        assertEquals(2, jump(new int[]{0, 2}));
    }

    @Test
    public void testSimple() {
        assertEquals(0, jump(new int[]{1}));
        assertEquals(0, jump(new int[]{2}));
        assertEquals(1, jump(new int[]{2, 2}));
        assertEquals(1, jump(new int[]{1, 2}));
        assertEquals(1, jump(new int[]{1, 0}));
        assertEquals(5, jump(new int[]{1, 1, 1, 1, 1, 0}));
        assertEquals(4, jump(new int[]{1, 1, 1, 1, 1}));
        assertEquals(1, jump(new int[]{10, 1, 1, 1, 1}));
        assertEquals(2, jump(new int[]{3, 1, 1, 1, 1}));
        assertEquals(3, jump(new int[]{2, 1, 1, 1, 1}));
        assertEquals(3, jump(new int[]{2, 1, 1, 10, 1, 0, 0, 0, 0, 0, 0, 0, 1}));
        assertEquals(1, jump(new int[]{8, 0, 0, 0, 0, 0, 0, 0, 0}));
        assertEquals(5, jump(new int[]{2,1,0,2,1,0}));
        assertEquals(2, jump(new int[]{3,2,1,3,2,1,0}));
        assertEquals(4, jump(new int[]{1,2,3,1,2,3,0}));
    }

    @Test
    public void test() {
        assertEquals(2, jump(new int[]{2, 3, 1, 1, 4}));
        assertEquals(2, jump(new int[]{2, 3, 0, 1, 4}));
    }
}
