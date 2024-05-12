package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumFallingPathSum_931 {
    interface MinimumFallingPathSum {
        int minFallingPathSum(int[][] matrix);
    }

    static class MinimumFallingPathSumImpl implements MinimumFallingPathSum {
        @Override
        public int minFallingPathSum(int[][] matrix) {
            int h = matrix.length;
            int w = matrix[0].length;
            int[][] dp = new int[h][w];
            dp[0] = matrix[0];
            for (int row = 1; row < h; row++) {
                int[] prevRow = dp[row - 1];
                for (int col = 0; col < w; col++) {
                    int value = matrix[row][col];
                    int fromLeft = (col > 0) ? prevRow[col - 1] : Integer.MAX_VALUE;
                    int fromRight = (col < w - 1) ? prevRow[col + 1] : Integer.MAX_VALUE;
                    int fromTop = prevRow[col];
                    int minFromPrevRow = Math.min(Math.min(fromLeft, fromRight), fromTop);
                    dp[row][col] = minFromPrevRow + value;
                }
            }
            int out = dp[h - 1][0];
            for (int i = 1; i < w; i++) {
                out = Math.min(out, dp[h - 1][i]);
            }
            return out;
        }
    }

    public static Stream<MinimumFallingPathSum> implementationsSource() {
        return Stream.of(new MinimumFallingPathSumImpl());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(MinimumFallingPathSum impl) {
        assertEquals(13, impl.minFallingPathSum(new int[][]{
                {2, 1, 3}, {6, 5, 4}, {7, 8, 9}
        }));
        assertEquals(-59, impl.minFallingPathSum(new int[][]{
                {-19, 57}, {-40, -5}
        }));
    }
}
