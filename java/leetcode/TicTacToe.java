package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
public class TicTacToe {
    public String tictactoe(int[][] moves) {
        int pi = -1;
        int[] res = new int[3 * 2 + 2];
        for (int[] m : moves) {
            int x = m[0];
            int y = m[1];
            res[x] += pi;
            res[3 + y] += pi;
            if (x == y) {
                res[6] += pi;
            }
            if (x == 3 - 1 - y) {
                res[7] += pi;
            }
            pi=pi==1?-1:1;
        }
        for (int r : res) {
            if (r == 3) {
                return "B";
            }
            if (r == -3) {
                return "A";
            }
        }
        if (moves.length == 9) {
            return "Draw";
        } else {
            return "Pending";
        }
    }

    @Test
    public void test() {
        assertEquals("A", tictactoe(new int[][]{{0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2}}));
        assertEquals("B", tictactoe(new int[][]{{0, 0}, {1, 1}, {0, 1}, {0, 2}, {1, 0}, {2, 0}}));
        assertEquals("Draw", tictactoe(new int[][]{{0, 0}, {1, 1}, {2, 0}, {1, 0}, {1, 2}, {2, 1}, {0, 1}, {0, 2}, {2, 2}}));
        assertEquals("Pending", tictactoe(new int[][]{{0, 0}, {1, 1}}));
    }

}
