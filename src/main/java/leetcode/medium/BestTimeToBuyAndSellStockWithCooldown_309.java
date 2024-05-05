package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStockWithCooldown_309 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;

        // states:
        // 0 - sold. after sold. no stock, next state is:    cool-down/reset(2)
        // 1 - held. after buy. have stock, next states are: held(1), sold(0)
        // 2 - reset.  no stock, next states are:            reset(2), held(1)
        // dp = new int[n][states]

        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            int fromSold, fromHeld, fromReset;
            if (i > 0) {
                int[] prev = dp[i - 1];
                fromSold = prev[0];
                fromHeld = prev[1];
                fromReset = prev[2];
            } else {
                fromSold = Integer.MIN_VALUE;
                fromHeld = Integer.MIN_VALUE;
                fromReset = 0;
            }
            int[] cur = dp[i];
            // from held to sold. sell:
            cur[0] = prices[i] + fromHeld;
            // from (reset and sold) to reset. rest
            cur[2] = Math.max(fromReset, fromSold);
            // from (reset and held) to held. buy?
            cur[1] = Math.max(-prices[i] + fromReset, fromHeld);
        }

        int maxProfit = 0;
        for (int i = 0; i < 3; i++) {
            maxProfit = Math.max(maxProfit, dp[n - 1][i]);
        }
        return maxProfit;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        /**
         * Input: prices = [1,2,3,0,2]
         * Output: 3
         * Explanation: transactions = [buy, sell, cooldown, buy, sell]
         * Example 2:
         *
         * Input: prices = [1]
         * Output: 0
         */
        assertEquals(3, maxProfit(new int[]{1, 2, 3, 0, 2}));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(3, maxProfit(new int[]{1, 2, 3, 0, 2, 1}));
        assertEquals(3, maxProfit(new int[]{1, 2, 3, 0, 2, 2}));
        assertEquals(4, maxProfit(new int[]{1, 2, 3, 0, 2, 3}));
        assertEquals(3, maxProfit(new int[]{1, 2, 0, 2, 3}));
        assertEquals(2, maxProfit(new int[]{0, 2, 0, 2, 0}));
    }

    @org.junit.jupiter.api.Test
    public void testSimple() {
        assertEquals(0, maxProfit(new int[]{1}));
        assertEquals(1, maxProfit(new int[]{1, 2}));
        assertEquals(2, maxProfit(new int[]{1, 2, 3}));
    }

    @org.junit.jupiter.api.Test
    public void testSimple123() {
        assertEquals(2, maxProfit(new int[]{1, 2, 3}));
    }

}
