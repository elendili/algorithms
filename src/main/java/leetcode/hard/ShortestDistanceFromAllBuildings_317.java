package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayDeque;
import java.util.Queue;

import static helpers.TestHelper.extract2dIntegerArrayFromBracketedString;

/**
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
 */
public class ShortestDistanceFromAllBuildings_317 {
    int[][] dirs = new int[][]{
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    int[][] grid;
    int[][] distances;

    public int shortestDistance(int[][] grid) {
        this.grid = grid;
        this.distances = new int[grid.length][grid[0].length];
        int emptyLandValue = 0;
        Queue<int[]> q = new ArrayDeque<>();
        int minDist = Integer.MAX_VALUE;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                int v = grid[r][c];
                if (v == 1) {
                    // START BFS WITH AVAILABILITY MAPPING AND DISTANCE MEASURE
                    minDist = Integer.MAX_VALUE;
                    q.add(new int[]{r, c});
                    int steps = 0;
                    while (!q.isEmpty()) {
                        steps++;

                        for (int level = q.size(); level > 0; level--) {
                            int[] curr = q.poll();
                            for (int[] dir : dirs) {
                                int nr = curr[0] + dir[0];
                                int nc = curr[1] + dir[1];
                                if (nr > -1 && nr < grid.length &&
                                        nc > -1 && nc < grid[0].length &&
                                        grid[nr][nc] == emptyLandValue
                                ) {
                                    grid[nr][nc] = emptyLandValue - 1;
                                    distances[nr][nc] += steps;
                                    q.add(new int[]{nr, nc});
                                    minDist = Math.min(minDist, distances[nr][nc]);
                                }
                            }
                        }
                    }
                    // Decrement empty land value to be searched in next iteration.
                    emptyLandValue--;
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected   | grid 
                    7          | [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
                    1          | [[1,0]]
                    -1         | [[1]]
                    """
    )
    public void test(int expected, String grid) {
        Assertions.assertEquals(expected,
                shortestDistance(extract2dIntegerArrayFromBracketedString(grid)));
    }
}
