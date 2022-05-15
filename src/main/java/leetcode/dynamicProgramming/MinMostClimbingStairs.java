package leetcode.dynamicProgramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * You are given an integer array cost where cost[i] is
 * the cost of ith step on a staircase.
 * Once you pay the cost, you can either climb one or two steps.
 * <p>
 * You can either start from the step with index 0, or the step with index 1.
 * <p>
 * Return the minimum cost to reach the top of the floor.
 */
public class MinMostClimbingStairs {
    /**
     * Intuition
             3, 4, 3, 4, 3
       ind: -1,-1, 1, 1, 2
       sum:  3, 4, 6, 8, 9

       2 variables to store cum sum to current index
       cumSum=3
       old=4
       iteration i=2, cv=3
           old=min(cumSum+cv,old+cv)=3+3=6
           cumSum=old=4
       iteration i=3, cv=4
           old=min(cumSum+cv,old+cv)=min(4+4, 6+4)=8
           cumSum=old=6
       iteration i=4, cv=3
           old=min(cumSum+cv,old+cv)=min(6+3, 8+3)=9
           cumSum=old=8

       stop. return min(old,cumSum)
        */
    public int minCostClimbingStairs(int[] cost) {
       
        assert cost.length >= 2 : "length should be more than 1";
        int cumV = cost[1];
        int preCumV = cost[0];
        int tmp;
        for (int i = 2; i < cost.length; i++) {
            int cv = cost[i];
            tmp = cumV;
            cumV = Math.min(cumV + cv, preCumV + cv);
            preCumV = tmp;
        }
        return Math.min(cumV, preCumV);
    }

    @Test
    public void increaseTest() {
        assertEquals(15,
                minCostClimbingStairs(new int[]{10, 15, 20}));
        assertEquals(30,
                minCostClimbingStairs(new int[]{10, 15, 20, 25}));
        assertEquals(40,
                minCostClimbingStairs(new int[]{10, 15, 20, 25, 35}));
    }

    @Test
    public void test2() {
        assertEquals(6,
                minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    @Test
    public void zigzagTest() {
        assertEquals(9,
                minCostClimbingStairs(new int[]{3, 4, 3, 4, 3, 4}));
        assertEquals(8,
                minCostClimbingStairs(new int[]{3, 4, 3, 4, 3}));
        assertEquals(6,
                minCostClimbingStairs(new int[]{3, 4, 3, 4}));
        assertEquals(6,
                minCostClimbingStairs(new int[]{3, 4, 3, 4}));
    }

    @Test
    public void decreaseTest() {
        assertEquals(6,
                minCostClimbingStairs(new int[]{5, 4, 3, 2, 1}));
        assertEquals(4,
                minCostClimbingStairs(new int[]{4, 3, 2, 1}));
    }

    @Test
    public void flattenTest() {
        assertEquals(2,
                minCostClimbingStairs(new int[]{10,1,0,100,0,1,10}));
        assertEquals(2,
                minCostClimbingStairs(new int[]{10,1,0,0,1,10}));
        assertEquals(11,
                minCostClimbingStairs(new int[]{0,1,10,10,1,0}));
        assertEquals(2,
                minCostClimbingStairs(new int[]{0,1,1,1,1,0}));
        assertEquals(1,
                minCostClimbingStairs(new int[]{0,1,1,1,0}));
        assertEquals(1,
                minCostClimbingStairs(new int[]{0,1,1,0}));
    }
}
