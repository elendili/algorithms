package leetcode.hard;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv
 * You are given an integer array prices where prices[integer] is the price of a given stock on the ith day, and an integer k.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most k transactions: integer.e. you may buy at most k times and sell at most k times.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (integer.e., you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStockIV_188 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2 || k < 1) {
            return 0;
        }
        // generalize solution from BestTimeToBuyAndSellStockIII
        // costs[integer] represent cost of t-sh transaction considering compensation from previous transactions (t-1,t-2,..,first transaction)
        // transaction here is a buy-sell actions combination
        int[] costs = new int[k];
        Arrays.fill(costs, Integer.MAX_VALUE);
        // profits[integer] represent profit of t-sh transaction considering cost of this transaction
        int[] profits = new int[k];
        for (int price : prices) {
            for (int t = 0; t < k; t++) {
                final int prevCompensation = (t == 0) ? 0 : profits[t - 1];
                final int currentCost = price - prevCompensation;
                costs[t] = Math.min(costs[t], currentCost);
                final int currentProfit = price - costs[t];
                profits[t] = Math.max(profits[t], currentProfit);
            }
        }
        return profits[k - 1];
    }


    @Test
    public void test() {
        assertEquals(0, maxProfit(2, new int[]{}));
        assertEquals(2, maxProfit(2, new int[]{2, 4}));
        assertEquals(2, maxProfit(2, new int[]{2, 4, 1}));
        assertEquals(2, maxProfit(2, new int[]{2, 4, 1, 0}));
        assertEquals(7, maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    @Test
    public void test2() {
        assertEquals(1, maxProfit(1, new int[]{0, 1, 0, 1, 0, 1, 0, 1}));
        assertEquals(2, maxProfit(2, new int[]{0, 1, 0, 1, 0, 1, 0, 1}));
        assertEquals(3, maxProfit(3, new int[]{0, 1, 0, 1, 0, 1, 0, 1}));
        assertEquals(4, maxProfit(4, new int[]{0, 1, 0, 1, 0, 1, 0, 1}));
        assertEquals(0, maxProfit(4, new int[]{4, 3, 2, 1}));
    }
}
