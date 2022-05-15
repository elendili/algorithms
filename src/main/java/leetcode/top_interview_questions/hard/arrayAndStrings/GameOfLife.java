package leetcode.top_interview_questions.hard.arrayAndStrings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-hard/116/array-and-strings/831/
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised
by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state.
 The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time:
You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array.
In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */
public class GameOfLife {

    /*
Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     */
    public void gameOfLife(int[][] board) {
        /*
        before after
        0 0 => 0
        0 1 => -1 // to live
        1 0 => -2 // to die
        1 1 => 1
         */
        // marking for death/life
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int v = board[i][j];
                int neighbours = count(board, i, j) - v;
                if (v > 0 && (neighbours < 2 || neighbours > 3)) {
                    board[i][j] = -2; // to die
                }
                if (v < 1 && neighbours == 3) {
                    board[i][j] = -1; // to live
                }
            }
        }
        // applying marks
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int v = board[i][j];
                if (v == -1) {
                    board[i][j] = 1; // born
                }
                if (v == -2) {
                    board[i][j] = 0; // die
                }
            }
        }
    }

    int count(int[][] a, int x, int y) {
        int out = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i > -1 && i < a.length && j > -1 && j < a[i].length) {
                    int v = a[i][j];
                    out += (v == -2 || v == 1) ? 1 : 0;
                }
            }
        }
        return out;
    }

    @Test
    public void simple() {
        asserterTest(new int[][]{{}}, new int[][]{{}});
        asserterTest(new int[][]{{0}}, new int[][]{{0}});
        asserterTest(new int[][]{{0}}, new int[][]{{1}});
        asserterTest(new int[][]{{0, 1, 0}}, new int[][]{{1, 1, 1}});
        asserterTest(new int[][]{{0, 1, 1, 0}}, new int[][]{{1, 1, 1, 1}});
        asserterTest(new int[][]{{1, 1}, {1, 1}}, new int[][]{{1, 1}, {1, 1}});
        asserterTest(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        asserterTest(new int[][]{{0, 1, 0}, {0, 1, 0}}, new int[][]{{1, 1, 1}, {0, 0, 0}});
    }

    @Test
    public void fromTask() {
        asserterTest(new int[][]
                        {{0, 0, 0},
                                {1, 0, 1},
                                {0, 1, 1},
                                {0, 1, 0}},
                new int[][]
                        {{0, 1, 0},
                                {0, 0, 1},
                                {1, 1, 1},
                                {0, 0, 0}}
        );
    }

    public void asserterTest(int[][] expected, int[][] input) {
        String expString = Arrays.stream(expected).map(a -> Arrays.stream(a).mapToObj(i -> i + "")
                .collect(Collectors.joining(","))).collect(Collectors.joining("\n"));
        gameOfLife(input);
        String actString = Arrays.stream(input).map(a -> Arrays.stream(a).mapToObj(i -> i + "")
                .collect(Collectors.joining(","))).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expString, actString);
    }
}
