package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SquaresOfSortedArray {
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        final int[] out = new int[nums.length];
        for (int i = nums.length-1; i>-1; i--) {
            int leftN = nums[left];
            int rightN = nums[right];
            final int toSquare;
            if (Math.abs(leftN) < Math.abs(rightN)) {
                toSquare = rightN;
                right--;
            } else {
                toSquare = leftN;
                left++;
            }
            out[i] = toSquare * toSquare;
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals("[1, 4]",
                Arrays.toString(sortedSquares(new int[]{-2, 1})));
        Assertions.assertEquals("[0, 1, 4]",
                Arrays.toString(sortedSquares(new int[]{-2, -1, 0})));
        Assertions.assertEquals("[1, 4, 9, 16]",
                Arrays.toString(sortedSquares(new int[]{1, 2, 3, 4})));
    }

    @Test
    public void test2() {
        Assertions.assertEquals("[0, 1, 9, 16, 100]",
                Arrays.toString(sortedSquares(new int[]{-4, -1, 0, 3, 10})));
        Assertions.assertEquals("[4, 9, 9, 49, 121]",
                Arrays.toString(sortedSquares(new int[]{-7, -3, 2, 3, 11})));
    }
}
