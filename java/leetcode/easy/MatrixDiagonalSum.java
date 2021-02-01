package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/matrix-diagonal-sum/
 */
public class MatrixDiagonalSum {
    public int diagonalSum(int[][] a) {
        int n = a.length;
        int out = 0;
        for (int i = 0; i < n; i++) {
            out += a[i][i];
            out += a[i][n - 1 - i];
        }
        if (a.length % 2 == 1) {
            out -= a[n / 2][n / 2];
        }

        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(32,
                diagonalSum(new int[][]{
                        {1, 2, 3, 1},
                        {4, 5, 6, 1},
                        {7, 8, 9, 1},
                        {1, 1, 1, 1}
                }));
        Assertions.assertEquals(25,
                diagonalSum(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}}));
    }
}
