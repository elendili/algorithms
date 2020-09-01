package leetcode.top_interview_questions.medium.arraysAndStrings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/set-matrix-zeroes/solution/
Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.

Follow up:
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?


Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

 */
public class SetZeroesInMatrix {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || (matrix.length == 1 && matrix[0].length == 1)) {
            return;
        }
        final int H = matrix.length;
        final int W = matrix[0].length;
        boolean firstRowToZero = false;
        boolean firstColumnToZero = false;
        //check matrix and use first row + column as storage
        for (int row = 0; row < H; row++) {
            for (int col = 0; col < W; col++) {
                if (matrix[row][col] == 0) {
                    if (row == 0) {
                        firstRowToZero = true;
                    }
                    if (col == 0) {
                        firstColumnToZero = true;
                    }
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }
        //zeroing matrix
        for (int row = 1; row < H; row++) {
            for (int col = 1; col < W; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        // zero first row
        if (firstRowToZero) {
            Arrays.fill(matrix[0], 0);
        }
        // zero first column
        if (firstColumnToZero) {
            for (int row = 0; row < H; row++) {
                matrix[row][0] = 0;
            }
        }
    }

    @Test
    public void test() {
//        check("0", new int[][]{{0}});
//        check("1", new int[][]{{1}});
//        check("1,0,1\n0,0,0\n1,0,1", new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
//        check("0,0,0,0\n0,4,5,0\n0,3,1,0", new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
//        check("1,0\n3,0\n0,0", new int[][]{{1, 2}, {3, 4}, {5, 0}});
//        check("1\n2\n3", new int[][]{{1}, {2}, {3}});
        check("0\n0\n0", new int[][]{{1}, {2}, {0}});
        check("1,2,3", new int[][]{{1, 2, 3}});
        check("0,0,0", new int[][]{{1, 2, 0}});
    }

    void check(String expected, int[][] a) {
        setZeroes(a);
        String actual = Arrays.stream(a).map(aa ->
                Arrays.stream(aa).mapToObj(i -> "" + i).collect(Collectors.joining(","))
        ).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, actual);
    }
}
