package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/max-consecutive-ones-ii/
 */
public class MaxConsecutiveOnesII {

    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int start = 0;
        int flipped = 0;
        int maxLength = 0;
        for (int end = 0; end < n; end++) {
            if (nums[end] == 0) {
                flipped++;
            }
            while (flipped > 1 && start < end) {
                if (nums[start] == 0) {
                    flipped--;
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, findMaxConsecutiveOnes(new int[]{0}));
        Assertions.assertEquals(2, findMaxConsecutiveOnes(new int[]{1, 1}));
        Assertions.assertEquals(2, findMaxConsecutiveOnes(new int[]{1, 0}));
        Assertions.assertEquals(1, findMaxConsecutiveOnes(new int[]{0, 0}));
        Assertions.assertEquals(2, findMaxConsecutiveOnes(new int[]{0, 0, 1, 0, 0}));
        Assertions.assertEquals(3, findMaxConsecutiveOnes(new int[]{0, 0, 1, 0, 1}));
        Assertions.assertEquals(2, findMaxConsecutiveOnes(new int[]{1, 0, 0, 1}));
        Assertions.assertEquals(4, findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0}));
        Assertions.assertEquals(4, findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }
}
