package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

// https://leetcode.com/problems/contains-duplicate-iii
public class ContainsDuplicateIii {
    public boolean containsNearbyAlmostDuplicate(int[] a, int diffI, int diffVal) {
        if (a.length == 0 || diffI <= 0 || a.length > 9999) {
            return false;
        }
        int n = a.length;
        int[][] aa = new int[n][2]; // v to index
        for (int i = 0; i < n; i++) {
            aa[i] = new int[]{a[i], i};
        }
        Arrays.sort(aa, Comparator.comparingInt(e -> e[0]));
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n && Math.abs(((long) aa[i][0]) - aa[j][0]) <= diffVal; j++) {
                if (Math.abs(aa[i][1] - aa[j][1]) <= diffI) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test3() {
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{MIN_VALUE, MAX_VALUE}, 1, 1));
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{MAX_VALUE, MIN_VALUE}, 1, 1));
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{MIN_VALUE, MAX_VALUE}, 1, MAX_VALUE));
    }

    @Test
    public void test2() {
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{1, 2}, 0, 0));
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{1, 2}, 0, 5));
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{1, 2}, 1, 0));
        Assertions.assertTrue(containsNearbyAlmostDuplicate(new int[]{1, 2}, 1, 1));
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{1, 3}, 1, 1));
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{1, 3, 5}, 1, 1));
        Assertions.assertTrue(containsNearbyAlmostDuplicate(new int[]{1, 3, 5}, 1, 2));
        Assertions.assertTrue(containsNearbyAlmostDuplicate(new int[]{1, 3, 1}, 2, 0));
    }

    @Test
    public void test() {
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{1, 2}, 0, 0));
        Assertions.assertTrue(containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        Assertions.assertTrue(containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        Assertions.assertTrue(containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2));
        Assertions.assertFalse(containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }
}
