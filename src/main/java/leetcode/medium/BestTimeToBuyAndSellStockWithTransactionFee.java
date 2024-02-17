package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        int transactionCost = prices[0];
        int sell = 0;
        int profit = 0;
        // consider buy-sell action pair as transaction,
        // every next transaction depends on previous:
        // its profit = sell price - (buy price - previous transaction profit) - fee
        // such as we don't care about amount of transactions
        // we can track minimum transaction cost and maximum profit
        for (int i = 1; i < n; i++) {
            int p = prices[i];
            int currentTransactionCost = p - profit;
            transactionCost = Math.min(transactionCost, currentTransactionCost);
            int currentProfit = p - transactionCost - fee;
            profit = Math.max(profit, currentProfit);
        }
        return profit;
    }

    @Test
    public void test() {
        assertEquals(8, maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    @Test
    public void test2() {
        assertEquals(6, maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3));
    }
}
