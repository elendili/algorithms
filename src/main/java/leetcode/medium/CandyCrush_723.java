package leetcode.medium;

import static helpers.TestHelper.twoDArrayToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandyCrush_723 {
    int h, w;
    int[][] board;

    public int[][] candyCrush(int[][] board) {
        // find similar
        h = board.length;
        w = board[0].length;
        this.board = board;


        while (removeStones() > 0) {
//            System.out.println("before shift");
//            System.out.println(twoDArrayToString(board));
            shiftDown();
//            System.out.println("after shift");
//            System.out.println(twoDArrayToString(board));
        }
        return board;
    }

    int removeStones() {
        // mark for removal
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int val = Math.abs(board[y][x]);
                if (val == 0) {
                    continue;
                }
                markStonesInLine(y, x, val);
            }
        }
        // remove
        int removed = 0;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if (board[y][x] < 0) {
                    board[y][x] = 0;
                    removed += 1;
                }
            }
        }
        return removed;
    }

    void markStonesInLine(int y, int x, int val) {
        // horizontal
        if (x + 2 < w
                && Math.abs(board[y][x + 1]) == val
                && Math.abs(board[y][x + 2]) == val) {
            for (int i = 0; x + i < w && Math.abs(board[y][x + i]) == val; i++) {
                board[y][x + i] = -Math.abs(board[y][x + i]);
            }
        }
        // vertical
        if (y + 2 < h
                && Math.abs(board[y + 1][x]) == val
                && Math.abs(board[y + 2][x]) == val) {
            for (int i = 0; y + i < h && Math.abs(board[y + i][x]) == val; i++) {
                board[y + i][x] = -Math.abs(board[y + i][x]);
            }
        }
    }

    void shiftDown() {
        for (int x = 0; x < w; x++) {
            boolean foundStone = false;
            for (int y = 0; y < h; y++) {
                if (board[y][x] == 0) {
                    if (foundStone) {
                        // time to shift down
                        for (int yy = y; yy > 0 && board[yy - 1][x] > 0; yy--) {
                            board[yy][x] = board[yy - 1][x];
                            board[yy - 1][x] = 0;
                        }
                    }
                } else {
                    foundStone = true;
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        testBody(new int[][]{
                        {110, 5, 112, 113, 114},
                        {210, 211, 5, 213, 214},
                        {310, 311, 3, 313, 314},
                        {410, 411, 412, 5, 414},
                        {5, 1, 512, 3, 3},
                        {610, 4, 1, 613, 614},
                        {710, 1, 2, 713, 714},
                        {810, 1, 2, 1, 1},
                        {1, 1, 2, 2, 2},
                        {4, 1, 4, 4, 1014}},
                new int[][]{
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {110, 0, 0, 0, 114},
                        {210, 0, 0, 0, 214},
                        {310, 0, 0, 113, 314},
                        {410, 0, 0, 213, 414},
                        {610, 211, 112, 313, 614},
                        {710, 311, 412, 613, 714},
                        {810, 411, 512, 713, 1014}
                });
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        testBody(new int[][]{{1, 3, 5, 5, 2}, {3, 4, 3, 3, 1}, {3, 2, 4, 5, 2}, {2, 4, 4, 5, 5}, {1, 4, 4, 1, 1}},
                new int[][]{{1, 3, 0, 0, 0}, {3, 4, 0, 5, 2}, {3, 2, 0, 3, 1}, {2, 4, 0, 5, 2}, {1, 4, 3, 1, 1}});
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        testBody(
                new int[][]{{1, 2, 1}, {2, 2, 2}, {1, 2, 1}},
                new int[][]{{0, 0, 0}, {1, 0, 1}, {1, 0, 1}}
        );
        testBody(
                new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}},
                new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
        );
        testBody(
                new int[][]{
                        {1, 1, 1},
                        {2, 2, 2},
                        {3, 3, 3}},
                new int[][]{
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}}
        );
        testBody(
                new int[][]{
                        {1, 2, 3},
                        {1, 2, 3},
                        {1, 2, 3}},
                new int[][]{
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}}
        );
        testBody(
                new int[][]{
                        {3, 2, 3},
                        {3, 3, 3},
                        {3, 2, 3}
                },
                new int[][]{
                        {0, 0, 0},
                        {0, 2, 0},
                        {0, 2, 0}
                }
        );
        testBody(
                new int[][]{
                        {3, 3, 3},
                        {2, 3, 2},
                        {3, 3, 3}
                },
                new int[][]{
                        {0, 0, 0},
                        {0, 0, 0},
                        {2, 0, 2}
                }
        );
        testBody(
                new int[][]{
                        {1, 1, 2},
                        {2, 2, 2},
                        {2, 1, 1}
                },
                new int[][]{
                        {0, 0, 0},
                        {1, 1, 2},
                        {2, 1, 1}
                }
        );
        testBody(
                new int[][]{
                        {1, 1, 1},
                        {2, 2, 2},
                        {3, 1, 3}
                },
                new int[][]{
                        {0, 0, 0},
                        {0, 0, 0},
                        {3, 1, 3}
                }
        );
        testBody(
                new int[][]{
                        {1, 1, 1},
                        {2, 0, 2},
                        {3, 1, 3}
                },
                new int[][]{
                        {0, 0, 0},
                        {2, 0, 2},
                        {3, 1, 3}
                }
        );
    }

    void testBody(int[][] input, int[][] expected) {
        String inputString = twoDArrayToString(input);
        System.out.println();
        System.out.println("<> Input:");
        System.out.println(inputString);

        String expectedString = twoDArrayToString(expected);
        String actual = twoDArrayToString(candyCrush(input));

        System.out.println("\n<> Actual:");
        System.out.println(actual);
        System.out.println("\n<> Expected:");
        System.out.println(expectedString);

        assertEquals(expectedString, actual);
    }
}
