package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
public class RangeSumQuery2dImmutable {

    static class NumMatrix {

        private final int[][] dp;

        public NumMatrix(int[][] a) {
            if (a.length > 0 && a[0].length > 0) {
                this.dp = new int[a.length][a[0].length];
                for (int i = 0; i < a.length; i++) {
                    for (int j = 0; j < a[0].length; j++) {
                        int fromTop = i == 0 ? 0 : this.dp[i - 1][j];
                        int fromLeft = j == 0 ? 0 : this.dp[i][j - 1];
                        int subtr = i == 0 || j == 0 ? 0 : this.dp[i - 1][j - 1];
                        this.dp[i][j] += fromLeft + fromTop + a[i][j] - subtr;
                    }
                }
            } else {
                this.dp = null;
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (dp != null) {
                int bigSquare = dp[row2][col2];
                int verticalSubtract = col1 == 0 ? 0 : dp[row2][col1 - 1];
                int horizontalSubtract = row1 == 0 ? 0 : dp[row1 - 1][col2];
                int smallAddendum = row1 == 0 || col1 == 0 ? 0 : dp[row1 - 1][col1 - 1];
                int out = bigSquare - verticalSubtract - horizontalSubtract + smallAddendum;
                return out;
            } else {
                return 0;
            }
        }
    }

    @Test
    public void test() {
        NumMatrix nm = new NumMatrix(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        Assertions.assertEquals(8, nm.sumRegion(2, 1, 4, 3));
        Assertions.assertEquals(11, nm.sumRegion(1, 1, 2, 2));
        Assertions.assertEquals(12, nm.sumRegion(1, 2, 2, 4));
    }
}
