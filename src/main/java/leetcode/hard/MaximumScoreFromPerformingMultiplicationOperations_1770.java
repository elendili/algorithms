package leetcode.hard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumScoreFromPerformingMultiplicationOperations_1770 {
    static interface MaximumScoreCalc {
        int maximumScore(int[] nums, int[] multipliers);
    }

    static class BottomUp implements MaximumScoreCalc {
        public int maximumScore(int[] nums, int[] multipliers) {
            int n = nums.length;
            int m = multipliers.length;
            int[][] dp = new int[m + 1][m + 1];

            for (int i = m - 1; i >= 0; i--) {
                for (int left = i; left >= 0; left--) {
                    int mult = multipliers[i];
                    int right = n - 1 - (i - left);
                    int fromLeft = mult * nums[left] + dp[i + 1][left + 1];
                    int fromRight = mult * nums[right] + dp[i + 1][left];
                    dp[i][left] = Math.max(fromLeft, fromRight);
                }
            }

            return dp[0][0];
        }

    }

    static class TopDown implements MaximumScoreCalc {
        private int[] multipliers;
        private int[] nums;
        private int[][] memo;
        private int n, m;

        public int maximumScore(int[] nums, int[] multipliers) {
            this.nums = nums;
            this.multipliers = multipliers;
            this.m = multipliers.length;
            this.n = nums.length;
            this.memo = new int[m][m];
            int out = dp(0, 0);
            return out;
        }

        private int dp(int i, int leftI) {
            if (i == m) {
                return 0;
            }
            int mult = multipliers[i];
            int rightI = n - 1 - (i - leftI);

            if (memo[i][leftI] == 0) {
                int fromLeft = mult * nums[leftI] + dp(i + 1, leftI + 1);
                int fromRight = mult * nums[rightI] + dp(i + 1, leftI);
                memo[i][leftI] = Math.max(fromLeft, fromRight);
            }
            int out = memo[i][leftI];
            return out;
        }

    }

    static Stream<MaximumScoreCalc> impls() {
        return Stream.of(
                new BottomUp(),
                new TopDown());
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("impls")
    public void paramTest(MaximumScoreCalc calc) {
        assertEquals(14, calc.maximumScore(new int[]{1, 2, 3}, new int[]{3, 2, 1}));
        assertEquals(102, calc.maximumScore(new int[]{-5, -3, -3, -2, 7, 1}, new int[]{-10, -5, 3, 4, 6}));
    }
}
