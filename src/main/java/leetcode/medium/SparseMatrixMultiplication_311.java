package leetcode.medium;

import helpers.TestHelper;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/sparse-matrix-multiplication/?envType=study-plan-v2&envId=premium-algo-100
 */
public class SparseMatrixMultiplication_311 {
    static class SparseMatrix {
        public ArrayList<Integer> values = new ArrayList<>();
        public ArrayList<Integer> colIndex = new ArrayList<>();
        public ArrayList<Integer> rowIndex = new ArrayList<>();

        static SparseMatrix asScarceRow(int[][] matrix){
            int rows = matrix.length;
            int cols = matrix[0].length;
            SparseMatrix sm = new SparseMatrix();
            sm.rowIndex.add(0);

            for (int row = 0; row < rows; ++row) {
                for (int col = 0; col < cols; ++col) {
                    if (matrix[row][col] != 0) {
                        sm.values.add(matrix[row][col]);
                        sm.colIndex.add(col);
                    }
                }
                sm.rowIndex.add(sm.values.size());
            }
            return sm;
        }

        // Compressed Sparse Row
        public static SparseMatrix asScarceColumn(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            SparseMatrix sm = new SparseMatrix();
            sm.colIndex.add(0);

            for (int col = 0; col < cols; ++col) {
                for (int row = 0; row < rows; ++row) {
                    if (matrix[row][col] != 0) {
                        sm.values.add(matrix[row][col]);
                        sm.rowIndex.add(row);
                    }
                }
                sm.colIndex.add(sm.values.size());
            }
            return sm;
        }
    }

    public int[][] multiply(int[][] mat1, int[][] mat2) {
        SparseMatrix A = SparseMatrix.asScarceRow(mat1);
        SparseMatrix B = SparseMatrix.asScarceColumn(mat2);

        int[][] ans = new int[mat1.length][mat2[0].length];

        for (int row = 0; row < ans.length; ++row) {
            for (int col = 0; col < ans[0].length; ++col) {

                // Row element range indices
                int matrixOneRowStart = A.rowIndex.get(row);
                int matrixOneRowEnd = A.rowIndex.get(row + 1);

                // Column element range indices
                int matrixTwoColStart = B.colIndex.get(col);
                int matrixTwoColEnd = B.colIndex.get(col + 1);

                // Iterate over both row and column.
                while (matrixOneRowStart < matrixOneRowEnd && matrixTwoColStart < matrixTwoColEnd) {
                    if (A.colIndex.get(matrixOneRowStart) < B.rowIndex.get(matrixTwoColStart)) {
                        matrixOneRowStart++;
                    } else if (A.colIndex.get(matrixOneRowStart) > B.rowIndex.get(matrixTwoColStart)) {
                        matrixTwoColStart++;
                    } else {
                        // Row index and col index are same so we can multiply these elements.
                        ans[row][col] += A.values.get(matrixOneRowStart) * B.values.get(matrixTwoColStart);
                        matrixOneRowStart++;
                        matrixTwoColStart++;
                    }
                }
            }
        }

        return ans;
    }

    public int[][] usual_multiply(int[][] mat1, int[][] mat2) {
        // C = rows from A by columns from B
        // C[i][j]=A[i][1]⋅B[1][j]+A[i][2]⋅B[2][j]+⋯+A[i][n]⋅B[n][j]

        int rowsCount = mat1.length;
        int columnCount = mat2[0].length;
        int xCount = mat1[0].length;
        int[][] out = new int[rowsCount][columnCount];
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                int sum = 0;
                for (int k = 0; k < xCount; k++) {
                    sum += mat1[i][k] * mat2[k][j];
                }
                out[i][j] = sum;
            }
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        String expected = TestHelper.twoDArrayToString(new int[][]{{7, 0, 0}, {-7, 0, 3}});
        String actual = TestHelper.twoDArrayToString(multiply(
                new int[][]{{1, 0, 0}, {-1, 0, 3}},
                new int[][]{{7, 0, 0}, {0, 0, 0}, {0, 0, 1}})
        );
        assertEquals(expected, actual);
    }
}
