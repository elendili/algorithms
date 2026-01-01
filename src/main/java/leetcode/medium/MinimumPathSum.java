package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MinimumPathSum {
    interface MinPathSum {
        int minPathSum(int[][] grid);
    }

    static class RecursiveSolution implements MinPathSum {
        int globalMin = Integer.MAX_VALUE;
        int xMax = -1;
        int yMax = -1;

        public int minPathSum(int[][] grid) {
            globalMin = Integer.MAX_VALUE;
            xMax = grid.length - 1;
            yMax = grid[0].length - 1;
            recurrent(grid, 0, 0, 0);
            return globalMin;
        }

        public void recurrent(int[][] grid, int x, int y, int pathSum) {
            pathSum += grid[x][y];
            if (pathSum <= globalMin) {
                if (x == xMax && y == yMax) {
                    if (pathSum < globalMin) {
                        globalMin = pathSum;
                    }
                } else {
                    if (x < xMax && y < yMax) {
                        // choose minimal path
                        if (grid[x + 1][y] < grid[x][y + 1]) {
                            recurrent(grid, x + 1, y, pathSum);
                            recurrent(grid, x, y + 1, pathSum);
                        } else {
                            recurrent(grid, x, y + 1, pathSum);
                            recurrent(grid, x + 1, y, pathSum);
                        }
                    } else if (x < xMax) {
                        recurrent(grid, x + 1, y, pathSum);
                    } else if (y < yMax) {
                        recurrent(grid, x, y + 1, pathSum);
                    }
                }
            }
        }
    }

    static class BFSolution implements MinPathSum {

        public int minPathSum(int[][] grid) {
            int iMax = grid.length - 1;
            int jMax = grid[0].length - 1;
            // top row
            for (int j = 1; j <= jMax; j++) {
                grid[0][j] += grid[0][j - 1];
            }
            // left column
            for (int i = 1; i <= iMax; i++) {
                grid[i][0] += grid[i - 1][0];
            }
            for (int i = 1; i <= iMax; i++) {
                for (int j = 1; j <= jMax; j++) {
                    int fromLeft = grid[i][j] + grid[i][j - 1];
                    int fromTop = grid[i][j] + grid[i - 1][j];
                    grid[i][j] = Math.min(fromLeft, fromTop);
                }
            }
            return grid[iMax][jMax];
        }

    }

    public static Stream<MinPathSum> testParams() {
        return Stream.of(
                new BFSolution(),
                new RecursiveSolution()
        );
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("testParams")
    public void test(MinPathSum minPathSum) {
        Assertions.assertEquals(3, minPathSum.minPathSum(new int[][]{{1, 2}}));
        Assertions.assertEquals(1, minPathSum.minPathSum(new int[][]{{1}}));
        Assertions.assertEquals(3, minPathSum.minPathSum(new int[][]{{1}, {2}}));
        Assertions.assertEquals(7, minPathSum.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        Assertions.assertEquals(12, minPathSum.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    public static int[][] get2dArrayFrom(URL url) {
        try (InputStream is = url.openStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))
        ) {
            List<int[]> out = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                out.add(Arrays.stream(line.split(","))
                        .map(s -> s.replaceAll("\\D+", ""))
                        .mapToInt(Integer::valueOf).toArray());
            }
            int[][] a = out.toArray(new int[][]{{}});
            return a;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("testParams")
    public void testBig(MinPathSum minPathSum) {
        int[][] a = get2dArrayFrom(getClass().getResource("MinimumPathSumSample.txt"));
        Assertions.assertEquals(823, minPathSum.minPathSum(a));
    }

}