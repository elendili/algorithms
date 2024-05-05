package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinChangeII_518 {
    interface CoinChangeII {
        int change(int amount, int[] coins);
    }

    static class TopDown implements CoinChangeII {
        int[][] memo;
        int[] coins;

        public int numberOfWays(int i, int amount) {
            if (amount == 0) {
                return 1;
            }
            if (i == coins.length) {
                return 0;
            }
            if (memo[i][amount] != -1) {
                return memo[i][amount];
            }

            if (coins[i] > amount) {
                return memo[i][amount] = numberOfWays(i + 1, amount);
            }

            memo[i][amount] = numberOfWays(i, amount - coins[i]) + numberOfWays(i + 1, amount);
            return memo[i][amount];
        }

        public int change(int amount, int[] coins) {
            this.coins = coins;
            memo = new int[coins.length][amount + 1];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return numberOfWays(0, amount);
        }

    }

    public static Stream<CoinChangeII> implementationsSource() {
        return Stream.of(new TopDown());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test_3_1_2(CoinChangeII impl) {
        assertEquals(2, impl.change(3, new int[]{1, 2}));

    }
    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test_5_1_2_5(CoinChangeII impl) {
        assertEquals(4, impl.change(5, new int[]{1, 2, 5}));

    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(CoinChangeII impl) {
        assertEquals(0, impl.change(3, new int[]{2}));
        assertEquals(1, impl.change(3, new int[]{3}));
    }
}
