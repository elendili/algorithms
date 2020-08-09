package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/549/
Given a non-empty array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int out=0;
        for(int n:nums){
            out^=n;
        }
        return out;
    }
    @Test
    public void test(){
        assertEquals(1, singleNumber(new int[]{2,2,1}));
        assertEquals(4, singleNumber(new int[]{4,1,2,1,2}));
        assertEquals(-4, singleNumber(new int[]{-4,-1,2,-1,2}));
    }
}
