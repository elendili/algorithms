package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
https://leetcode.com/problems/count-of-range-sum/
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.
 */
public class CountOfRangeSum {
    private int upper;
    private int lower;
    private int[] nums;

    public int countRangeSum2(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j <= n; ++j)
                if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper)
                    ans++;
        return ans;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        this.nums = nums;
        // cumulative sum
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return countWithMergeSort(0, nums.length);
    }

    private int countWithMergeSort(final int left, final int right) {
        int mid = left + (right - left) / 2;
        if (mid == left) {
            return 0;
        }
        int count = countWithMergeSort(left, mid)
                + countWithMergeSort(mid, right);
        int i1 = mid, i2 = mid;
        for (int i = left; i < mid; i++) {
            int leftValue = nums[i];
            while (i1 < right && (nums[i1] - leftValue) < lower) {
                i1 += 1;
            }
            while (i2 < right && (nums[i2] - leftValue) <= upper) {
                i2 += 1;
            }
            count += (i2 - i1);
        }
        Arrays.sort(nums, left, right);
        return count;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0, countRangeSum(new int[]{-2, 5, -1}, 5, 5));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(3, countRangeSum(new int[]{-2, 5, -1}, -2, 2));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(10, countRangeSum(new int[]{1, 2, 3, 4}, 0, 20));
    }

    @Test
    public void test4() {
        Assertions.assertEquals(6, countRangeSum(new int[]{1, 2, 3}, 0, 20));
    }

    @Test
    public void test5() {
        Assertions.assertEquals(3, countRangeSum(new int[]{1, 2}, 0, 20));
    }
}
