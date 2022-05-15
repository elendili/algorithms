package leetcode.top_interview_questions.hard.arrayAndStrings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-hard/116/array-and-strings/828/
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] a) {
        List<Integer> out = new ArrayList<>();
        if (a != null && a.length > 0 && a[0] != null && a[0].length > 0) {
            int rowsCount = a.length;
            int columnCount = a[0].length;
            int outSize = rowsCount * columnCount;
            int circlesCount = Math.min(rowsCount, columnCount) / 2;
            for (int circle = 0; circle <= circlesCount && out.size() < outSize; circle++) {
                // top side
                for (int i = circle; i < columnCount - circle; i++) {
                    out.add(a[circle][i]);
                }
                // right side
                int rightColumn = columnCount - circle - 1;
                for (int i = circle + 1; i < rowsCount - circle; i++) {
                    out.add(a[i][rightColumn]);
                }
                // bottom side
                int bottomRow = rowsCount - circle - 1;
                if (bottomRow != circle) {
                    for (int i = columnCount - circle - 2; i > circle - 1; i--) {
                        out.add(a[bottomRow][i]);
                    }
                }
                // left side
                if (rightColumn != circle) {
                    for (int i = rowsCount - circle - 2; i > circle; i--) {
                        out.add(a[i][circle]);
                    }
                }
            }
        }
        return out;
    }

    @Test
    public void invalid() {
        check(Arrays.asList(), null);
        check(Arrays.asList(), new int[][]{});
        check(Arrays.asList(), new int[][]{null});
        check(Arrays.asList(), new int[][]{{}, null});
    }

    @Test
    public void zeroRow() {
        check(Arrays.asList(), new int[][]{{}});
    }

    @Test
    public void zeroColumn() {
        check(Arrays.asList(), new int[][]{{}, {}});
    }

    @Test
    public void oneRow() {
        check(Arrays.asList(9), new int[][]{{9}});
        check(Arrays.asList(1, 2), new int[][]{{1, 2}});
    }

    @Test
    public void oneColumn() {
        check(Arrays.asList(1, 2), new int[][]{{1}, {2}});
    }

    @Test
    public void square2() {
        check(Arrays.asList(1, 2, 4, 3), new int[][]{{1, 2}, {3, 4}});
    }

    @Test
    public void rectangle2X3() {
        check(Arrays.asList(1, 2, 3, 6, 5, 4), new int[][]{{1, 2, 3}, {4, 5, 6}});
    }

    @Test
    public void rectangle3X2() {
        check(Arrays.asList(1, 2, 4, 6, 5, 3), new int[][]{{1, 2}, {3, 4}, {5, 6}});
    }

    @Test
    public void rectangle5X2() {
        check(Arrays.asList(1, 2, 4, 6, 8, 10, 9, 7, 5, 3), new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}});
    }

    @Test
    public void rectangle2x5() {
        check(Arrays.asList(1, 2, 3, 4, 5, 10, 9, 8, 7, 6), new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}});
    }

    @Test
    public void rectangle2x4() {
        check(Arrays.asList(1, 2, 3, 4, 8, 7, 6, 5), new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
    }

    @Test
    public void rectangle4x2() {
        check(Arrays.asList(1, 2, 4, 6, 8, 7, 5, 3), new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}});
    }

    @Test
    public void rectangle5X3() {
        check(Arrays.asList(1, 2, 3, 6, 9, 12, 15, 14, 13, 10, 7, 4, 5, 8, 11), new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}});
    }

    @Test
    public void rectangle3X5() {
        check(Arrays.asList(1, 2, 3, 4, 5, 10, 15, 14, 13, 12, 11, 6, 7, 8, 9), new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}});
    }

    @Test
    public void square3() {
        check(Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5),
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                });
    }

    @Test
    public void square4() {
        check(Arrays.asList(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10),
                new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16},
                });
    }

    @Test
    public void square5() {
        check(Arrays.asList(1, 2, 3, 4, 5, 10, 15, 20, 25, 24, 23, 22, 21, 16, 11, 6, 7, 8, 9, 14, 19, 18, 17, 12, 13),
                new int[][]{
                        {1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10},
                        {11, 12, 13, 14, 15},
                        {16, 17, 18, 19, 20},
                        {21, 22, 23, 24, 25},
                });
    }

    @Test
    public void test() {
        check(Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
                new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12}
                });

    }

    private void check(List<Integer> expected, int[][] input) {
        Assertions.assertEquals(expected.toString(), spiralOrder(input).toString());
    }
}
