package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveInterval_1272 {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> out = new ArrayList<>();
        int removeStart = toBeRemoved[0];
        int removeEnd = toBeRemoved[1];
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (end <= removeStart || start >= removeEnd) {
                // no overlap
                out.add(List.of(start, end));
            } else {
                // overlap
                if (start < removeStart) {
                    if (end > removeEnd) {
                        // [3,7] - [4,6] -> [3,4], [6,7]
                        out.add(List.of(start, removeStart));
                        int startOfSecondInterval = removeEnd > removeStart ? removeEnd : removeEnd + 1;
                        out.add(List.of(startOfSecondInterval, end));
                    } else {
                        // [3,5] - [4,6] -> [3,4]
                        // [3,5] - [5,6] -> [3,5]
                        out.add(List.of(start, removeStart));
                    }
                } else {
                    if (end > removeEnd) {
                        // [5,7] - [4,6] -> [6,7]
                        out.add(List.of(removeEnd, end));
                    } // else [5,7] - [6,8] -> no output
                }
            }
        }
        return out;
    }

    public static List<Arguments> samplesProvider() {
        return List.of(
                // [3,5] - [1,6] -> no output
                Arguments.of("[]",
                        new int[][]{{3, 5}}, new int[]{1, 6}),
                // [3,5] - [1,4] -> [4,5]
                Arguments.of("[[4,5]]",
                        new int[][]{{3, 5}}, new int[]{1, 4}),
                // [3,5] - [3,4] -> [4,5]
                Arguments.of("[[4,5]]",
                        new int[][]{{3, 5}}, new int[]{3, 4}),
                // [3,5] - [4,6] -> [3,4]
                Arguments.of("[[3,4]]",
                        new int[][]{{3, 5}}, new int[]{4, 6}),
                // [3,5] - [5,6] -> [3,5]
                Arguments.of("[[3,5]]",
                        new int[][]{{3, 5}}, new int[]{5, 6}),
                // [3,5] - [5,6] -> [3,5]
                Arguments.of("[[3,5]]",
                        new int[][]{{3, 5}}, new int[]{5, 5}),
                // [3,5] - [4,4] -> [3,4], [5,5]
                Arguments.of("[[3,4],[5,5]]",
                        new int[][]{{3, 5}}, new int[]{4, 4}),
                //
                Arguments.of("[[0,1],[6,7]]",
                        new int[][]{{0, 2}, {3, 4}, {5, 7}}, new int[]{1, 6}),
                Arguments.of("[[0,2],[3,5]]",
                        new int[][]{{0, 5}}, new int[]{2, 3}),
                Arguments.of("[[-5,-4],[-3,-2],[4,5],[8,9]]",
                        new int[][]{{-5, -4}, {-3, -2}, {1, 2}, {3, 5}, {8, 9}}, new int[]{-1, 4})
        );
    }

    @ParameterizedTest
    @MethodSource("samplesProvider")
    public void test(String expected,
                     int[][] intervals,
                     int[] toBeRemoved
    ) {
        assertEquals(expected, removeInterval(intervals, toBeRemoved)
                .toString().replaceAll(" ", ""));
    }
}
