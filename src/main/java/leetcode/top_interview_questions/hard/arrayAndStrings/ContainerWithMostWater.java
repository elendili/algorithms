package leetcode.top_interview_questions.hard.arrayAndStrings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-hard/116/array-and-strings/830/
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
In this case, the max area of water (blue section) the container can contain is 49.

Example:
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 */
public class ContainerWithMostWater {
    public int maxArea(int[] a) {
        int maxArea = 0;
        for (int l = 0, r = a.length - 1; l < r; ) {
            int h1 = a[l], h2 = a[r];
            maxArea = Math.max(maxArea, Math.min(h1, h2) * (r - l));
            if (h1 < h2) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }

    @Test
    public void test1() {
        Assertions.assertEquals(0, maxArea(new int[]{0}));
        Assertions.assertEquals(0, maxArea(new int[]{1}));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(0, maxArea(new int[]{0, 1}));
        Assertions.assertEquals(1, maxArea(new int[]{1, 1}));
        Assertions.assertEquals(1, maxArea(new int[]{1, 2}));
        Assertions.assertEquals(2, maxArea(new int[]{2, 2}));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(0, maxArea(new int[]{0, 0, 1}));
        Assertions.assertEquals(2, maxArea(new int[]{1, 10, 1}));
        Assertions.assertEquals(20, maxArea(new int[]{10, 1, 10}));
        Assertions.assertEquals(2, maxArea(new int[]{1, 2, 3}));
        Assertions.assertEquals(2, maxArea(new int[]{3, 2, 1}));
    }

    @Test
    public void maxMin() {
        Assertions.assertEquals(Integer.MAX_VALUE-1, maxArea(new int[]{Integer.MAX_VALUE/2, 1, Integer.MAX_VALUE/2}));
        Assertions.assertEquals(2, maxArea(new int[]{Integer.MIN_VALUE, 1, Integer.MIN_VALUE, 1}));
    }

    @Test
    public void fromTask() {
        Assertions.assertEquals(49, maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
