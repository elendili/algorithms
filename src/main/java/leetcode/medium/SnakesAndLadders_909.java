package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/problems/snakes-and-ladders/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class SnakesAndLadders_909 {
    public int snakesAndLadders(int[][] board) {
        final int n = board.length;
        int target = n * n;
        boolean[] visited = new boolean[target + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1); // first cell
        visited[1] = true;
        int levelsCount = 0;
        int levelSize = 1;
        int[] coords = new int[2];
        Integer cur = 1;
        while (!q.isEmpty()) {
            levelsCount += 1;
            int nextLevelSize = 0;
            System.out.println(levelsCount+". size="+levelSize+", q="+q);
            while (levelSize > 0 && !q.isEmpty()) {
                levelSize--;
                cur = q.poll();
                if (cur >= target) {
                    return levelsCount - 1;
                }
                int last = Math.min(cur + 6, target);
                for (int next = cur + 1; next <= last; next++) {
                    numberToIndex(next, n, coords);
                    int boardVal = board[coords[0]][coords[1]];
                    if (boardVal > 0) {
                        if (!visited[boardVal]) {
                            q.add(boardVal);
                            visited[boardVal] = true;
                            nextLevelSize += 1;
                        }
                    } else if (!visited[next]) {
                        q.add(next);
                        visited[next] = true;
                        nextLevelSize += 1;
                    }
                }
            }
            levelSize = nextLevelSize;
        }
        int out = cur >= target ? levelsCount : -1;
        return out;
    }

    void numberToIndex(int number, int n, int[] out) {
        assert number > 0 : "wrong number";
        /*

        7 8 9
        6 5 4
        1 2 3

        9 -> row: 0, col: 2
        8 -> row: 0, col: 1
        7 -> row: 0, col: 0
        6 -> row: 1, col: 2
        row:
        if num%n==0:
          row = (n-1) - (num-1)/n
           else:
          row = (n-1) - num/n
        col:
        left = (num-1)%n;    // 9%3=2, 8%3=1, 7%3=0
        if row%2==0:
            col=left
           else:
            col = (n-1)  - left


        */
        int row;
        if (number % n == 0) {
            row = (n - 1) - (number - 1) / n;
        } else {
            row = (n - 1) - number / n;
        }
        int leftover = (number - 1) % n;
        int col;
        if (n % 2 == 0) {
            if (row % 2 == 0) {
                col = (n - 1) - leftover;
            } else {
                col = leftover;
            }
        } else {
            if (row % 2 == 0) {
                col = leftover;
            } else {
                col = (n - 1) - leftover;
            }
        }

        out[0] = row;
        out[1] = col;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        int[][] input = new int[][]{
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        assertEquals(4, snakesAndLadders(input));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        int[][] input = new int[][]{
                {-1, -1},
                {-1, 3},
        };
        assertEquals(1, snakesAndLadders(input));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        int[][] input = new int[][]{
                {-1, -1, -1},
                {-1, -1, -1},
                {-1, -1, -1},
        };
        assertEquals(2, snakesAndLadders(input));
        input = new int[][]{
                {-1, -1, -1},
                {-1, -1, -1},
                {-1, -1, 9},
        };
        assertEquals(1, snakesAndLadders(input));
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        int[][] input = new int[][]{
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
        };
        assertEquals(3, snakesAndLadders(input));
        input = new int[][]{
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, 16, -1},
                {-1, -1, -1, -1},
        };
        assertEquals(1, snakesAndLadders(input));
    }

    @org.junit.jupiter.api.Test
    public void test_from_leetcode1() {
        int[][] input = new int[][]{
                {1, 1, -1},
                {1, 1, 1},
                {-1, 1, 1}
        };
        assertEquals(-1, snakesAndLadders(input));
    }

    @org.junit.jupiter.api.Test
    public void test_from_leetcode2() {
        int[][] input = new int[][]{
                {-1, 10, -1, 15, -1},
                {-1, -1, 18,  2, 20},
                {-1, -1, 12, -1, -1},
                { 2,  4, 11, 18,  8},
                {-1, -1, -1, -1, -1}
                /*
                {21, 22, 23, 24, 25},
                {20, 19, 18, 17, 16},
                {11, 12, 13, 14, 15},
                {10,  9,  8,  7,  6},
                { 1,  2,  3,  4,  5}
                 */

        };
        assertEquals(3, snakesAndLadders(input));
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            value = {
                    "number, n, expectedRow, expectedCol",
                    "2,      3, 2,           1",
                    "2,      4, 3,           1",
                    "2,      5, 4,           1",
                    "2,      6, 5,           1",
                    // end of second line
                    "4,      3, 1,           2",
                    "5,      4, 2,           3",
                    "6,      5, 3,           4",
                    "7,      6, 4,           5",
                    // start of third line
                    "7,      3, 0,           0",
                    "9,      4, 1,           0",
                    "11,     5, 2,           0",
                    "13,     6, 3,           0",
            })
    public void test(int number, int n, int expectedRow, int expectedCol) {
        int[] out = new int[2];
        numberToIndex(number, n, out);
        assertEquals(expectedRow, out[0], "wrong row");
        assertEquals(expectedCol, out[1], "wrong column");
    }
}
