package leetcode.hard;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/sliding-window-median/
 */
public class SlidingWindowMedian {
    final static Comparator<int[]> pairsComparator = Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[1]);

    // use TreeSet
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] out = new double[n - k + 1];// that's how many times window is located in array
        if (n > 0) {
            boolean even = k % 2 == 0;
            // use pairs of value and index for sorting

            // create 2 TreeSets for median search: minSet and maxSet
            TreeSet<int[]> minSet = new TreeSet<>(pairsComparator);
            TreeSet<int[]> maxSet = new TreeSet<>(pairsComparator);

            // pair[0] is value, pair[1] is index
            int[][] window = new int[k][2]; // iterate using index % k, which is index of pair to update
            for (int i = 0; i < k; i++) {
                int[] pair = new int[]{nums[i], i};
                window[i] = pair;
                minSet.add(pair);
            }

            for (int i = k; i <= n; i++) {
                balance(minSet, maxSet); // ensure invariant
                // get median
                int fromMinV = minSet.last()[0];
                double median = even ?
                        (fromMinV + ((double) maxSet.first()[0] - fromMinV) / 2)
                        : fromMinV;
                out[i - k] = median; // and put median in out
                if (i < n) {
                    // shift window
                    // remove old
                    int[] pair = window[i % k];
                    minSet.remove(pair);
                    maxSet.remove(pair);
                    balance(minSet, maxSet);
                    // update pair to make memory happy and cut corners on garbage collection
                    int newValue = nums[i];
                    pair[0] = newValue;
                    pair[1] = i;
                    if (minSet.isEmpty() || newValue <= minSet.last()[0]) {
                        minSet.add(pair);
                    } else {
                        maxSet.add(pair);
                    }

                }
            }
        }
        return out;
    }

    void balance(TreeSet<int[]> minSet, TreeSet<int[]> maxSet) {
        while (minSet.size() < maxSet.size()) {
            int[] pair = maxSet.first();
            maxSet.remove(pair);
            minSet.add(pair);
        }
        while (minSet.size() > maxSet.size() + 1) {
            int[] pair = minSet.last();
            minSet.remove(pair);
            maxSet.add(pair);
        }
    }

    @Test
    public void test() {
        assertEquals("[1.0]",
                Arrays.toString(medianSlidingWindow(new int[]{1}, 1)));

        assertEquals("[2.0]",
                Arrays.toString(medianSlidingWindow(new int[]{1, 3}, 2)));

        assertEquals("[1.0, 3.0, -1.0, 2.0]",
                Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, 2}, 1)));

        assertEquals("[2.0, 1.0, 0.5]",
                Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, 2}, 2)));

        assertEquals("[1.0, 2.0]",
                Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, 2}, 3)));

        assertEquals("[3.0, 2.0, 1.0, 1.0]",
                Arrays.toString(medianSlidingWindow(new int[]{3, 3, 1, 1, 1}, 2)));

        assertEquals("[3.0, 1.0, 1.0]",
                Arrays.toString(medianSlidingWindow(new int[]{3, 3, 1, 1, 1}, 3)));

        assertEquals("[2.0, 1.0]",
                Arrays.toString(medianSlidingWindow(new int[]{3, 3, 1, 1, 1}, 4)));

        assertEquals("[1.0, -1.0, -1.0, 3.0, 5.0, 6.0]",
                Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    @Test
    public void test2() {

        assertEquals("[-0.5]",
                Arrays.toString(medianSlidingWindow(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}, 2)));

        assertEquals("[2.147483647E9]",
                Arrays.toString(medianSlidingWindow(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, 2)));
    }

    @Test
    public void test3() {
        assertEquals("[3.0, 2.0]",
                Arrays.toString(medianSlidingWindow(new int[]{4, 2, 3, 1}, 3)));
        assertEquals("[2.0, 3.0, 3.0, 3.0, 2.0, 3.0, 2.0]",
                Arrays.toString(medianSlidingWindow(new int[]{1, 2, 3, 4, 2, 3, 1, 4, 2}, 3)));
    }

    @Test
    public void test4() {
        assertEquals("[8.0, 6.0]",
                Arrays.toString(medianSlidingWindow(new int[]{7, 0, 3, 9, 9, 9, 1}, 6)));
        assertEquals("[8.0, 6.0, 8.0, 8.0, 5.0]",
                Arrays.toString(medianSlidingWindow(new int[]{7, 0, 3, 9, 9, 9, 1, 7, 2, 3}, 6)));
    }

}
