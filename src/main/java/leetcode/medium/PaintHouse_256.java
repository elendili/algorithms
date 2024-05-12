package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaintHouse_256 {
    interface PaintHouse {
        int minCost(int[][] costs);
    }

    static class PaintHouseImpl implements PaintHouse {
        @Override
        public int minCost(int[][] costs) {
            int n = costs.length;
            int[][] dp = new int[n][3];
            // base case is costs for first house
            // dp[i][0] = costs[i][0] + Math.min(dp[i-1][1],dp[i-1][2])
            for (int i = 0; i < n; i++) {
                System.arraycopy(costs[i], 0, dp[i], 0, 3);
                if (i > 0) {
                    int[] prevHousesCosts = dp[i - 1];
                    dp[i][0] += Math.min(prevHousesCosts[1], prevHousesCosts[2]);
                    dp[i][1] += Math.min(prevHousesCosts[0], prevHousesCosts[2]);
                    dp[i][2] += Math.min(prevHousesCosts[0], prevHousesCosts[1]);
                }
            }
            int[] costsForAllHouses = dp[n - 1];
            int outMin = Math.min(Math.min(costsForAllHouses[0], costsForAllHouses[1]), costsForAllHouses[2]);
            return outMin;
        }

    }

    public static Stream<PaintHouse> implementationsSource() {
        return Stream.of(new PaintHouseImpl());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(PaintHouse impl) {
        assertEquals(10, impl.minCost(new int[][]{
                {17, 2, 17}, {16, 16, 5}, {14, 3, 19}
        }));
        assertEquals(2, impl.minCost(new int[][]{
                {7, 6, 2}
        }));
    }
}
