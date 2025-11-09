package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
https://leetcode.com/problems/design-tic-tac-toe/?envType=study-plan-v2&envId=premium-algo-100
Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Implement the TicTacToe class:

TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move, and the two players alternate in making moves. Return
0 if there is no winner after the move,
1 if player 1 is the winner after the move, or
2 if player 2 is the winner after the move.
 */
public class TicTacToeDesign {
    class TicTacToe {
        final int[] rows;
        final int[] columns;
        int diagonal;
        int reverseDiagonal;
        final int n;

        public TicTacToe(int n) {
            this.n = n;
            rows = new int[n];
            columns = new int[n];
            diagonal = 0;
            reverseDiagonal = 0;
        }

        public int move(int row, int col, int player) {
            int toAdd = player == 1 ? 1 : -1;
            rows[row] += toAdd;
            if (Math.abs(rows[row]) == n) return player;
            columns[col] += toAdd;
            if (Math.abs(columns[col]) == n) return player;
            if (row == col) { // top-left to bottom-right diagonal
                diagonal += toAdd;
                if (Math.abs(diagonal) == n) return player;
            }
            if (row + col == n - 1) { // top-right to bottom-left diagonal
                reverseDiagonal  += toAdd;
                if (Math.abs(reverseDiagonal) == n) return player;
            }
            return 0;
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TicTacToe ticTacToe = new TicTacToe(3);
        assertEquals(0, ticTacToe.move(0, 0, 1));
        assertEquals(0, ticTacToe.move(0, 2, 2));
        assertEquals(0, ticTacToe.move(2, 2, 1));
        assertEquals(0, ticTacToe.move(1, 1, 2));
        assertEquals(0, ticTacToe.move(2, 0, 1));
        assertEquals(0, ticTacToe.move(1, 0, 2));
        assertEquals(1, ticTacToe.move(2, 1, 1));
    }
}
