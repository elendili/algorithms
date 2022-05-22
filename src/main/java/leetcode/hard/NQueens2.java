package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/n-queens-ii/
 */
public class NQueens2 {
    static class Solution {
        private int count;
        public int totalNQueens(int n) {
            if (n < 1) {
                return 0;
            }
            count = 0;
            int diagonalCount = 2 * n - 1;
            recursive(new int[n],
                    new boolean[n],
                    new boolean[diagonalCount],
                    new boolean[diagonalCount],
                    n,
                    0);
            return count;
        }

        void recursive(int[] xs, // index is a row (y), value is column (x)
                       boolean[] xFlag,
                       boolean[] diagonalDown,
                       boolean[] diagonalUp,
                       int n, // size
                       int y // cur row
                       ) {
            if (n == y) {
                count++;
            } else {
                for (int x = 0; x < n; x++) {
                    // check whether field is under attack
                    int diagonalUpIndex = x + y;
                    int diagonalDownIndex = n - 1 + x - y;
                    boolean underAttack = xFlag[x]
                            || diagonalUp[diagonalUpIndex]
                            || diagonalDown[diagonalDownIndex];
                    if (!underAttack) {
                        // add hope
                        xs[y] = x;
                        xFlag[x] = true;
                        diagonalUp[diagonalUpIndex] = true;
                        diagonalDown[diagonalDownIndex] = true;
                        // go deep
                        recursive(xs, xFlag, diagonalDown, diagonalUp, n, y + 1);
                        // rollback
                        xs[y] = 0;
                        xFlag[x] = false;
                        diagonalUp[diagonalUpIndex] = false;
                        diagonalDown[diagonalDownIndex] = false;
                    }
                }
            }
        }

        @Test
        public void test() {
            Assertions.assertEquals(0, totalNQueens(0));
            Assertions.assertEquals(1, totalNQueens(1));
            Assertions.assertEquals(0, totalNQueens(2));
            Assertions.assertEquals(0, totalNQueens(3));
            Assertions.assertEquals(2, totalNQueens(4));
            Assertions.assertEquals(10, totalNQueens(5));
            Assertions.assertEquals(4, totalNQueens(6));
            Assertions.assertEquals(40, totalNQueens(7));
            Assertions.assertEquals(92,  totalNQueens(8));
            Assertions.assertEquals(352,  totalNQueens(9));
            Assertions.assertEquals(724,  totalNQueens(10));
            Assertions.assertEquals(2680,  totalNQueens(11));
//            Assertions.assertEquals(365_596,  totalNQueens(14));
        }
    }

}
