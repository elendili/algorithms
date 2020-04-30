package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

// https://leetcode.com/problems/n-queens/
public class NQueens {
    static class Solution {
        private StringBuilder outLine;

        private StringBuilder makeEmptyOutLine(int n) {
            return Stream.generate(() -> ".")
                    .limit(n).reduce(new StringBuilder(),
                            StringBuilder::append,
                            StringBuilder::append);
        }

        private List<String> makeOutField(int[] xs) {
            List<String> out = new ArrayList<>(xs.length);
            for (int c : xs) {
                outLine.setCharAt(c, 'Q');
                out.add(outLine.toString());
                outLine.setCharAt(c, '.');
            }
            return out;
        }

        public List<List<String>> solveNQueens(int n) {
            if (n < 1) {
                return Collections.emptyList();
            }
            outLine = makeEmptyOutLine(n);
            List<List<String>> out = new LinkedList<>();
            int diagonalCount = 2 * n - 1;
            recursive(new int[n],
                    new boolean[n],
                    new boolean[diagonalCount],
                    new boolean[diagonalCount],
                    n,
                    0,
                    out
            );
            return out;
        }

        void recursive(int[] xs, // index is a row (y), value is column (x)
                       boolean[] xFlag,
                       boolean[] diagonalDown,
                       boolean[] diagonalUp,
                       int n, // size
                       int y, // cur row
                       List<List<String>> out) {
            if (n == y) {
                out.add(makeOutField(xs));
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
                        recursive(xs, xFlag, diagonalDown, diagonalUp, n, y + 1, out);
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
            Assertions.assertEquals(emptyList(), solveNQueens(0));
            Assertions.assertEquals("[[Q]]", solveNQueens(1).toString());
            Assertions.assertEquals(emptyList(), solveNQueens(2));
            Assertions.assertEquals(emptyList(), solveNQueens(3));
            String expected = Arrays.asList(
                    Arrays.asList(".Q..", "...Q", "Q...", "..Q."),
                    Arrays.asList("..Q.", "Q...", "...Q", ".Q.."))
                    .toString();
            Assertions.assertEquals(expected,
                    solveNQueens(4).toString());


            Assertions.assertEquals(10, solveNQueens(5).size());
            Assertions.assertEquals(4, solveNQueens(6).size());
            Assertions.assertEquals(40, solveNQueens(7).size());
//            Assertions.assertEquals(724,  solveNQueens(10).size());
//            Assertions.assertEquals(365_596,  solveNQueens(14).size());

//            System.out.println(
//                    solveNQueens(5).stream().map(f -> String.join("\n", f))
//                            .collect(Collectors.joining("\n----\n")));
        }
    }

}
