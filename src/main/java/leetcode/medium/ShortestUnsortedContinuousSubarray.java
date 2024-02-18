package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
//        find first and last index with unsorted elements, then calc distance
        int n = nums.length;

        // search min among unordered
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
            }
        }

        // check that array is already fully sorted
        if (min == Integer.MAX_VALUE) {
            return 0; // already sorted
        }

        // search max among unordered
        int max = Integer.MIN_VALUE;
        for (int i = n - 2; i > -1; i--) {
            if (nums[i] > nums[i + 1]) {
                max = Math.max(max, nums[i]);
            }
        }

        // search left boundary
        int startUnsortered, endUnsortered;
        for (startUnsortered = 0; startUnsortered < n; startUnsortered++) {
            if (min < nums[startUnsortered])
                break;
        }

        // search right boundary
        for (endUnsortered = n - 1; endUnsortered >= 0; endUnsortered--) {
            if (max > nums[endUnsortered])
                break;
        }

        return endUnsortered - startUnsortered + 1;
    }

    @Test
    public void test() {
        Assertions.assertEquals(5, findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(0, findUnsortedSubarray(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(3, findUnsortedSubarray(new int[]{3, 2, 1}));
    }

    @Test
    public void test4() {
        Assertions.assertEquals(4, findUnsortedSubarray(new int[]{3, 2, 1, 2}));
    }

    @Test
    public void test5() {
        Assertions.assertEquals(2, findUnsortedSubarray(new int[]{1, 2, 1, 2}));
        Assertions.assertEquals(4, findUnsortedSubarray(new int[]{1, 2, 1, 2, 1, 2}));
        Assertions.assertEquals(4, findUnsortedSubarray(new int[]{2, 1, 2, 1, 2, 2}));
    }

    @Test
    public void test6() {
        Assertions.assertEquals(2, findUnsortedSubarray(new int[]{0, 1, 2, 1, 4, 5}));
    }

    @Test
    public void test7() {
        Assertions.assertEquals(3, findUnsortedSubarray(new int[]{2, 3, 3, 2, 4}));
    }

    @Test
    public void test8() {
        Assertions.assertEquals(2, findUnsortedSubarray(new int[]{2, 3, 4, 3, 4}));
    }

    @Test
    public void test9() {
        Assertions.assertEquals(3, findUnsortedSubarray(new int[]{1, 2, 4, 5, 3}));
    }

}
