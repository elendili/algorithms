package leetcode.top_interview_questions.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*

https://leetcode.com/explore/featured/card/top-interview-questions-easy/97/dynamic-programming/576/
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping
you from robbing each of them is that adjacent houses have security system
connected and it will automatically contact the police if two adjacent houses
were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.

 */
public class HouseRobber {

    public int rob(int[] nums) {
        int sum_toHouseIndexMinus1=0;
        int sum_toHouseIndexMinus2=0;
        int sumBest=0;
        for (int num : nums) {
            int newSum = sum_toHouseIndexMinus2 + num;
            sumBest = Math.max(sum_toHouseIndexMinus1, newSum); // previous sum is better
            // shift to next house
            sum_toHouseIndexMinus2 = sum_toHouseIndexMinus1;
            sum_toHouseIndexMinus1 = sumBest;

        }
        return sumBest;
    }

    @Test
    public void test() {
        assertEquals(4, rob(new int[]{1,2,3,1}));
        assertEquals(12, rob(new int[]{2,7,9,3,1}));
    }
}
