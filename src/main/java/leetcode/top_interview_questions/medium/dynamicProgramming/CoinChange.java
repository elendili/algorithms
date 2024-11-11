package leetcode.top_interview_questions.medium.dynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/111/dynamic-programming/809/
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {
    private static void helper(final int[] coins,
                               final int coinIndex,
                               final int amount,           // how much left to exchange
                               final int accumulatedCount, // how much coins we have in current recursion branch
                               final int[] result) {
        if (coinIndex > -1) {
            int count = amount / coins[coinIndex];
            final int rem = amount % coins[coinIndex];
            if (rem == 0) {
                int toPut = accumulatedCount + count;
                result[0] = result[0] < 0? toPut : Math.min(result[0], toPut);
                return;
            }
            while (count > -1
                    &&
                    (result[0]<0
                    ||
                    // check if final result is already better than can be found in this loop
                    (accumulatedCount + count + 1) < result[0])) {
                helper(coins, coinIndex - 1, amount - coins[coinIndex] * count, accumulatedCount + count, result);
                count--; // let's try with less count
            }
        }
    }

    public static int coinChange(final int[] coins, final int amount) {
        Arrays.sort(coins);
        int[] result = new int[]{-1};
        helper(coins, coins.length - 1, amount, 0, result);
        return result[0];
    }

    /*
        perf  cpomplexity O(S*n)
        space complexity O(n)
     */
    public static int coinChangeDP(int[] coins, final int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int best = dp[i];
            for (int j = coins.length - 1; j > -1; j--) {
                int coin = coins[j];
                if (coin <= i) {
                    best = Math.min(best, dp[i - coin] + 1);
                }
            }
            dp[i] = best;
        }
        int out = dp[amount];
        out = (out > amount) ? -1 : out;
        return out;
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void test(String name, BiFunction<int[], Integer, Integer> methodUnderTest) {
        Assertions.assertEquals(3, methodUnderTest.apply(new int[]{1, 2, 5}, 11));
        Assertions.assertEquals(2, methodUnderTest.apply(new int[]{1, 2, 5}, 10));
        Assertions.assertEquals(5, methodUnderTest.apply(new int[]{1, 2}, 10));
        Assertions.assertEquals(7, methodUnderTest.apply(new int[]{1}, 7));
        Assertions.assertEquals(3, methodUnderTest.apply(new int[]{1, 3, 5}, 7));
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void negTest(String name, BiFunction<int[], Integer, Integer> methodUnderTest) {
        Assertions.assertEquals(-1, methodUnderTest.apply(new int[]{3}, 10));
        Assertions.assertEquals(-1, methodUnderTest.apply(new int[]{2}, 3));
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void giantTest(String name, BiFunction<int[], Integer, Integer> methodUnderTest) {
        Assertions.assertEquals(-1, methodUnderTest.apply(new int[]{Integer.MAX_VALUE}, Integer.MAX_VALUE / 100));
        Assertions.assertEquals(1, methodUnderTest.apply(new int[]{Integer.MAX_VALUE / 100}, Integer.MAX_VALUE / 100));
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void test2(String name, BiFunction<int[], Integer, Integer> methodUnderTest) {
        Assertions.assertEquals(20, methodUnderTest.apply(new int[]{186, 419, 83, 408}, 6249));
    }
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void test_TLE(String name, BiFunction<int[], Integer, Integer> methodUnderTest) {
        Assertions.assertEquals(24, methodUnderTest.apply(new int[]{411,412,413,414,415,416,417,418,419,420,421,422}, 9864));
    }

    public static Stream<Arguments> predicateStream() {
        return Stream.of(
                Arguments.arguments("coinChangeDP",
                        (BiFunction<int[], Integer, Integer>) (tn, amount) -> coinChangeDP(tn, amount)),
                Arguments.arguments("coinChange",
                        (BiFunction<int[], Integer, Integer>) (tn, amount) -> coinChange(tn, amount))

        );
    }
}
