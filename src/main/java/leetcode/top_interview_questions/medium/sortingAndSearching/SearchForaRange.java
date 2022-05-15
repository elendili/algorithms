package leetcode.top_interview_questions.medium.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/110/sorting-and-searching/802/

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
nums is a non decreasing array.
-10^9 <= target <= 10^9
 */
public class SearchForaRange {
    public void searchBoundary(int[] a, int out[], int lo, int hi, int target, boolean leftBoundary) {
        boolean hiSet = false;
        while (lo<=hi) {
            final int mid = lo + (hi - lo) / 2;
            final int cval = a[mid];
            if (cval < target) {
                // go to right
                lo = mid+1;
            } else if (cval > target) {
                // go to left
                hi = mid-1;
            } else {
                // target is found, search boundary
                if(!hiSet) {
                    out[1] = hi;
                    hiSet=true;
                }
                if(leftBoundary){
                    out[0] = mid;
                    hi=mid-1;
                } else {
                    out[1] = mid;
                    lo=mid+1;
                }
            }
        }
    }

    public int[] searchRange(int[] a, int target) {
        int[] out = new int[]{-1, -1};
        if (a != null && a.length > 0) {
            int hi = a.length - 1;
            searchBoundary(a,out, 0,  hi, target,true);
            if(out[0]>-1){
                searchBoundary(a, out, out[0], out[1], target,false);
            }
        }
        return out;
    }


    @Test
    public void positive() {
        Assertions.assertEquals("[0, 0]", Arrays.toString(searchRange(new int[]{1}, 1)));
        Assertions.assertEquals("[0, 2]", Arrays.toString(searchRange(new int[]{1, 1, 1}, 1)));
        Assertions.assertEquals("[1, 3]", Arrays.toString(searchRange(new int[]{0, 1, 1, 1, 2}, 1)));
        Assertions.assertEquals("[2, 4]", Arrays.toString(searchRange(new int[]{0, 0, 1, 1, 1, 2, 2}, 1)));
        Assertions.assertEquals("[3, 4]", Arrays.toString(searchRange(new int[]{-1, 0, 0, 1, 1, 2, 3, 4}, 1)));
        Assertions.assertEquals("[3, 4]", Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
    @Test
    public void negative() {
        Assertions.assertEquals("[-1, -1]", Arrays.toString(searchRange(new int[]{1}, 2)));
        Assertions.assertEquals("[-1, -1]", Arrays.toString(searchRange(new int[]{1,1,1}, 2)));
        Assertions.assertEquals("[-1, -1]", Arrays.toString(searchRange(new int[]{1,1,1}, 0)));
        Assertions.assertEquals("[-1, -1]", Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
    }
}
