package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int length = heights.length;
        int maxArea = 0;
        for (int i = 0; i < length; i++) {
            while ((stack.peek() != -1)
                    && (heights[stack.peek()] >= heights[i])) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = length - stack.peek() - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }
        return maxArea;
    }

    @Test
    public void test() {
        Assertions.assertEquals(10, largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(4, largestRectangleArea(new int[]{2, 4}));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(4, largestRectangleArea(new int[]{2, 1, 2, 1}));
        Assertions.assertEquals(4, largestRectangleArea(new int[]{1, 0, 2, 1, 2, 1}));
    }

}
