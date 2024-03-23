package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> n1Set = new HashSet<>();
        for (int i : nums1) {
            n1Set.add(i);
        }

        List<Integer> out = new ArrayList<>();
        for (int i : nums2) {
            if (n1Set.remove(i)) {
                out.add(i);
            }
        }
        int[] a = new int[out.size()];
        int j = 0;
        for (int i : out) {
            a[j++] = i;
        }
        return a;
    }

    @Test
    public void test() {
        Assertions.assertEquals("[2]", Arrays.toString(intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        Assertions.assertEquals("[9, 4]", Arrays.toString(intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }
}
