package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/
public class ValidPathInAGridWithStreets {
    public boolean hasValidPath(int[][] grid) {
        int yLength = grid.length;
        int xLength = grid[0].length;
        if (xLength < 2 && yLength < 2) {
            return true;
        }
        if (grid[yLength - 1][xLength - 1] == 4 || grid[0][0] == 5) {
            return false;
        }
        // only in case of 4 there are 2 ways possible which need to check.
        return iterativePathFollow(grid, 0) || (grid[0][0] == 4) && iterativePathFollow(grid, 1);
    }

    private boolean iterativePathFollow(int[][] grid, int px) {
        int yLength = grid.length;
        int xLength = grid[0].length;
        int[][] track = new int[yLength][xLength];
        int t = 1;
        int x = 0, y = 0;
        int py=0;
        while (!(py + 1 == yLength && px + 1 == xLength)) {
            int v = grid[y][x];
            track[y][x] = t++;
            // choose way
            switch (v) {
                case 1: // right <-> left
                    if (py != y) {
                        return false;
                    }
                    if (px > x) {
                        px = x;
                        py = y;
                        x--;
                    } else {
                        px = x;
                        py = y;
                        x++;
                    }
                    break;
                case 2: // bottom <-> top
                    if (px != x) {
                        return false;
                    }
                    if (py > y) {
                        px = x;
                        py = y;
                        y--;
                    } else {
                        px = x;
                        py = y;
                        y++;
                    }
                    break;
                case 3: // left <-> bottom
                    if (px > x || py < y) {
                        return false;
                    }
                    if (px == x && py < y ) { // from bottom
                        px = x;
                        py = y;
                        x--;
                    } else {         // from left
                        px = x;
                        py = y;
                        y++;
                    }
                    break;
                case 4: // right <-> bottom
                    if (px < x || py < y) {
                        return false;
                    }
                    if (py == y && px > x) { // from right
                        px = x;
                        py = y;
                        y++;
                    } else {        // from bottom
                        px = x;
                        py = y;
                        x++;
                    }
                    break;
                case 5: // left <-> top
                    if (px > x || py > y) {
                        return false;
                    }
                    if (py == y && px < x) { // from left
                        px = x;
                        y--;
                    } else {        // from top
                        px = x;
                        py = y;
                        x--;
                    }
                    break;
                case 6: // right <-> top
                    if (px < x || py > y) {
                        return false;
                    }
                    if (py == y && px > x) { // from right
                        px = x;
                        y--;
                    } else {        // from top
                        py = y;
                        px = x;
                        x++;
                    }
                    break;
            }
            // check boundaries
            if (y < 0 || x < 0 || x >= xLength || y >= yLength) {
                boolean out = py + 1 == yLength && px + 1 == xLength;
                return out;
            }
            // check loop
            if (track[y][x] > 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        Assertions.assertTrue(hasValidPath(new int[][]{{2,4,3},{6,5,2}}));
        Assertions.assertTrue(hasValidPath(new int[][]{{1,1,1,1,1,1,3}}));
        Assertions.assertTrue(hasValidPath(new int[][]{{2},{2},{2},{2},{2},{2},{6}}));
        Assertions.assertTrue(hasValidPath(new int[][]{{4,1},{6,1}}));
        Assertions.assertTrue(hasValidPath(new int[][]{{3, 4, 3, 4}, {2, 2, 2, 2}, {6, 5, 6, 5}}));
        Assertions.assertFalse(hasValidPath(new int[][]{{1, 2, 1}, {1, 2, 1}}));
        Assertions.assertFalse(hasValidPath(new int[][]{{1, 1, 2}}));
    }
}
