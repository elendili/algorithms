package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static helpers.TestHelper.extract2dIntegerArrayFromBracketedString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/number-of-distinct-islands/?envType=study-plan-v2&envId=premium-algo-100
 */
public class NumberOfDistinctIslands_694 {
    int[][] grid;

    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        int count = 0;
        Set<String> islandSchemes = new HashSet<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 1) {
                    // process island
                    StringBuilder islandScheme = new StringBuilder();
                    discoverIsland(y, x, 'o', islandScheme);
                    if (islandSchemes.add(islandScheme.toString())) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    void discoverIsland(int y, int x, char d, StringBuilder islandScheme) {
        if (y >= grid.length || x >= grid[0].length || y < 0 || x < 0 || grid[y][x] == 0) {
            return;
        }
        grid[y][x] = 0; // to avoid visiting again
        islandScheme.append(d);
        discoverIsland(y + 1, x, 'd', islandScheme);
        discoverIsland(y - 1, x, 'u', islandScheme);
        discoverIsland(y, x + 1, 'r', islandScheme);
        discoverIsland(y, x - 1, 'l', islandScheme);
        islandScheme.append('b');
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected | grid
                    0        | []
                    1        | [[1]]
                    1        | [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
                    3        | [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
                    2        | [[1,1,0],[0,1,1],[0,0,0],[1,1,1],[0,1,0]]
                    """
    )
    public void test(int expected, String grid) {
        assertEquals(expected,
                numDistinctIslands(extract2dIntegerArrayFromBracketedString(grid)));
    }
}
