package leetcode.top_interview_questions.medium.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/110/sorting-and-searching/804/
You are given an integer array nums sorted in ascending order, and an integer target.

Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

If target is found in the array return its index, otherwise, return -1.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1

Constraints:

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is guranteed to be rotated at some pivot.
-10^4 <= target <= 10^4
 */
public class SearchRotatedSortedArray {
    public int search(int[] a, int t) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            final int mid = lo + (hi - lo) / 2;
            final int lv = a[lo];
            final int mv = a[mid];
            final int hv = a[hi];
            if (mv == t) {
                return mid;
            } else if(t==hv) {
                return hi;
            } else if(t==lv) {
                return lo;
            } else if (lv < mv) {
                // left part is sorted
                 if(t<mv && t>lv){ // target in left range
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                // right part is sorted
                if(t>mv && t<hv){// target in right range
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0, search(new int[]{1}, 1));
        Assertions.assertEquals(0, search(new int[]{3, 1, 2}, 3));

        Assertions.assertEquals(1, search(new int[]{3, 1, 2}, 1));
        Assertions.assertEquals(2, search(new int[]{3, 1, 2}, 2));
        Assertions.assertEquals(4, search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));

        Assertions.assertEquals(-1, search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        Assertions.assertEquals(-1, search(new int[]{1}, 0));
    }
}
