package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
 */
public class LongestSubarrayOf1sAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int maxLength = 0;
        int start = 0;
        int zeroCount = 0;
        for (int end = 0; end < n; end++) {   // slide windows
            if (nums[end] == 0) {
                zeroCount++;
                while (zeroCount > 1) {
                    if (nums[start] == 0) {
                        zeroCount--;
                    }
                    start++;
                }
            }
            maxLength = Math.max(maxLength, end - start - zeroCount + 1);
        }
        if (maxLength == n && zeroCount == 0) {
            maxLength--;
        }
        return maxLength;

    }

    @Test
    public void test() {
        Assertions.assertEquals(0, longestSubarray(new int[]{}));
        Assertions.assertEquals(0, longestSubarray(new int[]{1}));
        Assertions.assertEquals(0, longestSubarray(new int[]{0}));
        Assertions.assertEquals(1, longestSubarray(new int[]{1, 0}));
        Assertions.assertEquals(1, longestSubarray(new int[]{0, 1}));
        Assertions.assertEquals(0, longestSubarray(new int[]{0, 0}));
        Assertions.assertEquals(2, longestSubarray(new int[]{0, 0, 1, 0, 1}));
        Assertions.assertEquals(2, longestSubarray(new int[]{1, 0, 1, 0, 0}));
        Assertions.assertEquals(3, longestSubarray(new int[]{1, 1, 1, 0, 0}));
        Assertions.assertEquals(3, longestSubarray(new int[]{0, 0, 1, 1, 0, 1}));
        Assertions.assertEquals(3, longestSubarray(new int[]{0, 0, 1, 1, 1, 0}));

        Assertions.assertEquals(3, longestSubarray(new int[]{1, 1, 0, 1}));
        Assertions.assertEquals(5, longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
        Assertions.assertEquals(2, longestSubarray(new int[]{1, 1, 1}));
    }
}
