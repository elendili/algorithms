package leetcode.hard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/paint-house-ii
 */
public class PaintHouseII_265 {
    interface PaintHouseII {
        int minCostII(int[][] costs);
    }

    static class BottomUp implements PaintHouseII {
        @Override
        public int minCostII(int[][] costs) {
            // can be rewritten without DP array,
            // just search min in line and index of its, and second min
            int n = costs.length;
            int k = costs[0].length;
            int[][] dp = new int[n][k];
            for (int i = 0; i < n; i++) {
                System.arraycopy(costs[i], 0, dp[i], 0, k);
                if (i > 0) {
                    for (int j = 0; j < k; j++) { // colors of integer-house
                        int minCostOfPaintingOfPrevHouses = Integer.MAX_VALUE;
                        for (int c = 0; c < k; c++) { // color of (integer-1)-house
                            if (c != j) {
                                minCostOfPaintingOfPrevHouses = Math.min(minCostOfPaintingOfPrevHouses, dp[i - 1][c]);
                            }
                        }
                        dp[i][j] += minCostOfPaintingOfPrevHouses;
                    }
                }
            }
            // search overall min cost of painting of all houses
            int out = dp[n - 1][0];
            for (int i = 0; i < k; i++) {
                out = Math.min(out, dp[n - 1][i]);
            }
            return out;
        }

    }

    public static Stream<PaintHouseII> implementationsSource() {
        return Stream.of(new BottomUp());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(PaintHouseII impl) {
        assertEquals(5, impl.minCostII(new int[][]{
                {1, 5, 3}, {2, 9, 4}
        }));
        assertEquals(5, impl.minCostII(new int[][]{
                {1, 3}, {2, 4}
        }));
    }
}
