package leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumTimeToVisitCellInGrid {

    public int minimumTime(int[][] grid) {
        int h = grid.length, w = grid[0].length;
        if (w > 1) {
            if (h == 1 && grid[0][1] > 1) {
                return -1;
            } else if (grid[0][1] > 1 && grid[1][0] > 1) {
                return -1;
            }
        } else if (h > 1 && grid[1][0] > 1) {
            return -1;
        }

        int[] dirs = {-1, 0, 1, 0};
        boolean[][] visited = new boolean[h][w];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0];
            int row = curr[1];
            int col = curr[2];
            if (row == h - 1 && col == w - 1) {
                return time;
            }
            for (int i = 0; i < 4; i++) {
                int r = row + dirs[i];
                int c = col + dirs[(i + 1) % 4];
                if (r > -1 && r < h && c > -1 && c < w && !visited[r][c]) {
                    // ping pong jumps depends on diff between grid[r][c] and time
                    // if diff is even then we come to the cell at moment=grid[r][c]+1
                    // if diff is odd then we come to the cell at moment=grid[r][c]
                    int extraJump = (grid[r][c] - time + 1) % 2;
                    int newTime = Math.max(grid[r][c] + extraJump, time + 1);
                    pq.offer(new int[]{newTime, r, c});
                    visited[r][c] = true;
                }
            }
        }
        return -1;
    }

    @org.junit.jupiter.api.Test
    public void test0() {
        assertEquals(1, minimumTime(new int[][]{{0, 0}}));
        assertEquals(2, minimumTime(new int[][]{{0, 0, 0}}));
        assertEquals(1, minimumTime(new int[][]{{0}, {0}}));
        assertEquals(2, minimumTime(new int[][]{{0}, {0}, {0}}));

        assertEquals(-1, minimumTime(new int[][]{{0, 2, 0}}));
        assertEquals(-1, minimumTime(new int[][]{{0}, {2}, {0}}));

        assertEquals(101, minimumTime(new int[][]{{0, 0, 100, 0}}));
        assertEquals(5, minimumTime(new int[][]{{0, 0, 3, 0}}));
        assertEquals(3, minimumTime(new int[][]{{0, 0, 2, 0}}));
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(7, minimumTime(new int[][]{{0, 1, 3, 2}, {5, 1, 2, 5}, {4, 3, 8, 6}}));
        assertEquals(-1, minimumTime(new int[][]{{0, 2, 4}, {3, 2, 1}, {1, 0, 4}}));
    }
}
