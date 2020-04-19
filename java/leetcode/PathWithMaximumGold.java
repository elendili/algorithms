package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathWithMaximumGold {
    static class Solution {
        public int getMaximumGold(int[][] grid) {
            int h = grid.length;
            int w = grid[0].length;
            // search dead ends
            int[][] neighbors = new int[h][w];
            int minNeighbors = 4;
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (grid[y][x] != 0) {
                        int count = 0;
                        if (y - 1 >= 0 && grid[y - 1][x] > 0) count++;
                        if (y + 1 < h && grid[y + 1][x] > 0) count++;
                        if (x - 1 >= 0 && grid[y][x - 1] > 0) count++;
                        if (x + 1 < w && grid[y][x + 1] > 0) count++;
                        neighbors[y][x] = count;
                        if (count > 0) {
                            minNeighbors = Math.min(minNeighbors, count);
                        }
                    }
                }
            }
            // search gold
            int max = 0;
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int v = grid[y][x];
                    if (v > 0 && neighbors[y][x] <= minNeighbors) {
                        int res = dfs(grid, h, w, x, y);
                        max = Math.max(max, res);
                    }
                }
            }
            return max;
        }

        // return max of paths
        public int dfs(int[][] a, int h, int w, int x, int y) {
            if (x < 0 || x >= w || y < 0 || y >= h || a[y][x] < 1) {
                return 0;
            } else {
                int val = a[y][x];
                a[y][x] = 0;
                int leftRight = Math.max(dfs(a, h, w, x - 1, y), dfs(a, h, w, x + 1, y));
                int upDown = Math.max(dfs(a, h, w, x, y - 1), dfs(a, h, w, x, y + 1));
                a[y][x] = val;
                int out = val + Math.max(leftRight, upDown);
                return out;
            }
        }

        @Test
        public void test() {
            Assertions.assertEquals(0, getMaximumGold(new int[][]{{0, 0}, {0, 0}}));
            Assertions.assertEquals(1, getMaximumGold(new int[][]{{1, 0}, {0, 0}}));
            Assertions.assertEquals(1, getMaximumGold(new int[][]{{0, 1}, {0, 0}}));
            Assertions.assertEquals(1, getMaximumGold(new int[][]{{0, 0}, {1, 0}}));
            Assertions.assertEquals(1, getMaximumGold(new int[][]{{0, 0}, {0, 1}}));
            Assertions.assertEquals(2, getMaximumGold(new int[][]{{1, 0}, {0, 2}}));
            Assertions.assertEquals(17, getMaximumGold(new int[][]{
                    {1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 1},
                    {1, 0, 17, 0, 1},
                    {1, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1},
            }));
            Assertions.assertEquals(16, getMaximumGold(new int[][]{
                    {1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 1},
                    {1, 0, 15, 0, 1},
                    {1, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1},
            }));
            Assertions.assertEquals(24, getMaximumGold(new int[][]{
                    {0, 6, 0},
                    {5, 8, 7},
                    {0, 9, 0}}));
            Assertions.assertEquals(28, getMaximumGold(new int[][]{{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}}));
        }
    }
}
