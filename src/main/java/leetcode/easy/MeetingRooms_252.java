package leetcode.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/meeting-rooms/?envType=study-plan-v2&envId=premium-algo-100
 */
public class MeetingRooms_252 {

    public boolean canAttendMeetings2(int[][] intervals) {
        // sort intervals by start time
        Arrays.sort(intervals, java.util.Comparator.comparingInt(a -> a[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    public boolean canAttendMeetings(int[][] intervals) {
        // sort intervals by start time
        quicksort(intervals, 0, intervals.length - 1);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    void quicksort(int[][] intervals, int low, int high) {
        if (low < high) {
            int pi = partition(intervals, low, high);
            quicksort(intervals, low, pi - 1);
            quicksort(intervals, pi + 1, high);
        }
    }

    int partition(int[][] intervals, int low, int high) {
        int pivotStart = intervals[high][0];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            int intervalStart = intervals[j][0];
            if (intervalStart <= pivotStart) {
                i++;
                // swap intervals[i] and intervals[j]
                swap(intervals, i, j);
                // check for overlap
            }
        }
        // swap intervals[i + 1] and intervals[high] (or pivot)
        swap(intervals, i + 1, high);
        return i + 1;
    }

    void swap(int[][] intervals, int i, int j) {
        int[] temp = intervals[i];
        intervals[i] = intervals[j];
        intervals[j] = temp;
    }

    public static List<Arguments> samplesProvider() {
        return List.of(
                Arguments.of(true, new int[][]{}),
                Arguments.of(true, new int[][]{{0, 1}}),
                Arguments.of(true, new int[][]{{1, 2}, {0, 1}}),
                Arguments.of(true, new int[][]{{7, 10}, {2, 4}}),
                Arguments.of(true, new int[][]{{7, 10}, {1, 1}, {2, 4}, {5, 6}}),
                Arguments.of(false, new int[][]{{1, 2}, {1, 2}}),
                Arguments.of(false, new int[][]{{1, 3}, {2, 2}}),
                Arguments.of(false, new int[][]{{1, 4}, {2, 3}}),
                Arguments.of(false, new int[][]{{1, 3}, {2, 4}}),
                Arguments.of(false, new int[][]{{1, 3}, {0, 2}}),
                Arguments.of(false, new int[][]{{1, 3}, {1, 2}}),
                Arguments.of(false, new int[][]{{15, 20}, {5, 10}, {0, 30}}),
                Arguments.of(false, new int[][]{{0,30},{60,240},{90,120}})
        );
    }

    @ParameterizedTest
    @MethodSource("samplesProvider")
    public void test(boolean expected,
                     int[][] nums) {
        assertEquals(expected, canAttendMeetings(nums));
    }

}
