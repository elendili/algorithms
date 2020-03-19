package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// https://leetcode.com/problems/insert-interval/
public class InsertInterval {
    // check if no overlaps are possible via comparing starts and ends
    // insert new interval (in beginning or ending of output) if it's the case

    // get intervals which starts inside of new interval
    // preserve intervals to further remove from output
    // get max of ends of these intervals and end of new interval
    // if no intervals found then max = end of new interval

    // get intervals which ends inside of new interval
    // preserve intervals to further remove from output
    // get min of starts of these intervals and start of new interval
    // if no intervals found then min = start of new interval

    // new interval =  [min,start]
    // iterate intervals,
    // if current interval exists among found ones before, skip it,
    // and insert new one if it first met of found intervals

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }
        int n = intervals.length;
        int[][] out;
        if (n == 0) {
            out = new int[n + 1][];
            out[0] = newInterval;
            return out;
        }

        // when new interval should be added at the end
        int start = newInterval[0];
        if (start > intervals[intervals.length - 1][1]) {
            out = new int[n + 1][];
            System.arraycopy(intervals, 0, out, 0, n);
            out[n] = newInterval;
            return out;
        }
        // when new interval should be added at the start
        int end = newInterval[1];
        if (end < intervals[0][0]) {
            out = new int[n + 1][];
            System.arraycopy(intervals, 0, out, 1, n);
            out[0] = newInterval;
            return out;
        }

        // search overlaps
        Set<int[]> overlapping = Collections.newSetFromMap(new IdentityHashMap<>());
        for (int[] x : intervals) {
            int cStart = x[0];
            int cEnd = x[1];
            if ((cStart >= start && cStart <= end)
                    || (cEnd <= end && cEnd >= start)
                    || (end <= cEnd && start >= cStart)
            ) {
                overlapping.add(x);
                end = Math.max(end, cEnd);
                start = Math.min(start, cStart);
                n--;
            }
        }
        // insert with overlaps
        out = new int[n + 1][];
        int[] toInsert = new int[]{start, end};
        int j = 0;
        boolean inserted = false;
        for (int[] x : intervals) {
            if (overlapping.isEmpty() && x[0] > end) {
                if(!inserted){
                    out[j++] = toInsert;
                    inserted = true;
                }
                out[j++] = x;
            } else if (overlapping.contains(x)) {
                if(!inserted){
                    out[j++] = toInsert;
                    inserted = true;
                }
            } else {
                out[j++] = x;
            }
        }
        return out;
    }

    @Test
    public void test() {
        assertArrayEquals(new int[][]{{1, 2}},
                insert(new int[][]{}, new int[]{1, 2}));

        assertArrayEquals(new int[][]{{1, 2}, {4, 5}},
                insert(new int[][]{{1, 2}}, new int[]{4, 5}));

        assertArrayEquals(new int[][]{{1, 2}, {4, 5}},
                insert(new int[][]{{4, 5}}, new int[]{1, 2}));

        assertArrayEquals(new int[][]{{1, 3}},
                insert(new int[][]{{2, 3}}, new int[]{1, 2}));

        assertArrayEquals(new int[][]{{1, 3}},
                insert(new int[][]{{1, 2}}, new int[]{2, 3}));

        assertArrayEquals(new int[][]{{-1, 2}},
                insert(new int[][]{{1, 2}}, new int[]{-1, 1}));

        assertArrayEquals(new int[][]{{-1, 2}},
                insert(new int[][]{{1, 2}}, new int[]{-1, 1}));

        assertArrayEquals(new int[][]{{1, 3}, {6, 9}},
                insert(new int[][]{{1, 3}, {6, 9}}, new int[]{7, 8}));

        // =====
        assertArrayEquals(new int[][]{{1, 5}, {6, 9}},
                insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}));
        assertArrayEquals(new int[][]{{1, 2}, {3, 10}, {12, 16}},
                insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8}));

        assertArrayEquals(new int[][]{{3, 5}, {6, 6}, {12, 15}},
                insert(new int[][]{{3, 5}, {12, 15}}, new int[]{6, 6}));

        assertArrayEquals(new int[][]{{0, 10}, {11, 11}, {14, 14}, {15, 20}},
                insert(new int[][]{{0, 10}, {14, 14}, {15, 20}}, new int[]{11, 11}));

    }
}
