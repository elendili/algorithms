package leetcode.top_interview_questions.medium.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/110/sorting-and-searching/800/

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

 */
public class KthLargestElementInArray {

    public int findKthLargest(final int[] a, final int k) {
        final int ki = k - 1; // required element will be in this index, because of descending order
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            final int j = partition(a, lo, hi);
            if (j < ki) {
                lo = j + 1;
            } else if (j > ki) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return a[ki];
    }

    // sort in descending order: from big to small
    private int partition(int[] a,
                          final int lo,
                          final int hi
    ) {
        int left = lo;
        int right = hi + 1;
        while (true) {
            while (left < hi && (a[++left] > a[lo])) ;
            while (right > lo && (a[lo] > a[--right])) ;
            if (left >= right) {
                break;
            }
            swap(a, left, right);
        }
        swap(a, lo, right);
        return right;
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    @Test
    public void shortTest() {
        Assertions.assertEquals(1, findKthLargest(new int[]{1}, 1));
        Assertions.assertEquals(4, findKthLargest(new int[]{4, 2}, 1));
        Assertions.assertEquals(2, findKthLargest(new int[]{4, 2}, 2));
    }

    @Test
    public void test() {
        Assertions.assertEquals(5, findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        Assertions.assertEquals(4, findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test
    public void descSort() {
        Assertions.assertEquals(7, findKthLargest(new int[]{7, 6, 5, 4, 3, 2, 1}, 1));
        Assertions.assertEquals(1, findKthLargest(new int[]{7, 6, 5, 4, 3, 2, 1}, 7));
    }

    @Test
    public void ascSort() {
        Assertions.assertEquals(7, findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7}, 1));
        Assertions.assertEquals(6, findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7}, 2));
        Assertions.assertEquals(4, findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7}, 4));
        Assertions.assertEquals(1, findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7}, 7));
    }

}
