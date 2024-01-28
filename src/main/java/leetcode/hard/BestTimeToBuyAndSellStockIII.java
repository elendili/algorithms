package leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        // cost of second transaction is compensated by profit from first transaction
        int t1_cost = Integer.MAX_VALUE;
        int t2_cost = Integer.MAX_VALUE;
        int t1_profit = 0;
        int t2_profit = 0;

        for (int price : prices) {
            // first transaction
            t1_cost = Math.min(t1_cost, price);
            t1_profit = Math.max(t1_profit, price - t1_cost);
            // second transaction
            t2_cost = Math.min(t2_cost, price - t1_profit);
            t2_profit = Math.max(t2_profit, price - t2_cost);
        }
        return t2_profit;
    }

    @Test
    public void testSimple() {
        assertEquals(0, maxProfit(new int[]{1}));
        assertEquals(0, maxProfit(new int[]{2, 1}));
        assertEquals(1, maxProfit(new int[]{1, 2}));
        assertEquals(2, maxProfit(new int[]{1, 2, 3}));
        assertEquals(4, maxProfit(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testSimple2() {
        assertEquals(1, maxProfit(new int[]{1, 1, 0, 1}));
        assertEquals(2, maxProfit(new int[]{0, 1, 0, 1}));
        assertEquals(0, maxProfit(new int[]{1, 1, 1, 1}));
        assertEquals(2, maxProfit(new int[]{1, 0, 1, 0, 1}));
        assertEquals(2, maxProfit(new int[]{1, 0, 1, 0, 1, 0, 1}));
    }

    @Test
    public void testSimple3() {
        assertEquals(3, maxProfit(new int[]{0, 1, 0, 1, 2}));
    }

    @Test
    public void test() {
        assertEquals(6, maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        assertEquals(4, maxProfit(new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}
