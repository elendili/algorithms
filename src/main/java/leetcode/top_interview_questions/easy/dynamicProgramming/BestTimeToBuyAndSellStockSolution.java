package leetcode.top_interview_questions.easy.dynamicProgramming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* https://leetcode.com/explore/featured/card/top-interview-questions-easy/97/dynamic-programming/572/
Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (i.e.,
buy one and sell one share of the stock), design an algorithm to find the maximum profit.
Note that you cannot sell a stock before you buy one.
*/
public class BestTimeToBuyAndSellStockSolution {

    static class ViaBackwardIterationAndSearchingMaxProfitThroughShiftingSellPoint implements MaxProfitSolver {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length < 2) {
                return 0;
            }
            int out = 0;
            int l = prices.length;
            int sellPrice = prices[l - 1];
            for (int i = l - 1; i > -1; i--) {
                int cur = prices[i];
                if (cur >= sellPrice) {
                    sellPrice = cur;
                } else {
                    out = Math.max(out, sellPrice - cur);
                }
            }
            return out;
        }
    }

    static class ViaDiffMaxPriceAndSellPrice implements MaxProfitSolver {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length < 2) {
                return 0;
            }
            int l = prices.length;
            int minBuy = Integer.MAX_VALUE;
            int maxProfit = 0;
            for (int i = 0; i < l; i++) {
                int cur = prices[i];
                minBuy = Math.min(minBuy, cur);
                maxProfit = Math.max(maxProfit, cur - minBuy);
            }
            return maxProfit;
        }
    }

    interface MaxProfitSolver {
        int maxProfit(int[] prices);
    }

    static Stream<Arguments> methods() {
        return Stream.of(
                Arguments.arguments("ViaBackwardIterationAndSearchingMaxProfitThroughShiftingSellPoint",
                        new ViaBackwardIterationAndSearchingMaxProfitThroughShiftingSellPoint()),
                Arguments.arguments("ViaDiffMaxPriceAndSellPrice",
                        new ViaDiffMaxPriceAndSellPrice())
        );

    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("methods")
    public void test(String name, MaxProfitSolver maxProfitSolver) {
        assertEquals(5, maxProfitSolver.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(0, maxProfitSolver.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}
