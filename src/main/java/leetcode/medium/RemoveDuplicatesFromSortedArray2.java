package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii
 Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 */
public class RemoveDuplicatesFromSortedArray2 {

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return n;
        }
        int outCount = n;
        int dupCount = 1;
        for (int i = 1, j = 1; i < n; i++, j++) {
            if (nums[i] == nums[i - 1]) {
                dupCount++;
                if (dupCount >= 3) {
                    j--;
                    dupCount--;
                    outCount--;
                }
            } else {
                dupCount = 1;
            }
            nums[j] = nums[i];
        }
        return outCount;
    }

    @Test
    public void test() {
        int[] a;

        a = new int[]{};
        assertEquals(0, removeDuplicates(a));
        a = new int[]{1};
        assertEquals(1, removeDuplicates(a));
        a = new int[]{3, 3, 3};
        assertEquals(2, removeDuplicates(a));
        assertEquals("[3, 3, 3]", Arrays.toString(a));
        a = new int[]{1, 1, 1, 2, 2, 3};
        assertEquals(5, removeDuplicates(a));
        assertEquals("[1, 1, 2, 2, 3, 3]", Arrays.toString(a));

        a = new int[]{0, 0, 0, 0, 1, 2};
        assertEquals(4, removeDuplicates(a));
        assertEquals("[0, 0, 1, 2, 1, 2]", Arrays.toString(a));

        a = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        assertEquals(7, removeDuplicates(a));
        assertEquals("[0, 0, 1, 1, 2, 3, 3, 3, 3]", Arrays.toString(a));

        a = new int[]{0, 0, 0, 1, 2, 3};
        assertEquals(5, removeDuplicates(a));
        assertEquals("[0, 0, 1, 2, 3, 3]", Arrays.toString(a));
    }
}
