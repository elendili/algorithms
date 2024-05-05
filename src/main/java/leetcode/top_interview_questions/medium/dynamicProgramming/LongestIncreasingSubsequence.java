package leetcode.top_interview_questions.medium.dynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/111/dynamic-programming/810/
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Note:
There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // contains numbers from input nums which form increasing sequence
        int[] increasingSequence = new int[nums.length];
        int l = 0;
        int seqsIndex;
        for (int i = 0; i < nums.length; i++) {
            final int v = nums[i];
            seqsIndex = Arrays.binarySearch(increasingSequence, 0, l, v);
            if (seqsIndex < 0) {
                seqsIndex = -(seqsIndex + 1);
            }
            final int curLength = seqsIndex + 1;
            l = Math.max(l, curLength);
            increasingSequence[seqsIndex] = v;
        }
        return l;
    }

    @Test
    public void downTest() {
        Assertions.assertEquals(1, lengthOfLIS(new int[]{3, 2, 1}));
        Assertions.assertEquals(1, lengthOfLIS(new int[]{-1, -2, -3}));
    }

    @Test
    public void upTest() {
        Assertions.assertEquals(3, lengthOfLIS(new int[]{1, 2, 3}));
        Assertions.assertEquals(3, lengthOfLIS(new int[]{-3, -2, -1}));
    }

    @Test
    public void test() {
        Assertions.assertEquals(4, lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        Assertions.assertEquals(3, lengthOfLIS(new int[]{10, 1, 9, 2, 8, 3}));
        Assertions.assertEquals(3, lengthOfLIS(new int[]{100, 1, 101, 2, 102, 3}));
        Assertions.assertEquals(3, lengthOfLIS(new int[]{100, 1, 101, 2, 102, 3}));
        Assertions.assertEquals(2, lengthOfLIS(new int[]{100, 0, 0, 0, 0, 101}));
    }

}
