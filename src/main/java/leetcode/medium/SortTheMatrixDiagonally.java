package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static helpers.TestHelper.twoDArrayToString;

/**
 * https://leetcode.com/problems/sort-the-matrix-diagonally/description/
 */
public class SortTheMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        int h = mat.length;
        int w = mat[0].length;
        if (h == 1 || w == 1) {
            return mat;
        }
        int[][] output = new int[h][w];
        List<Integer> diagonal = new ArrayList<>();
        for (int sy = h - 1; sy > -1; sy--) {// diagonal start  on first column
            diagonalSort(sy, 0, diagonal, mat, output);
        }
        for (int sx = 1; sx < w; sx++) {      // diagonal start  on first row
            diagonalSort(0, sx, diagonal, mat, output);
        }
        return output;
    }

    void diagonalSort(int sy, int sx, List<Integer> diagonal, int[][] input, int[][] output) {
        int x = sx;
        int y = sy;
        while (y < input.length && x < input[0].length) {
            diagonal.add(input[y][x]);
            y++;
            x++;
        }
        Collections.sort(diagonal);
        x = sx;
        y = sy;
        for (int i = 0; i < diagonal.size(); i++) {
            output[y][x] = diagonal.get(i);
            x++;
            y++;
        }
        diagonal.clear();
    }

    @Test
    public void test0() {
        int[][] input = new int[][]{{3, 1, 1}};
        Assertions.assertEquals("[3, 1, 1]",
                twoDArrayToString(diagonalSort(input)));
        input = new int[][]{{3}, {1}, {1}};
        Assertions.assertEquals("[3]\n[1]\n[1]",
                twoDArrayToString(diagonalSort(input)));
    }

    @Test
    public void test() {
        int[][] input = new int[][]{
                {3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}
        };
        Assertions.assertEquals("[1, 1, 1, 1]\n[1, 2, 2, 2]\n[1, 2, 3, 3]",
                twoDArrayToString(diagonalSort(input)));
    }

    @Test
    public void test2() {
        int[][] input = new int[][]{
                {11, 25, 66, 1, 69, 7},
                {23, 55, 17, 45, 15, 52},
                {75, 31, 36, 44, 58, 8},
                {22, 27, 33, 25, 68, 4},
                {84, 28, 14, 11, 5, 50}
        };
        Assertions.assertEquals(
                "[5, 17, 4, 1, 52, 7]\n" +
                        "[11, 11, 25, 45, 8, 69]\n" +
                        "[14, 23, 25, 44, 58, 15]\n" +
                        "[22, 27, 31, 36, 50, 66]\n" +
                        "[84, 28, 75, 33, 55, 68]",
                twoDArrayToString(diagonalSort(input)));
    }
}
