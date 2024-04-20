package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumObstacleRemovalToReachCorner {
    int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int minimumObstacles(int[][] grid) {
        // use BFS with priority queue ?
        // use amount of already removed as priority
        Deque<int[]> q = new ArrayDeque<>();
        int w = grid.length;
        int h = grid[0].length;
        q.add(new int[]{0, 0, 0});

        int[][] visitedDistance = new int[w][h];
        for (int i = 0; i < visitedDistance.length; i++) {
            Arrays.fill(visitedDistance[i], Integer.MAX_VALUE);
        }

        while (!q.isEmpty()) {
            int[] c = q.poll();
            // exit condition
            int row = c[0];
            int col = c[1];
            int count = c[2];
            if (row == w - 1 && col == h - 1) {
                return count;
            }
            for (int[] delta : directions) {
                int newRow = row + delta[0];
                int newCol = col + delta[1];
                if (newRow < 0 || newRow >= w || newCol < 0 || newCol >= h) {
                    continue;
                }
                int newCount = count + grid[newRow][newCol];
                if (newCount < visitedDistance[newRow][newCol]) {
                    int[] p = new int[]{newRow, newCol, newCount};
                    if (grid[newRow][newCol] > 0) {
                        q.addLast(p);
                    } else {
                        q.addFirst(p);
                    }
                    visitedDistance[newRow][newCol] = newCount;
                }
            }

        }
        return -1;
    }


    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(1, minimumObstacles(new int[][]{{0, 1}, {1, 0}}));
        assertEquals(3, minimumObstacles(new int[][]{{0, 1, 1}, {1, 1, 1}, {1, 1, 0}}));
        assertEquals(2, minimumObstacles(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}));
        assertEquals(0, minimumObstacles(new int[][]{{0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}}));

        assertEquals(3, minimumObstacles(new int[][]{{0, 1, 1, 1, 0}}));
        assertEquals(2, minimumObstacles(new int[][]{{0, 1, 0, 1, 0}}));

        assertEquals(3, minimumObstacles(new int[][]{{0}, {1}, {1}, {1}, {0}}));
        assertEquals(2, minimumObstacles(new int[][]{{0}, {1}, {0}, {1}, {0}}));

        assertEquals(0, minimumObstacles(new int[][]{{0, 0}, {0, 0}}));

    }
}
