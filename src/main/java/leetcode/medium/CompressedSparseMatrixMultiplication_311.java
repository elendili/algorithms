package leetcode.medium;

import helpers.TestHelper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/sparse-matrix-multiplication/?envType=study-plan-v2&envId=premium-algo-100
 */
public class CompressedSparseMatrixMultiplication_311 {
    static class CompressedSparseMatrix {
        // sequence - is a specific row or column in matrix
        private int valuesCount = 0;
        private List<Integer> values;
        private List<Integer> valueIndexInSequence;
        private List<Integer> sequencesStarts;

        static CompressedSparseMatrix asScarceRow(int[][] matrix){
            int rows = matrix.length;
            int cols = matrix[0].length;
            CompressedSparseMatrix sm = new CompressedSparseMatrix();
            int supposedSize = rows*cols/2;
            sm.values = new ArrayList<>(supposedSize);
            sm.valueIndexInSequence = new ArrayList<>(supposedSize);
            sm.sequencesStarts = new ArrayList<>(supposedSize);
            sm.sequencesStarts.add(0);

            for (int row = 0; row < rows; ++row) {
                for (int col = 0; col < cols; ++col) {
                    addVal(sm, col, matrix[row][col]);
                }
                sm.sequencesStarts.add(sm.valuesCount);
            }
            return sm;
        }

        // Compressed Sparse Row
        static CompressedSparseMatrix asScarceColumn(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            CompressedSparseMatrix sm = new CompressedSparseMatrix();
            int supposedSize = rows*cols/2;
            sm.values = new ArrayList<>(supposedSize);
            sm.valueIndexInSequence = new ArrayList<>(supposedSize);
            sm.sequencesStarts = new ArrayList<>(supposedSize);
            sm.sequencesStarts.add(0);

            for (int col = 0; col < cols; ++col) {
                for (int row = 0; row < rows; ++row) {
                    addVal(sm, row, matrix[row][col]);
                }
                sm.sequencesStarts.add(sm.valuesCount);
            }
            return sm;
        }
        private static void addVal(CompressedSparseMatrix sm, int indexInSeq, int val) {
            if (val != 0) {
                sm.values.add(val);
                sm.valueIndexInSequence.add(indexInSeq);
                sm.valuesCount++;
            }
        }
        public static int[][] multiply(int[][] mat1, int[][] mat2) {
            CompressedSparseMatrix A = CompressedSparseMatrix.asScarceRow(mat1);
            CompressedSparseMatrix B = CompressedSparseMatrix.asScarceColumn(mat2);
            int outRows = mat1.length;
            int outColumns = mat2[0].length;
            int[][] ans = new int[outRows][outColumns];

            for (int row = 0; row < outRows; ++row) {
                for (int col = 0; col < outColumns; ++col) {

                    // Row element range indices
                    int aRowStart = A.sequencesStarts.get(row);
                    int aRowEnd = A.sequencesStarts.get(row + 1);

                    // Column element range indices
                    int bColStart = B.sequencesStarts.get(col);
                    int bColEnd = B.sequencesStarts.get(col + 1);

                    // Iterate over both row and column.
                    while (aRowStart < aRowEnd && bColStart < bColEnd) {
                        int indexInRow = A.valueIndexInSequence.get(aRowStart);
                        int indexInCol = B.valueIndexInSequence.get(bColStart);
                        if (indexInRow < indexInCol) {
                            aRowStart++;
                        } else if (indexInRow > indexInCol) {
                            bColStart++;
                        } else {
                            // Row index and col index are same so we can multiply these elements.
                            ans[row][col] += A.values.get(aRowStart) * B.values.get(bColStart);
                            aRowStart++;
                            bColStart++;
                        }
                    }
                }
            }

            return ans;
        }
    }


    public int[][] multiply(int[][] mat1, int[][] mat2) {
        return CompressedSparseMatrix.multiply(mat1, mat2);
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
