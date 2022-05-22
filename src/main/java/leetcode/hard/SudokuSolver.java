package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/sudoku-solver/
 * <p>
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0);
    }

    public boolean solveSudoku(char[][] board, int index) {
        if (index >= 81) {
            return true;
        }
        int y = index / 9;
        int x = index - y * 9;
        char c = board[y][x];
        if (c == '.') {
            for (char k = '1'; k <= '9'; k++) {
                if (checkDigit(board, y, x, k)) {
                    board[y][x] = k;
                    if (solveSudoku(board, index + 1)) {
                        return true;
                    }
                }
            }
            board[y][x] = '.';
            return false;
        }
        return solveSudoku(board, index + 1);
    }

    boolean checkDigit(char[][] board, int y, int x, char k) {
        int boxX = (x / 3) * 3; // 0 1 2
        int boxY = (y / 3) * 3; // 0 1 2
        // check line and check column
        for (int i = 0; i < 9; i++) {
            if (k == board[i][x] || k == board[y][i]) {
                return false;
            }
        }
        // check sub-box
        for (int dx = 0; dx < 3; dx++) {
            for (int dy = 0; dy < 3; dy++) {
                if (k == board[boxY + dy][boxX + dx]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        char[][] inputToBeProcessed =
                new char[][]{
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };
        char[][] expected = new char[][]{
                {'5', '3', '4', '6', '7', '8', '9', '1', '2'},
                {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
                {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
                {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
                {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
                {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
                {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
                {'3', '4', '5', '2', '8', '6', '1', '7', '9'}
        };
        solveSudoku(inputToBeProcessed);
        String expectedString = Arrays.stream(expected).map(Arrays::toString).collect(Collectors.joining("\n"));
        String actualString = Arrays.stream(inputToBeProcessed).map(Arrays::toString).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedString, actualString);
    }
}
