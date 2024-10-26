package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/submissions/1427351255/?envType=study-plan-v2&envId=top-interview-150
 */
public class SearchA2DMatrix_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowCount = matrix.length;
        if (rowCount == 0 || matrix[0].length == 0) {
            return false;
        }
        int minRow = 0;
        int maxRow = rowCount - 1;
        while (minRow < maxRow) {
            int midRow = (maxRow + minRow) / 2;
            int midRowStartV = matrix[midRow][0];
            int midRowEndV = matrix[midRow][matrix[midRow].length - 1];
            if (midRowStartV == target || midRowEndV == target) {
                return true;
            } else if (midRowStartV > target) {
                // exclude bottom rows
                maxRow = midRow - 1;
            } else if (midRowEndV < target) {
                // restrict search area from top
                minRow = midRow + 1;
            } else {
                // restrict search area to one mid row
                //  midRowStartV<=target && midRowEndV>=target
                minRow=midRow;
                break;
            }
        }
        return searchInRow(matrix[minRow], target);
    }

    boolean searchInRow(int[] row, int target) {
        int l = 0;
        int r = row.length;
        while (l < r) {
            int mid = (l + r) / 2;
            int v = row[mid];
            if (v == target) {
                return true;
            } else if (v < target) {
                // restrict search from left
                l = mid + 1;
            } else {
                // restrict search from right
                r = mid;
            }
        }
        return false;
    }

    @org.junit.jupiter.api.Test
    public void testHorizontal() {
        int[][] input = new int[][]{
                {1, 3, 5, 7},
        };
        assertFalse(searchMatrix(input, 0));
        assertTrue(searchMatrix(input, 1));
        assertTrue(searchMatrix(input, 3));
        assertTrue(searchMatrix(input, 7));
        assertFalse(searchMatrix(input, 8));
    }

    @org.junit.jupiter.api.Test
    public void testVertical() {
        int[][] input = new int[][]{
                {1}, {3}, {5}, {7},
        };
        assertFalse(searchMatrix(input, 0));
        assertTrue(searchMatrix(input, 1));
        assertTrue(searchMatrix(input, 3));
        assertTrue(searchMatrix(input, 7));
        assertFalse(searchMatrix(input, 8));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        int[][] input = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        assertTrue(searchMatrix(input, 3));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        int[][] input = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        assertFalse(searchMatrix(input, 4));
        assertFalse(searchMatrix(input, 13));
        assertFalse(searchMatrix(input, 32));
        assertFalse(searchMatrix(input, 61));
        assertFalse(searchMatrix(input, 0));
        assertFalse(searchMatrix(input, 8));
        assertFalse(searchMatrix(input, 21));

        assertTrue(searchMatrix(input, 1));
        assertTrue(searchMatrix(input, 60));
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        int[][] input = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}

        };
        assertTrue(searchMatrix(input, 11));
    }
}
