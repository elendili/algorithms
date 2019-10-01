package find;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class FindClosestSumPairFrom2Arrays {

    @Test
    public  void test() {
        int[] a1 = {-1, 3, 8, 2, 9, 5};
        int[] a2 = {4, 1, 2, 10, 5, 20};
        int aTarget = 24;
        Assert.assertEquals(asList(3, 20),closestSumPair(a1, a2, aTarget));
        // should return {5, 20} or {3, 20}

        a1 = new int[]{7, 4, 1, 10};
        a2 = new int[]{4, 5, 8, 7};
        aTarget = 13;
        Assert.assertEquals(asList(4, 8),closestSumPair(a1, a2, aTarget));
        // should return {4, 8}, {7, 7}, {7, 5}, or {10, 4}

        a1 = new int[]{6, 8, -1, -8, -3};
        a2 = new int[]{4, -6, 2, 9, -3};
        aTarget = 3;
        Assert.assertEquals(asList(-1, 4),closestSumPair(a1, a2, aTarget));
        // should return {-1, 4} or {6, -3}

        a1 = new int[]{19, 14, 6, 11, -16, 14, -16, -9, 16, 13};
        a2 = new int[]{13, 9, -15, -2, -18, 16, 17, 2, -11, -7};
        aTarget = -15;
        Assert.assertEquals(asList(-16, 2),closestSumPair(a1, a2, aTarget));
        // should return {-16, 2}, {-9, -7}
    }

    // a1 and a2 are the given arrays, and target is the target sum.
    // It should return an array of two numbers as the result,
    // one from each array.
    public static List<Integer> closestSumPair(int[] a1, int[] a2, int target) {
        int[] a1Sorted = Arrays.copyOf(a1, a1.length);
        Arrays.sort(a1Sorted);
        int[] a2Sorted = Arrays.copyOf(a2, a2.length);
        Arrays.sort(a2Sorted);

        int i = 0;
        int j = a2Sorted.length - 1;
        int smallestDiff = Math.abs(a1Sorted[0] + a2Sorted[0] - target);
        int[] closestPair = {a1Sorted[0], a2Sorted[0]};

        while (i < a1Sorted.length && j >= 0 ) {
            int v1 = a1Sorted[i];
            int v2 = a2Sorted[j];
            int currentDiff = v1 + v2 - target;
            if (Math.abs(currentDiff) < smallestDiff) {
                smallestDiff = Math.abs(currentDiff);
                closestPair[0] = v1; closestPair[1] = v2;
            }

            if (currentDiff == 0) {
                break;
            } else if (currentDiff < 0) {
                i += 1;
            } else {
                j -= 1;
            }
        }

        return Arrays.stream(closestPair).boxed().collect(Collectors.toList());
    }
}
