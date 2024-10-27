package leetcode.medium;

import static helpers.TestHelper.twoDArrayToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/surrounded-regions
 */
public class SurroundedRegions_130 {
    private char[][] board;
    boolean[][] visited;

    public void solve(char[][] board) {
        this.board = board;
        // iterate over board boundaries and mark all 'O' regions and connected as visited
        // then on everything unvisited replace 'O' with 'X'

        this.visited = new boolean[board.length][board[0].length];
        // left, right boundary
        for (int row = 0; row < board.length; row++) {
            discover(row, 0);
            discover(row, board[0].length - 1);
        }
        // top, bottom boundary
        for (int col = 0; col < board[0].length; col++) {
            discover(0, col);
            discover(board.length - 1, col);
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (!visited[row][col]) {
                    board[row][col] = 'X';
                }
            }
        }
    }

    void discover(int row, int column) {
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length || visited[row][column]) {
            return;
        }
        visited[row][column] = true;
        if (board[row][column] == 'O') {
            discover(row - 1, column);
            discover(row + 1, column);
            discover(row, column - 1);
            discover(row, column + 1);
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        char[][] input = new char[][]
                {{'X', 'X', 'X', 'X'},
                        {'X', 'O', 'O', 'X'},
                        {'X', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'X'}};
        solve(input);
        assertEquals(
                "[X, X, X, X]\n" +
                        "[X, X, X, X]\n" +
                        "[X, X, X, X]\n" +
                        "[X, O, X, X]",
                twoDArrayToString(input));
    }
    @org.junit.jupiter.api.Test
    public void test2() {
        char[][] input = new char[][]
                        {{'X', 'X', 'O', 'X'},
                        {'O', 'X', 'X', 'O'},
                        {'X', 'X', 'X', 'O'},
                        {'X', 'O', 'O', 'X'}};
        solve(input);
        assertEquals(
                "[X, X, O, X]\n" +
                        "[O, X, X, O]\n" +
                        "[X, X, X, O]\n" +
                        "[X, O, O, X]",
                twoDArrayToString(input));
    }
    @org.junit.jupiter.api.Test
    public void test3() {
        char[][] input = new char[][]
                        {{'O', 'X', 'X', 'O'},
                        {'X', 'O', 'O', 'X'},
                        {'X', 'O', 'O', 'X'},
                        {'O', 'X', 'X', 'O'}};
        solve(input);
        assertEquals(
                "[O, X, X, O]\n" +
                        "[X, X, X, X]\n" +
                        "[X, X, X, X]\n" +
                        "[O, X, X, O]",
                twoDArrayToString(input));
    }
}
