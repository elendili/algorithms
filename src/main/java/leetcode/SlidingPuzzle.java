package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://leetcode.com/problems/sliding-puzzle/
// https://medium.com/@benjamin.botto/sequentially-indexing-permutations-a-linear-algorithm-for-computing-lexicographic-rank-a22220ffd6e3

public class SlidingPuzzle {
    static class Solution3 {
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
                this.hashCode = _hashCode();
            }

            @Override
            public boolean equals(Object o) {
                return o.hashCode() == this.hashCode();
            }

            private final int hashCode;

            private int _hashCode() {
                int out = 0;
                for (int i = 0; i < 6; i++) {
                    int n = board[i / 3][i % 3];
                    out += n * Math.pow(10, 6 - i - 1);
                }
                return out;
            }

            @Override
            public int hashCode() {
                return hashCode;
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
        Solution3 s = new Solution3();
        Assertions.assertEquals(0, s.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 5, 0}}));
        Assertions.assertEquals(1, s.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 0, 5}}));
        Assertions.assertEquals(1, s.slidingPuzzle(new int[][]{{1, 2, 0}, {4, 5, 3}}));
        Assertions.assertEquals(-1, s.slidingPuzzle(new int[][]{{1, 2, 3}, {5, 4, 0}}));
        Assertions.assertEquals(5, s.slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
    }

    static class Solution {
        static class D {
            final byte x0, y0;
            private final int hashCode;

            D(D d2, int xDelta, int yDelta) {
                this(d2.hashCode, d2.x0, d2.y0,
                        (byte) (d2.x0 + xDelta),
                        (byte) (d2.y0 + yDelta));
            }

            D(int[][] b) {
                byte x = -1, y;
                outerloop:
                for (y = 0; y < b.length; y++) {
                    for (x = 0; x < b[0].length; x++) {
                        if (b[y][x] == 0) break outerloop;
                    }
                }
                this.hashCode = _hashCode(b);
                this.x0 = x;
                this.y0 = y;
            }

            D(int hashCode, byte x0, byte y0, byte xD, byte yD) {
                if ((x0 == xD && y0 == yD)) {
                    this.hashCode = hashCode;
                    this.x0 = x0;
                    this.y0 = y0;
                } else {
                    byte i1 = (byte) (yD * 3 + xD);
                    byte i2 = (byte) (y0 * 3 + x0);
                    this.hashCode = swapDecimalDigit(hashCode, (byte) 6, i1, i2);
                    this.x0 = xD;
                    this.y0 = yD;
                }
            }

            @Override
            public boolean equals(Object o) {
                boolean out = o.hashCode() == this.hashCode();
                return out;
            }

            private String _toString() {
                return hashCode() + "";
            }

            private int _hashCode(int[][] a) {
                int out = 0;
                for (int i = 0; i < 6; i++) {
                    int n = a[i / 3][i % 3];
                    out += n * Math.pow(10, 6 - i - 1);
                }
                return out;
            }

            static int swapDecimalDigit(int number, byte size, byte i1, byte i2) {
                int power1 = (int) Math.pow(10, size - i1 - 1);
                int power2 = (int) Math.pow(10, size - i2 - 1);
                int d1 = (number / power1) % 10;
                int d2 = (number / power2) % 10;
                int number2 = number - d1 * power1 + d2 * power1;
                int number3 = number2 - d2 * power2 + d1 * power2;
                return number3;
            }

            @Override
            public int hashCode() {
                return hashCode;
            }
        }

        private static final D solutionD = new D(123450, (byte) 2, (byte) 1, (byte) 2, (byte) 1);
        private static final HashMap<D, Integer> distances = new HashMap<>();

        static {
            distances.put(solutionD, 0);
        }

        public int slidingPuzzle(final int[][] fromBoard) {
            D fromBoardD = new D(fromBoard);
            if (distances.size() < 360) {
                Set<D> met = new HashSet<>();
                Queue<D> q = new ArrayDeque<>();
                q.add(solutionD);
                while (!q.isEmpty() && !distances.containsKey(fromBoardD)) {
                    final D d = q.poll();
                    int newDist = distances.get(d) + 1;
                    if (d.x0 > 0) {
                        D dL = new D(d, -1, 0);
                        if (met.add(dL)) {
                            q.add(dL);
                            distances.compute(dL, (k, v) -> v == null ? newDist : Math.min(newDist, v));
                        }
                    }
                    if (d.x0 < 2) {
                        D dR = new D(d, +1, 0);
                        if (met.add(dR)) {
                            q.add(dR);
                            distances.compute(dR, (k, v) -> v == null ? newDist : Math.min(newDist, v));
                        }

                    }
                    if (d.y0 > 0) {
                        D dU = new D(d, 0, -1);
                        if (met.add(dU)) {
                            q.add(dU);
                            distances.compute(dU, (k, v) -> v == null ? newDist : Math.min(newDist, v));
                        }
                    }
                    if (d.y0 < 1) {
                        D dD = new D(d, 0, +1);
                        if (met.add(dD)) {
                            q.add(dD);
                            distances.compute(dD, (k, v) -> v == null ? newDist : Math.min(newDist, v));
                        }
                    }
                }
            }
            return distances.getOrDefault(fromBoardD, -1);
        }
    }


    @Test
    public void test2() {
        Solution s = new Solution();
        Assertions.assertEquals(-1, s.slidingPuzzle(new int[][]{{1, 2, 3}, {5, 4, 0}}));
        Assertions.assertEquals(0, s.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 5, 0}}));
        Assertions.assertEquals(1, s.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 0, 5}}));
        Assertions.assertEquals(2, s.slidingPuzzle(new int[][]{{1, 2, 3}, {0, 4, 5}}));
        Assertions.assertEquals(3, s.slidingPuzzle(new int[][]{{0, 2, 3}, {1, 4, 5}}));
        Assertions.assertEquals(1, s.slidingPuzzle(new int[][]{{1, 2, 0}, {4, 5, 3}}));
        Assertions.assertEquals(5, s.slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
    }

    @Test
    public void testSwapDigit() {
        Assertions.assertEquals(21, Solution.D.swapDecimalDigit(12, (byte) 2, (byte) 0, (byte) 1));
        Assertions.assertEquals(12543, Solution.D.swapDecimalDigit(12345, (byte) 5, (byte) 2, (byte) 4));
        Assertions.assertEquals(52341, Solution.D.swapDecimalDigit(12345, (byte) 5, (byte) 0, (byte) 4));
        Assertions.assertEquals(12435, Solution.D.swapDecimalDigit(12345, (byte) 5, (byte) 3, (byte) 2));
    }


}
