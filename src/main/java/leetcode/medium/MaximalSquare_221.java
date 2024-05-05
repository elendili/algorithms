package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/maximal-square
 */
public class MaximalSquare_221 {

    public int maximalSquare(char[][] matrix) {
        // convert to int matrix or use
        int maxSide = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == '1') {
                    int val = 1;
                    if (row > 0 && col > 0) {
                        int up = matrix[row - 1][col] - '0';
                        int left = matrix[row][col - 1] - '0';
                        int upLeft = matrix[row - 1][col - 1] - '0';
                        int minOfNeighs = Math.min(upLeft, Math.min(up, left));
                        val = minOfNeighs + 1;
                    }
                    matrix[row][col] = (char) (val + '0');
                    maxSide = Math.max(maxSide, val);
                }
            }
        }
        int square = maxSide * maxSide;
        return square;
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        char[][] input = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        assertEquals(4, maximalSquare(input));
    }

    @org.junit.jupiter.api.Test
    public void test9() {
        char[][] input = new char[][]{
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        assertEquals(9, maximalSquare(input));
    }

    @org.junit.jupiter.api.Test
    public void test16() {
        char[][] input = new char[][]{
                {'1', '0', '1', '0', '1'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'}
        };
        assertEquals(16, maximalSquare(input));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        char[][] input = new char[][]{{'0', '1'}, {'1', '0'}};
        assertEquals(1, maximalSquare(input));
    }

    @org.junit.jupiter.api.Test
    public void test0() {
        char[][] input = new char[][]{{'0'}};
        assertEquals(0, maximalSquare(input));
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        char[][] input = new char[][]{{'1'}};
        assertEquals(1, maximalSquare(input));
    }
}
