package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/trapping-rain-water/editorial/
 */
public class TrappingRainWater {
    // sliding window
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int out = 0;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            if (leftHeight < rightHeight) {
                if (leftHeight > leftMax) {
                    leftMax = leftHeight;
                } else {
                    out += leftMax - leftHeight;
                }
                left++;
            } else {
                if (rightHeight > rightMax) {
                    rightMax = rightHeight;
                } else {
                    out += rightMax - rightHeight;
                }
                right--;
            }
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, trap(new int[]{2, 1, 2}));
        Assertions.assertEquals(0, trap(new int[]{1, 2, 1}));
        Assertions.assertEquals(1, trap(new int[]{3, 1, 2}));
        Assertions.assertEquals(6, trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        Assertions.assertEquals(9, trap(new int[]{4, 2, 0, 3, 2, 5}));
    }
}
