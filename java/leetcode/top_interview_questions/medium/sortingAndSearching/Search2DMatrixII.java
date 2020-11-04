package leetcode.top_interview_questions.medium.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/110/sorting-and-searching/806/
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 */
public class Search2DMatrixII {
    public boolean searchMatrix(final int[][] matrix, final int target) {
        if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
            int col = matrix[0].length - 1;
            int row = 0;
            while (col > -1 && row < matrix.length) {
                final int v = matrix[row][col];
                if (v == target) {
                    return true;
                } else if (v > target) {
                    col--; // exclude column (right) from further search
                } else {
                    row++; // exclude row  (above) from further search
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[][] input = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        Assertions.assertTrue(searchMatrix(input, 15));
        Assertions.assertTrue(searchMatrix(input, 1));
        Assertions.assertTrue(searchMatrix(input, 5));
        Assertions.assertTrue(searchMatrix(input, 18));
        Assertions.assertTrue(searchMatrix(input, 30));

        Assertions.assertFalse(searchMatrix(input, 0));
        Assertions.assertFalse(searchMatrix(input, 20));
        Assertions.assertFalse(searchMatrix(input, 31));
    }
}
