package leetcode.top_interview_questions.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/99/others/722/
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 */
public class MissingNumber {

        public int missingNumber(int[] nums) {
            int sum = 0;
            for(int n:nums){
                sum+=(1+n);
            }
            int n= nums.length;
            int goodAnswer = (n+1)*(n+2)/2; // god bless Gauss
            int diff = goodAnswer - sum  - 1;
            return diff;
        }

    @Test
    public void test() {
        assertEquals(0,missingNumber(new int[]{})); // n=1, n(n+1)/2 => 1.
        assertEquals(0,missingNumber(new int[]{1})); // n=1, n(n+1)/2 => 1.
                                                              // (with 1 shift )=>  2(3)/2=3 vs 2 from shifted sum  => (3-2)-1 = 0 (missed number)
        assertEquals(0,missingNumber(new int[]{2,3,1})); // ()
        assertEquals(2,missingNumber(new int[]{3,0,1}));
        assertEquals(8,missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
    }
}
