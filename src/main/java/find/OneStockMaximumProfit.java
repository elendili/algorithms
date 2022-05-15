package find;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneStockMaximumProfit {
    // return list size of 3 [profit, buy price index, sell price index]
    // method searches global maximum and minimum, considering minimum should go before maximum
    //
    public List<Integer> maxProfitForBuying1ShareOnce(int[] prices) {
        assert prices.length > 1 : "there should be more prices than 1";
        int buyPriceIndex = 0;
        int buyPrice = prices[buyPriceIndex];
        int sellPriceIndex = 1;
        int sellPrice = prices[sellPriceIndex];
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int curPrice = prices[i];
            int nextPrice = prices[i + 1];
            if (curPrice < buyPrice) {
                buyPriceIndex = i;
                buyPrice = curPrice;
            }
            if (nextPrice > sellPrice) {
                sellPriceIndex = i + 1;
                sellPrice = nextPrice;
            }
            int profit = sellPrice - buyPrice;
            if (profit > maxProfit && buyPriceIndex < sellPriceIndex) {
                maxProfit = profit;
            }
        }
        return new ArrayList<>(asList(maxProfit, buyPriceIndex, sellPriceIndex));
    }

    private int findNearestTippingPoint(int[] arr, int fromIndex) {
        int prev = arr[fromIndex];
        for (int i = fromIndex; i < arr.length - 1; i++) {
            int cur = arr[i];
            int next = arr[i + 1];
            int delta1 = prev - cur;
            int delta2 = cur - next;
            if (delta1 > 0 && delta2 < 0) {
                return -(i);// local low point
            }
            if (delta1 < 0 && delta2 > 0) {
                return (i);// local high point
            }
            prev = cur;
        }
        return arr.length;
    }

    // return cumulative profit from multiple buy/sells
    public List<Integer> maxProfitForBuying1ShareManyTimes(int[] prices) {
        assert prices.length > 1 : "there should be more prices than 1";
        int cumProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int tippingPoint = findNearestTippingPoint(prices, i);
            if (tippingPoint >= 0) {
                if (tippingPoint == prices.length) {
                    if (prices[tippingPoint - 1] > prices[i]) {
                        tippingPoint = tippingPoint - 1;
                    } else {
                        break;
                    }
                }
                int curProfit = prices[tippingPoint] - prices[i];
                cumProfit += curProfit;
                System.out.printf("buy on %d for %d, sell on %d for %d. Profit: %d. Total Profit: %d\n",
                        i, prices[i], tippingPoint, prices[tippingPoint], curProfit, cumProfit);
            }
            i = Math.abs(tippingPoint) - 1;
        }
        return new ArrayList<>(asList(cumProfit));
    }

    @Test
    public void test() {
        assertEquals("[16, 1, 4]", maxProfitForBuying1ShareOnce(new int[]{45, 24, 35, 31, 40, 38, 11}).toString());
        assertEquals("[21, 1, 6]", maxProfitForBuying1ShareOnce(new int[]{45, 24, 35, 31, 40, 38, 45}).toString());
        assertEquals("[0, 5, 1]", maxProfitForBuying1ShareOnce(new int[]{45, 44, 43, 43, 42, 41, 40}).toString());
        assertEquals("[1, 0, 1]", maxProfitForBuying1ShareOnce(new int[]{10, 11, 10, 11, 10, 11, 10}).toString());
        assertEquals("[1, 0, 1]", maxProfitForBuying1ShareOnce(new int[]{1, 2}).toString());
        assertEquals("[0, 0, 1]", maxProfitForBuying1ShareOnce(new int[]{2, 1}).toString());
    }

    @Test
    public void findNearestTippingPoint() {
        assertEquals(3, findNearestTippingPoint(new int[]{1, 2, 3}, 0));
        assertEquals(-1, findNearestTippingPoint(new int[]{1, 0, 3}, 0));
        assertEquals(1, findNearestTippingPoint(new int[]{1, 2, 1}, 0));
        assertEquals(2, findNearestTippingPoint(new int[]{1, 2, 3, 2, 1}, 1));
        assertEquals(2, findNearestTippingPoint(new int[]{1, 2}, 1));
        assertEquals(2, findNearestTippingPoint(new int[]{2, 1}, 1));
    }

    @Test
    public void test2() {
        assertEquals("[3]", maxProfitForBuying1ShareManyTimes(new int[]{10, 11, 10, 11, 10, 11, 10}).toString());
        assertEquals("[2]", maxProfitForBuying1ShareManyTimes(new int[]{11, 10, 11, 10, 11}).toString());
        assertEquals("[20]", maxProfitForBuying1ShareManyTimes(new int[]{45, 24, 35, 31, 40, 38, 11}).toString());
        assertEquals("[0]", maxProfitForBuying1ShareManyTimes(new int[]{6, 5, 4, 3, 2, 1}).toString());
        assertEquals("[4]", maxProfitForBuying1ShareManyTimes(new int[]{1, 2, 3, 4, 4, 5}).toString());
        System.out.println("----");
        assertEquals("[6]", maxProfitForBuying1ShareManyTimes(new int[]{1, 2, 3, 4, 3, 6}).toString());
        System.out.println("----");
        assertEquals("[4]", maxProfitForBuying1ShareManyTimes(new int[]{1, 2, 3, 4, 3, 4}).toString());
        assertEquals("[3]", maxProfitForBuying1ShareManyTimes(new int[]{1, 2, 3, 4, 4, 4}).toString());
        assertEquals("[1]", maxProfitForBuying1ShareManyTimes(new int[]{1, 2}).toString());
        assertEquals("[0]", maxProfitForBuying1ShareManyTimes(new int[]{2, 1}).toString());
    }


}
