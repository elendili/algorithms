package leetcode.top_interview_questions.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        if(nums==null || nums.length==0){
            return 0;
        }
        int bestSum=Integer.MIN_VALUE;
        int curSum=Integer.MIN_VALUE;
        for(int n:nums){
            if(n<0 && curSum<0 && curSum+n>0){
                curSum=n;
            } else {
                curSum=Math.max(n,curSum+n);
            }
            bestSum=Math.max(bestSum,curSum);
        }
        return bestSum;
    }

    @Test
    public void test(){
        assertEquals(6,maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
/*
-2,1,-3,4,-1,2,1,-5,4
-2,1,1,4,4,4,

 */
}
