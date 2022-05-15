package hackerrank.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/lilys-homework/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
 */
public class LilysHomework {
    static int lilysHomework(int[] arr) {
        int out = 0;
        if (arr.length > 2) {
            int n = arr.length;
            int[] unsorted = Arrays.copyOf(arr, arr.length);
            int[] sorted = Arrays.copyOf(arr, arr.length);
            Arrays.sort(sorted); // sorted increasingly
            // get min swaps
            int answerAscending = minimumSwapsBetweenArrays(unsorted, sorted);
            // revert sorted array
            for (int i = 0; i < n / 2; i++) {
                swap(sorted, i, n - 1 - i);
            }
            // get min swaps for reverted sorted array
            int answerDescending = minimumSwapsBetweenArrays(arr, sorted);
            // get answer as min of 2 swap counts
            out = Math.min(answerAscending, answerDescending);
        }
        return out;
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static int minimumSwapsBetweenArrays(int[] original, int[] sorted) {
        // Idea: compare values in original and sorted arrays
        // make map with values of a[] to their indexes
        // iterate over a, if current a[i] != b[i], swap and update indexes in map, increase count
        Map<Integer, Integer> valuesToIndexes = new HashMap<>();
        for (int i = 0; i < original.length; i++) {
            valuesToIndexes.put(original[i], i);
        }
        int out = 0;
        for (int i = 0; i < original.length; i++) {
            int originalValue = original[i];
            int sortedValue = sorted[i];
            if (originalValue != sortedValue) {
                out += 1;
                // swap with element which should be on given place using map
                int indexOfSortedElement = valuesToIndexes.get(sortedValue);
                swap(original, i, indexOfSortedElement);
                // update map
                valuesToIndexes.put(sortedValue, i);
                valuesToIndexes.put(originalValue, indexOfSortedElement);
            }
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(2,
                lilysHomework(new int[]{7, 15, 12, 3}));
        Assertions.assertEquals(2,
                lilysHomework(new int[]{2, 5, 3, 1}));
    }

    @Test
    public void testEdge() {
        Assertions.assertEquals(0,
                lilysHomework(new int[]{4, 3, 2, 1}));
        Assertions.assertEquals(0,
                lilysHomework(new int[]{1, 2, 3}));
        Assertions.assertEquals(0,
                lilysHomework(new int[]{1}));
    }

    @Test
    public void testEdge2() {
        Assertions.assertEquals(1,
                lilysHomework(new int[]{1, 3, 2}));
    }
}
