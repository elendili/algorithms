package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOfDiceRollsWithTargetSum_1155 {
    interface NumberOfDiceRollsWithTargetSum {
        int numRollsToTarget(int n, int k, int target);
    }

    static class TopDown implements NumberOfDiceRollsWithTargetSum {
        static final int mod = 1_000_000_000 + 7;
        private Integer[][] dp;
        private int k;

        public int numRollsToTarget(int n, int k, int target) {
            this.k = k;
            this.dp = new Integer[target + 1][n + 1];
            return recursive(n, target);
        }

        int recursive(int n, int target) {
            if (target < 0 || target > n * k) {
                return 0;
            }
            if (n == 0) {
                if (target == 0) {
                    return 1;
                }
                return 0;
            }
            if (dp[target][n] == null) {
                int out = 0;
                for (int i = 1; i <= k; i++) {
                    int v = recursive(n - 1, target - i);
                    out = (out + v) % mod;
                }
                dp[target][n] = out;
            }
            return dp[target][n];
        }
    }

    public static Stream<NumberOfDiceRollsWithTargetSum> implementationsSource() {
        return Stream.of(new TopDown());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void testSimple(NumberOfDiceRollsWithTargetSum impl) {
        assertEquals(1, impl.numRollsToTarget(1, 6, 3));
        assertEquals(6, impl.numRollsToTarget(2, 6, 7));
        assertEquals(1, impl.numRollsToTarget(2, 6, 12));
        assertEquals(0, impl.numRollsToTarget(2, 6, 1));
        assertEquals(1, impl.numRollsToTarget(2, 6, 2));
        assertEquals(2, impl.numRollsToTarget(2, 6, 3));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void testBig(NumberOfDiceRollsWithTargetSum impl) {
        assertEquals(222616187, impl.numRollsToTarget(30, 30, 500));
    }
}
