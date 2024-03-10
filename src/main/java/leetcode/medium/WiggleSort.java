package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/wiggle-sort/description/
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                // should be minimum
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                // should be maximum
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    @Test
    public void test() {
        check("[3, 5, 1, 6, 2, 4]", new int[]{3, 5, 2, 1, 6, 4});
    }

    @Test
    public void test2() {
        check("[6, 6, 5, 6, 3, 8]", new int[]{6, 6, 5, 6, 3, 8});
    }

    @Test
    public void test3() {
        check("[0, 0, 0]", new int[]{0, 0, 0});
    }

    @Test
    public void test4() {
        check("[1, 3, 2]", new int[]{1, 2, 3});
        check("[1, 3, 2, 5, 4, 6]", new int[]{1, 2, 3, 4, 5, 6});
        check("[4, 5, 2, 3, 1]", new int[]{5, 4, 3, 2, 1});
    }

    void check(String expected, int[] input) {
        wiggleSort(input);
        Assertions.assertEquals(expected, Arrays.toString(input));
    }
}
