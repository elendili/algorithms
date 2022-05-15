package leetcode.dynamicProgramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/house-robber/
 * <p>
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {

    public int rob(int[] nums) {
        int cumCurrent = 0;
        int preCum = 0;
        int prePreCum = 0;
        int t;
        for (int num : nums) {
            t = cumCurrent;
            cumCurrent = num + Math.max(preCum, prePreCum);
            prePreCum = preCum;
            preCum = t;
        }
        return Math.max(cumCurrent, preCum);
    }

    @Test
    public void test() {
        assertEquals(4, rob(new int[]{1, 2, 3, 1}));
    }

    @Test
    public void test2() {
        assertEquals(12, rob(new int[]{2, 7, 9, 3, 1}));
    }

    @Test
    public void test4() {
        assertEquals(4, rob(new int[]{2, 1, 1, 2}));
    }

    @Test
    public void test3() {
        assertEquals(201, rob(new int[]{100, 10, 1, 10, 100}));
        assertEquals(201, rob(new int[]{100, 10, 1, 100, 100}));
        assertEquals(201, rob(new int[]{100, 100, 1, 100, 100}));
        assertEquals(4, rob(new int[]{1, 1, 2, 1, 1}));
        assertEquals(1, rob(new int[]{1, 1}));
        assertEquals(4, rob(new int[]{1, 1, 3}));
        assertEquals(4, rob(new int[]{3, 1, 1}));
        assertEquals(4, rob(new int[]{3, 3, 1}));
        assertEquals(3, rob(new int[]{1, 3, 1}));
    }

}
