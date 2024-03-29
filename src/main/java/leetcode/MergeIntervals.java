package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

//https://leetcode.com/problems/merge-intervals/submissions/
public class MergeIntervals {
    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length < 2) {
                return intervals;
            }
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

            int[] prev = intervals[0];
            int j = 0;
            for (int i = 1; i < intervals.length; i++) {
                int[] cur = intervals[i];
                if (cur[0] <= prev[1]) {
                    prev[1] = Math.max(cur[1], prev[1]);
                } else {
                    intervals[j++] = prev;
                    prev = cur;
                }
            }
            intervals[j++] = prev;

            int[][] out = new int[j][];
            System.arraycopy(intervals, 0, out, 0, j);
            return out;
        }
    }

    @Test
    public void test() {
        Solution s = new Solution();
        check("[1, 6],[8, 10],[15, 18]",
                s.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
        check("[1, 4]", s.merge(new int[][]{{1, 4}, {2, 3}}));
        check("[0, 4]", s.merge(new int[][]{{1, 4}, {0, 4}}));
        check("[0, 0],[1, 4]", s.merge(new int[][]{{1, 4}, {0, 0}}));
    }

    void check(String expected, int[][] actual) {
        String aString = Arrays.stream(actual).map(a -> Arrays.toString(a)).collect(Collectors.joining(","));
        Assertions.assertEquals(expected, aString);
    }
}
