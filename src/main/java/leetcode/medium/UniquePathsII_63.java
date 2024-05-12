package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/unique-paths-ii/description/
 */
public class UniquePathsII_63 {
    interface UniquePathsII {
        int uniquePathsWithObstacles(int[][] obstacleGrid);
    }

    static class UniquePathsII_63Impl implements UniquePathsII {
        @Override
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int h = obstacleGrid.length;
            int w = obstacleGrid[0].length;
            int[][] calculatedPaths = new int[h][w];
            if (obstacleGrid[0][0] == 0) {
                calculatedPaths[0][0] = 1;
            }

            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    if (obstacleGrid[row][col] > 0) {
                        continue;
                    }
                    if (row > 0) {
                        calculatedPaths[row][col] += calculatedPaths[row - 1][col];
                    }
                    if (col > 0) {
                        calculatedPaths[row][col] += calculatedPaths[row][col - 1];
                    }
                }
            }

            return calculatedPaths[h - 1][w - 1];
        }

    }

    public static Stream<UniquePathsII> implementationsSource() {
        return Stream.of(new UniquePathsII_63Impl());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void baseTest(UniquePathsII impl) {
        assertEquals(2, impl.uniquePathsWithObstacles(new int[][]{
                {0, 0, 0}, {0, 1, 0}, {0, 0, 0}
        }));
        assertEquals(1, impl.uniquePathsWithObstacles(new int[][]{
                {0, 1}, {0, 0}
        }));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(UniquePathsII impl) {
        assertEquals(3, impl.uniquePathsWithObstacles(new int[][]{
                {0, 1, 0}, {0, 0, 0}, {0, 0, 0}
        }));
        assertEquals(1, impl.uniquePathsWithObstacles(new int[][]{
                {0, 1, 0}, {0, 1, 0}, {0, 0, 0}
        }));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void testSpecial(UniquePathsII impl) {
        assertEquals(0, impl.uniquePathsWithObstacles(new int[][]{
                {1}
        }));
        assertEquals(0, impl.uniquePathsWithObstacles(new int[][]{
                {0, 1, 0}, {0, 1, 0}, {0, 1, 0}
        }));
    }
}
