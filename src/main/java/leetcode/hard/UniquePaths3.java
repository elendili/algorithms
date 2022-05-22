package leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/unique-paths-iii/
 * You are given an m x n integer array grid where grid[i][j] could be:
 * <p>
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * 1 <= m * n <= 20
 * -1 <= grid[i][j] <= 2
 * There is exactly one starting cell and one ending cell.
 */
public class UniquePaths3 {
    public int uniquePathsIII(int[][] grid) {
        // find beginning and zero count
        int emptyCellCount = 0, startX = -1, startY = -1;
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++) {
                int v = grid[y][x];
                if (v == 0) {
                    emptyCellCount++;
                } else if (v == 1) {
                    startX = x;
                    startY = y;
                }
            }
        // start recursive search
        return searchRecursively(grid, startY, startX, emptyCellCount);
    }

    private int searchRecursively(int[][] grid, int y, int x, int emptyCellCount) {
        if (y > -1 && y < grid.length && x > -1 && x < grid[0].length) {
            int v = grid[y][x];
            if (v == 0 || v == 1) {
                grid[y][x] = -1;
                int out = 0;
                out += searchRecursively(grid, y - 1, x, emptyCellCount - 1);
                out += searchRecursively(grid, y + 1, x, emptyCellCount - 1);
                out += searchRecursively(grid, y, x - 1, emptyCellCount - 1);
                out += searchRecursively(grid, y, x + 1, emptyCellCount - 1);
                grid[y][x] = v;
                return out;
            } else if (v == 2) { // end cell
                if ((emptyCellCount + 1) == 0) { //+ final cell
                    return 1;
                }
            }
        }
        return 0;
    }

    @Test
    public void test() {
        assertEquals(0, uniquePathsIII(new int[][]{{-1, 1}, {2, -1}}));
        assertEquals(1, uniquePathsIII(new int[][]{{2, 1}}));
        assertEquals(1, uniquePathsIII(new int[][]{{2}, {1}}));
        assertEquals(1, uniquePathsIII(new int[][]{{1, 2}}));
        assertEquals(1, uniquePathsIII(new int[][]{{1}, {2}}));
        assertEquals(0, uniquePathsIII(new int[][]{{0, 1}, {2, 0}}));
        assertEquals(1, uniquePathsIII(new int[][]{{0, 0, 1}, {2, 0, 0}}));
        assertEquals(0, uniquePathsIII(new int[][]{{0, 1, 0}, {0, 2, 0}}));
        assertEquals(0, uniquePathsIII(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 2, 0}}));
        assertEquals(2, uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}));
        assertEquals(4, uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}));
    }
}
