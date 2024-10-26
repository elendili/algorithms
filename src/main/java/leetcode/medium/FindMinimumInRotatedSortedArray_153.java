package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class FindMinimumInRotatedSortedArray_153 {
    public int findMin(int[] a) {
        // adopted binary search
        // if choose i-th element
        // if a[i-1]<a[i] - section is ordered, search min on left
        // if a[i-1]>a[i] - section is a boundary, and a[i] is a min  
        int l = 0;
        int r = a.length;
        while (l < r) {
            int mid = (r + l) / 2;
            int v = a[mid];
//            int leftV = mid == 0 ? Integer.MIN_VALUE : a[mid - 1];
            if (mid == 0 || v < a[mid - 1]) {
                return v;
            } else {
                // assume all values are uniq, then v>leftV
                // then need to understand where to search: on left or right part of array
                // if start of array is less than cur value than a[0:mid] are ordered nicely
                if (a[0] < v) {
                    // search on right, boundary should be there
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
        }
        return a[0];
    }

    @org.junit.jupiter.api.Test
    public void test0() {
        assertEquals(1, findMin(new int[]{1}));
        assertEquals(1, findMin(new int[]{1, 2}));
        assertEquals(1, findMin(new int[]{2, 1}));
        assertEquals(1, findMin(new int[]{3, 1, 2}));
        assertEquals(1, findMin(new int[]{2, 3, 1}));
        assertEquals(1, findMin(new int[]{1, 2, 3}));
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(1, findMin(new int[]{3, 4, 5, 1, 2}));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(0, findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals(11, findMin(new int[]{11, 13, 15, 17}));
    }
}
