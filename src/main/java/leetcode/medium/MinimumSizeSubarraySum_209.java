package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/?envType=study-plan-v2&envId=top-interview-150
 */
public class MinimumSizeSubarraySum_209 {
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int curWindowSum = 0;
        for (int left = 0, right = 0; right < nums.length; right++) {
            curWindowSum += nums[right];
            while (curWindowSum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                curWindowSum -= nums[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(2, minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        assertEquals(1, minSubArrayLen(4, new int[]{1, 4, 4}));
        assertEquals(0, minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }
}
