package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

// https://leetcode.com/problems/sliding-puzzle/
public class SlidingPuzzle {
    static class Solution1 {
        private static final int[][] solution = new int[][]{{1, 2, 3}, {4, 5, 0}};
        private static final int maxMoves = 50;
        private static final Map<Integer, Integer> memo = new HashMap<>();

        public int slidingPuzzle(int[][] b) {
            // find zero
            int x = -1, y;
            outerloop:
            for (y = 0; y < b.length; y++) {
                for (x = 0; x < b[0].length; x++) {
                    if (b[y][x] == 0) break outerloop;
                }
            }
            int out = recursion(b, x, y, x, y, 0, new HashMap<>());
            return out == maxMoves ? -1 : out;
        }

        private int[][] swapWithClone(int[][] a, int x0, int y0, int xD, int yD) {
            if (x0 == xD && y0 == yD) {
                return a;
            } else {
                int[][] copyA = Arrays.stream(a).map(int[]::clone).toArray(int[][]::new);
                int tmp = copyA[yD][xD];
                copyA[yD][xD] = 0;
                copyA[y0][x0] = tmp;
                return copyA;
            }
        }

        int hashCode(int[][] a) {
            int out = 0;
            for (int i = 0; i < 6; i++) {
                int n = a[i / 3][i % 3];
                out += n * Math.pow(10, 6 - i - 1);
            }
            return out;
        }

        int recursion(int[][] _a, int x0, int y0, int xD, int yD, int moves, Map<Integer, Integer> memo) {
            // check board boundaries
            if (
                    x0 < 0 || y0 < 0 || y0 > 1 || x0 > 2 ||
                            xD < 0 || yD < 0 || yD > 1 || xD > 2
                            || moves > maxMoves
            ) {
                return maxMoves;
            } else {
                int key = hashCode(_a);
                Integer memorized = memo.get(key);
                if (memorized != null) {
                    return memorized;
                } else {
                    int[][] a = swapWithClone(_a, x0, y0, xD, yD);
                    key = hashCode(a);
                    // check end condition
                    if (xD == 2 && yD == 1 && Arrays.deepEquals(a, solution)) {
//                    memo.put(key, moves);
                        return moves;
                    } else {
                        int left = x0 == xD - 1 ? maxMoves : recursion(a, xD, yD, xD - 1, yD, moves + 1, memo);
                        int right = x0 == xD + 1 ? maxMoves : recursion(a, xD, yD, xD + 1, yD, moves + 1, memo);
                        int down = y0 == yD + 1 ? maxMoves : recursion(a, xD, yD, xD, yD + 1, moves + 1, memo);
                        int up = y0 == yD - 1 ? maxMoves : recursion(a, xD, yD, xD, yD - 1, moves + 1, memo);
                        int out = IntStream.of(left, right, down, up).min().orElse(maxMoves);
                        memo.compute(key, (k, v) -> v == null ? out : Math.min(v, out));
                        return out;
                    }
                }
            }
        }
    }

//    https://medium.com/@benjamin.botto/sequentially-indexing-permutations-a-linear-algorithm-for-computing-lexicographic-rank-a22220ffd6e3
    static class Solution2 {
        static class D {
            final int[][] board;
            final int x0, y0;

            D(D d2, int xDelta, int yDelta) {
                this(d2.board, d2.x0, d2.y0, d2.x0 + xDelta, d2.y0 + yDelta);
            }

            D(int[][] b, int x0, int y0, int xD, int yD) {
                if (x0 == xD && y0 == yD) {
                    this.board = b;
                    this.x0 = x0;
                    this.y0 = y0;
                } else {
                    int[][] copyA = Arrays.stream(b).map(int[]::clone).toArray(int[][]::new);
                    int tmp = copyA[yD][xD];
                    copyA[yD][xD] = 0;
                    copyA[y0][x0] = tmp;
                    this.board = copyA;
                    this.x0 = xD;
                    this.y0 = yD;
                }
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                D d = (D) o;
                return Arrays.deepEquals(board, d.board);
            }

            @Override
            public int hashCode() {
                int out = 0;
                for (int i = 0; i < 6; i++) {
                    int n = board[i / 3][i % 3];
                    out += n * Math.pow(10, 6 - i - 1);
                }
                return out;
            }
        }

        private static final int[][] solution = new int[][]{{1, 2, 3}, {4, 5, 0}};

        public int slidingPuzzle(int[][] b) {
            int x = -1, y;
            outerloop:
            for (y = 0; y < b.length; y++) {
                for (x = 0; x < b[0].length; x++) {
                    if (b[y][x] == 0) break outerloop;
                }
            }
            HashMap<D, Integer> distances = new HashMap<>();
            Queue<D> q = new ArrayDeque<>();
            q.add(new D(b, x, y, x, y));
            distances.put(q.peek(), 0);
            while (!q.isEmpty()) {
                final D d = q.poll();
                if (d.x0 == 2 && d.y0 == 1 && Arrays.deepEquals(d.board, solution)) {
                    return distances.getOrDefault(d, 0);
                } else {
                    int newDist = distances.get(d) + 1;
                    if (d.x0 > 0) {
                        D dL = new D(d, -1, 0);
                        if (!distances.containsKey(dL)) {
                            distances.put(dL, newDist);
                            q.add(dL);
                        }
                    }
                    if (d.x0 < 2) {
                        D dR = new D(d, +1, 0);
                        if (!distances.containsKey(dR)) {
                            distances.put(dR, newDist);
                            q.add(dR);
                        }
                    }
                    if (d.y0 > 0) {
                        D dU = new D(d, 0, -1);
                        if (!distances.containsKey(dU)) {
                            distances.put(dU, newDist);
                            q.add(dU);
                        }
                    }
                    if (d.y0 < 1) {
                        D dD = new D(d, 0, +1);
                        if (!distances.containsKey(dD)) {
                            distances.put(dD, newDist);
                            q.add(dD);
                        }
                    }
                }
            }
            return -1;
        }
    }


    @Test
    public void test() {
        Solution1 s = new Solution1();
        Assertions.assertEquals(1, s.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 0, 5}}));
        Assertions.assertEquals(1, s.slidingPuzzle(new int[][]{{1, 2, 0}, {4, 5, 3}}));
        Assertions.assertEquals(-1, s.slidingPuzzle(new int[][]{{1, 2, 3}, {5, 4, 0}}));
        Assertions.assertEquals(5, s.slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
    }

    @Test
    public void test2() {
        Solution2 s = new Solution2();
        Assertions.assertEquals(1, s.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 0, 5}}));
        Assertions.assertEquals(1, s.slidingPuzzle(new int[][]{{1, 2, 0}, {4, 5, 3}}));
        Assertions.assertEquals(-1, s.slidingPuzzle(new int[][]{{1, 2, 3}, {5, 4, 0}}));
        Assertions.assertEquals(5, s.slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
    }
}
