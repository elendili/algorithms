package leetcode.hard;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/sliding-window-median/
public class SlidingWindowMedian_again {
    static class MedianWindow {
        private final TreeMap<Integer, Integer> smallerValuesMap = new TreeMap<>();
        private final TreeMap<Integer, Integer> biggerValuesMap = new TreeMap<>();
        private int smallerCount;
        private int biggerCount;

        void add(int val) {
            if (count() == 0 || val > median()) {
                biggerValuesMap.compute(val, (k, v) -> v == null ? 1 : v + 1);
                biggerCount++;
            } else {
                smallerValuesMap.compute(val, (k, v) -> v == null ? 1 : v + 1);
                smallerCount++;
            }
            normalize();
        }

        private int count() {
            return smallerCount + biggerCount;
        }

        private void normalize() {
            while (biggerCount > smallerCount) {
                Map.Entry<Integer, Integer> e = biggerValuesMap.pollFirstEntry();
                smallerValuesMap.compute(e.getKey(), (k, v) -> v == null ? 1 : v + 1);
                smallerCount++;
                biggerCount--;
                if (e.getValue() > 1) {
                    biggerValuesMap.compute(e.getKey(), (k, v) -> e.getValue() - 1);
                }
            }
            while (biggerCount + 1 < smallerCount) {
                Map.Entry<Integer, Integer> e = smallerValuesMap.pollLastEntry();
                biggerValuesMap.compute(e.getKey(), (k, v) -> v == null ? 1 : v + 1);
                smallerCount--;
                biggerCount++;
                if (e.getValue() > 1) {
                    smallerValuesMap.compute(e.getKey(), (k, v) -> e.getValue() - 1);
                }
            }
        }

        void remove(int val) {
            if (count() > 0) {
                if (val > median()) {
                    biggerValuesMap.compute(val, (k, v) -> (v == null || v == 1) ? null : v - 1);
                    biggerCount--;
                } else {
                    smallerValuesMap.compute(val, (k, v) -> (v == null || v == 1) ? null : v - 1);
                    smallerCount--;
                }
                normalize();
            }
        }

        double median() {
            assert count() > 0 : "empty window";
            if (count() % 2 == 0) {
                int smallOne = smallerValuesMap.lastKey();
                int bigOne = biggerValuesMap.firstKey();
                return smallOne + ((long) bigOne - smallOne) / 2d;
            } else {
                return smallerValuesMap.lastKey();
            }
        }

    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (k < 1 || nums == null || nums.length == 0) {
            return new double[]{};
        }
        if (nums.length == 1) {
            return new double[]{nums[0]};
        }

        int n = nums.length;
        double[] out = new double[n - k + 1];
        // init window
        MedianWindow mw = new MedianWindow();
        for (int i = 0; i < k; i++) {
            mw.add(nums[i]);
        }
        out[0] = mw.median();
        // slide window
        for (int i = k; i < nums.length; i++) {
            mw.add(nums[i]);
            mw.remove(nums[i - k]);
            out[i - k + 1] = mw.median();
        }
        return out;
    }

    @Test
    public void test() {
        assertArrayEquals(new double[]{
                        -2147483648.00000, -2147483648.00000, -2147483648.00000, -2147483648.00000, -2147483648.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, -2147483648.00000
                },
                medianSlidingWindow(new int[]{-2147483648, -2147483648,
                        2147483647, -2147483648,
                        -2147483648, -2147483648,
                        2147483647, 2147483647,
                        2147483647, 2147483647,
                        -2147483648, 2147483647,
                        -2147483648}, 3));
        assertArrayEquals(new double[]{2147483647},
                medianSlidingWindow(new int[]{2147483647, 2147483647}, 2));
        assertArrayEquals(new double[]{1, -1, -1, 3, 5, 6},
                medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    @Test
    public void testMedianWindowAdd() {
        MedianWindow mw = new MedianWindow();
        mw.add(5);
        assertEquals(5, mw.median()); // [5]
        mw.add(4);
        assertEquals(4.5, mw.median()); // [4,5]
        mw.add(6);
        assertEquals(5, mw.median()); // [4,5,6]
        mw.add(7);
        assertEquals(5.5, mw.median()); // [4,5,6,7]
        mw.add(9);
        assertEquals(6, mw.median()); // [4,5,6,7,9]
    }

    @Test
    public void testMedianWindowAddDupes() {
        MedianWindow mw = new MedianWindow();
        mw.add(4);
        assertEquals(4, mw.median());
        mw.add(4);
        assertEquals(4, mw.median());
        mw.add(5);
        assertEquals(4, mw.median());
        mw.add(5);
        assertEquals(4.5, mw.median());
        mw.add(5);
        assertEquals(5, mw.median());
        mw.add(5);
        assertEquals(5, mw.median());
    }

    @Test
    public void testMedianWindowAddRemove() {
        MedianWindow mw = new MedianWindow();
        mw.add(4);
        mw.add(5);
        mw.remove(5);
        assertEquals(4, mw.median()); // [4]
        mw.add(5);
        assertEquals(4.5, mw.median()); // [4,5]
        mw.remove(4);
        assertEquals(5, mw.median()); // [5]
        mw.add(5);
        mw.add(4);
        assertEquals(5, mw.median());  // [4,5,5]
        mw.add(4);
        assertEquals(4.5, mw.median()); // [4,4,5,5]
        mw.add(6);
        assertEquals(5, mw.median()); // [4,4,5,5,6]
        mw.remove(4);
        assertEquals(5, mw.median()); // [4,5,5,6]
        mw.remove(4);
        assertEquals(5, mw.median()); // [5,5,6]
        mw.remove(5);
        assertEquals(5.5, mw.median()); // [5,6]
        mw.remove(5);
        assertEquals(6, mw.median()); // [5,6]
    }
}
