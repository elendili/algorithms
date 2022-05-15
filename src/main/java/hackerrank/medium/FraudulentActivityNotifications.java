package hackerrank.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting&h_r=next-challenge&h_v=zen
 */
public class FraudulentActivityNotifications {
    // using TreeSet
    static int activityNotificationsWithSets(int[] expenditure, int d) {
        int out = 0;
        int n = expenditure.length;
        boolean odd = (d % 2) == 1;

        if (d < n) {// has sense only when window is smaller than array
            // let use pair as an array of 2 elements. first is value, second is index
            // create max and min sets
            Comparator<int[]> comparator = (e1, e2) -> {
                if (e1[0] == e2[0]) { // when values are equal compare indexes
                    return Integer.compare(e1[1], e2[1]);
                } else { // check value
                    return Integer.compare(e1[0], e2[0]);
                }
            };
            TreeSet<int[]> minSet = new TreeSet<>(comparator); // when d is odd minSet should be bigger on 1 element
            TreeSet<int[]> maxSet = new TreeSet<>(comparator);
            // invariant:
            // when even: minSet.size == maxSet.size
            // when odd:  minSet.size == maxSet.size+1

            // create array to track window first/last element
            // fill minSet
            int[][] pairs = new int[d][2];
            for (int i = 0; i < d; i++) {
                int[] pair = new int[]{expenditure[i], i};
                pairs[i] = pair;
                minSet.add(pair);
            }

            for (int i = d; i < n; i++) {
                balance(minSet, maxSet);// ensure the invariant
                int newElementValue = expenditure[i];
                // check median
                double median;
                if (odd) {
                    median = minSet.last()[0];
                } else {
                    median = ((double) minSet.last()[0] + maxSet.first()[0]) / 2;
                }

                // check notification condition
                if (newElementValue >= median * 2) {
                    out += 1;
                }
                //
                // move window
                int[] pair = pairs[i % d];
                //search where to remove pair
                if (minSet.contains(pair)) {
                    minSet.remove(pair);
                } else {
                    maxSet.remove(pair);
                }
                // remap pair
                pair[0] = newElementValue;
                pair[1] = i;
                if (minSet.last()[0] >= newElementValue) {
                    minSet.add(pair);
                } else {
                    maxSet.add(pair);
                }
            }
        }
        return out;
    }


    static void balance(TreeSet<int[]> minSet, TreeSet<int[]> maxSet) {
        // invariant: minSet size >= maxSet size
        while (minSet.size() < maxSet.size()) {
            int[] e = maxSet.first();
            maxSet.remove(e);
            minSet.add(e);
        }
        while (minSet.size() > maxSet.size() + 1) {
            int[] e = minSet.last();
            minSet.remove(e);
            maxSet.add(e);
        }
    }

    // using sorting
    static int activityNotificationsWithSorting(int[] expenditure, int d) {
        // get sorted array as window of size d
        // get element on the right, compare with median, incr out when condition satisfied
        // move by replacing excluded left element with right included element - use binary search
        int out = 0;
        int n = expenditure.length;
        boolean odd = (d % 2) == 1;
        if (d < n) {
            int[] window = Arrays.copyOfRange(expenditure, 0, d);
            for (int i = d; i < n; i++) {
                // sorting
                Arrays.sort(window);
                int newElementValue = expenditure[i];
                // get median
                double median = (odd ?
                        window[d / 2] :
                        ((double) window[d / 2] + window[d / 2 - 1]) / 2);
                // check notification condition
                if (newElementValue >= median * 2) {
                    out += 1;
                }
                // move window
                // search index of left element
                int excludeElementValue = expenditure[i - d];
                int indexOfExcluded = Arrays.binarySearch(window, excludeElementValue);
                // replace
                window[indexOfExcluded] = newElementValue;
            }
        }
        return out;
    }

    static Stream<BiFunction<int[], Integer, Integer>> predicateStream() {
        return Stream.of(
                FraudulentActivityNotifications::activityNotificationsWithSorting,
                FraudulentActivityNotifications::activityNotificationsWithSets
        );
    }

    @ParameterizedTest
    @MethodSource("predicateStream")
    public void test(BiFunction<int[], Integer, Integer> function) {
        Assertions.assertEquals(0,
                function.apply(new int[]{10}, 1));
        Assertions.assertEquals(0,
                function.apply(new int[]{10, 15}, 1));
        Assertions.assertEquals(1,
                function.apply(new int[]{10, 20}, 1));
        Assertions.assertEquals(1,
                function.apply(new int[]{10, 20, 30, 40}, 3));
        Assertions.assertEquals(1,
                function.apply(new int[]{10, 20, 30, 40}, 2));
        Assertions.assertEquals(1,
                function.apply(new int[]{10, 20, 30, 40, 50}, 3));

        Assertions.assertEquals(2,
                function.apply(new int[]{1, 2, 1, 2, 1, 2}, 3));
        Assertions.assertEquals(0,
                function.apply(new int[]{1, 2, 1, 2, 1, 2}, 4));
    }

    @ParameterizedTest
    @MethodSource("predicateStream")
    public void test2(BiFunction<int[], Integer, Integer> function) {
        Assertions.assertEquals(2,
                function.apply(new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5}, 5));
    }

}
