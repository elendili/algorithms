package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumPathSum_64 {
    interface MinimumPathSum {
        int minPathSum(int[][] grid);
    }

    static class MinimumPathSumImpl implements MinimumPathSum {
        @Override
        public int minPathSum(int[][] grid) {
            int h = grid.length;
            int w = grid[0].length;
            int[][] dp = new int[h][w];
            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    int fromMin;
                    if (row > 0 && col > 0) {
                        fromMin = Math.min(dp[row - 1][col], dp[row][col - 1]);
                    } else if (row > 0) {
                        fromMin = dp[row - 1][col];
                    } else if (col > 0) {
                        fromMin = dp[row][col - 1];
                    } else {
                        fromMin = 0;
                    }

                    dp[row][col] = fromMin + grid[row][col];
                }
            }
            return dp[h - 1][w - 1];
        }

    }

    public static Stream<MinimumPathSum> implementationsSource() {
        return Stream.of(new MinimumPathSumImpl());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(MinimumPathSum impl) {
        assertEquals(7, impl.minPathSum(new int[][]{
                {1, 3, 1}, {1, 5, 1}, {4, 2, 1}
        }));
        assertEquals(12, impl.minPathSum(new int[][]{
                {1, 2, 3}, {4, 5, 6}
        }));
        assertEquals(6, impl.minPathSum(new int[][]{
                {1, 2, 3}
        }));
        assertEquals(6, impl.minPathSum(new int[][]{
                {1}, {2}, {3}
        }));
    }
}
