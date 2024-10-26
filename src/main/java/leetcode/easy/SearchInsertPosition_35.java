package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/search-insert-position/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class SearchInsertPosition_35 {
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        int mid;
        while (l < r) {
            mid = l + (r - l) / 2;
            int v = nums[mid];
            if (v == target) {
                return mid;
            } else if (v < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(2, searchInsert(new int[]{1, 3, 5, 6}, 5));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals(0, searchInsert(new int[]{}, 5));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(1, searchInsert(new int[]{1, 3, 5, 6}, 2));
        assertEquals(4, searchInsert(new int[]{1, 3, 5, 6}, 7));
        assertEquals(0, searchInsert(new int[]{1, 3, 5, 6}, 0));
        assertEquals(3, searchInsert(new int[]{1, 3, 5, 6}, 6));
    }
}
