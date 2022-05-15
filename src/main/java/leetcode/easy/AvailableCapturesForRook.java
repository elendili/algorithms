package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/available-captures-for-rook/

On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.  These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.

The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.

Return the number of pawns the rook can capture in one move.



Example 1:



Input: [['.','.','.','.','.','.','.','.'],['.','.','.','p','.','.','.','.'],['.','.','.','R','.','.','.','p'],['.','.','.','.','.','.','.','.'],['.','.','.','.','.','.','.','.'],['.','.','.','p','.','.','.','.'],['.','.','.','.','.','.','.','.'],['.','.','.','.','.','.','.','.']]
Output: 3
Explanation:
In this example the rook is able to capture all the pawns.
Example 2:



Input: [['.','.','.','.','.','.','.','.'],['.','p','p','p','p','p','.','.'],['.','p','p','B','p','p','.','.'],['.','p','B','R','B','p','.','.'],['.','p','p','B','p','p','.','.'],['.','p','p','p','p','p','.','.'],['.','.','.','.','.','.','.','.'],['.','.','.','.','.','.','.','.']]
Output: 0
Explanation:
Bishops are blocking the rook to capture any pawn.
Example 3:



Input: [['.','.','.','.','.','.','.','.'],['.','.','.','p','.','.','.','.'],['.','.','.','p','.','.','.','.'],['p','p','.','R','.','p','B','.'],['.','.','.','.','.','.','.','.'],['.','.','.','B','.','.','.','.'],['.','.','.','p','.','.','.','.'],['.','.','.','.','.','.','.','.']]
Output: 3
Explanation:
The rook can capture the pawns at positions b5, d6 and f5.


Note:

board.length == board[i].length == 8
board[i][j] is either 'R', '.', 'B', or 'p'
There is exactly one cell with board[i][j] == 'R'
 */
public class AvailableCapturesForRook {

  public int numRookCaptures(char[][] board) {
    // find rook
    int row = 0, col = 0;
    label:
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board.length; c++) {
        if (board[r][c] == 'R') {
          row = r;
          col = c;
          break label;
        }
      }
    }
    int out = 0;
    // track to left
    for (int c = col; c < board.length; c++) {
      char v = board[row][c];
      if (v != '.' && v != 'R') {
        if (v == 'p') {
          out++;
        }
        break;
      }
    }
    // track to right
    for (int c = col; c > -1; c--) {
      char v = board[row][c];
      if (v != '.' && v != 'R') {
        if (v == 'p') {
          out++;
        }
        break;
      }
    }
    // track to top
    for (int r = row; r > -1; r--) {
      char v = board[r][col];
      if (v != '.' && v != 'R') {
        if (v == 'p') {
          out++;
        }
        break;
      }
    }
    // track to bottom
    for (int r = row; r < board.length; r++) {
      char v = board[r][col];
      if (v != '.' && v != 'R') {
        if (v == 'p') {
          out++;
        }
        break;
      }
    }
    return out;
  }

  @Test
  public void test1() {
    char[][] input =
        new char[][]{
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', 'p', '.', '.', '.', '.'},
            {'.', '.', '.', 'R', '.', '.', '.', 'p'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', 'p', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'}
        };
    Assertions.assertEquals(3, numRookCaptures(input));
  }

  @Test
  public void test2() {
    char[][] input =
        new char[][]{
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
            {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
            {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'},
            {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
            {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'}
        };
    Assertions.assertEquals(0, numRookCaptures(input));
  }

  @Test
  public void test3() {
    char[][] input =
        new char[][]{
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', 'p', '.', '.', '.', '.'},
            {'.', '.', '.', 'p', '.', '.', '.', '.'},
            {'p', 'p', '.', 'R', '.', 'p', 'B', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', 'B', '.', '.', '.', '.'},
            {'.', '.', '.', 'p', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'}
        };
    Assertions.assertEquals(3, numRookCaptures(input));
  }
}
