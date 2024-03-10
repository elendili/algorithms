package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode.com/problems/find-the-difference-of-two-arrays/description/
 */
public class FindTheDifferenceOfTwoArrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> in1 = arrayToSet(nums1);
        Set<Integer> in2 = arrayToSet(nums2);
        List<Integer> existsOnlyInNums1 = existsOnlyInFirst(in1, in2);
        List<Integer> existsOnlyInNums2 = existsOnlyInFirst(in2, in1);
        return Arrays.asList(existsOnlyInNums1, existsOnlyInNums2);
    }

    Set<Integer> arrayToSet(int[] nums1) {
        Set<Integer> out = new HashSet<>();
        for (int n : nums1) {
            out.add(n);
        }
        return out;
    }

    List<Integer> existsOnlyInFirst(
            java.util.Collection<Integer> a,
            java.util.Collection<Integer> b
    ) {
        List<Integer> out = new ArrayList<>();
        for (int n : a) {
            if (!b.contains(n)) {
                out.add(n);
            }
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals("[[1, 3], [4, 6]]", findDifference(new int[]{1, 2, 3}, new int[]{2, 4, 6}).toString());
        Assertions.assertEquals("[[3], []]", findDifference(new int[]{1, 2, 3, 3}, new int[]{1, 1, 2, 2}).toString());
    }
}
