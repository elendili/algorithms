package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Comparator;

import static helpers.TestHelper.extract2dIntegerArrayFromBracketedString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-right-interval/?envType=study-plan-v2&envId=binary-search
 */
public class FindRightInterval_436 {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            intervals[i] = new int[]{intervals[i][0], intervals[i][1], i};
        }
        Arrays.sort(intervals, Comparator.comparing(a -> a[0]));
        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            int startOfInterval = intervals[i][0];
            int endOfInterval = intervals[i][1];
            // find right
            int l = 0, r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                int midStart = intervals[mid][0];
                if (midStart >= endOfInterval) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            int oi;
            if (l >= n) {
                oi = -1;
            } else if (intervals[l] == intervals[i] && intervals[l][0] != intervals[i][1]) {
                oi = -1;
            } else {
                oi = intervals[l][2];
            }
            out[intervals[i][2]] = oi;
        }
        return out;
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|',
            value = {
                    "[-1]           | [[1,2]]",
                    "[-1,0,1]       | [[3,4],[2,3],[1,2]]",
                    "[-1,2,-1]      | [[1,4],[2,3],[3,4]]",
                    "[0]            | [[1,1]]",
                    "[0,0]          | [[1,1],[1,1]",
                    "[-1,-1]        | [[1,2],[1,2]",
            })
    // on start I get smallest from existing
    // on end I get biggest from existing
    public void test(String expected, String input) {
        assertEquals(expected, Arrays.toString(findRightInterval(extract2dIntegerArrayFromBracketedString(input))).replaceAll(" ", ""));
    }
}
