package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.TestHelper.*;

public class CountNegativeNumbersInASortedMatrix_1351 {
    public int countNegatives(int[][] grid) {
        // iterate over rows, when column has negative element, then all elements below are negative
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        int l = 0, r = m;
        for (int row = 0; row < n; row++) {
            l = 0;
            while (l < r) {
                int mid = l + (r - l) / 2;
                int midV = grid[row][mid];
                if (midV > -1) {
                    // search in right part
                    l = mid + 1;
                } else {
                    // search zero in left part
                    r = mid;
                }
            }
            if (l < m && grid[row][l] < 0) {
                int newCount = m - l;
                count += newCount;
                r = l;
            }
        }
        return count;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected | grid  
                    5        | [[[4,3,2,0],[3,2,1,0],[1,1,0,-2],[-1,-1,-2,-3]]
                    8        | [[[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
                    6        | [[1,1,-1,-2],[-1,-1,-2,-3]]
                    2        | [[[4,3,2,-1],[3,2,1,-1]]
                    0        | [[3,2],[1,0]]
                    4        | [[-1,-2],[-1,-2]]
                    1        | [[0,-2]]
                    1        | [[2,1,0,-2]]
                    1        | [[0],[-2]]
                    1        | [[0,0],[0,-2]]
                    2        | [[0,-1],[0,-2]]
                    2        | [[0,0],[-1,-2]]
                    2        | [[0,0],[-1,-1]]
                    4        | [[-1,-1],[-1,-1]]
                    3        | [[1,-1],[-1,-1]]
                    """
    )
    public void test(int expected, String grid) {
        int actual = countNegatives(extract2dIntegerArrayFromBracketedString(grid));
        Assertions.assertEquals(expected, actual, grid + " -> " + actual);
    }
}
