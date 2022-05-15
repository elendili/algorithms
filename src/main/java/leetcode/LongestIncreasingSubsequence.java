package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {

    // this algo is about contiguous sequences, it's incorrect
    public int lengthOfContiguousLIS(int[] a) {
        if (a.length == 0) {
            return 0;
        }
        int prev = a[0];
        int tmp = 0, max = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < prev || i == a.length - 1) {
                max = Math.max(max, tmp);
                tmp = 0;
            } else {
                tmp += 1;
            }
            prev = a[i];
        }
        return max;
    }

    // algorithm is adopted copy from https://en.wikipedia.org/wiki/Longest_increasing_subsequence
    public int lengthOfLIS(int[] a) {
        final int n = a.length;
        if (n == 0 || n == 1) {
            return n;
        }
        // memo[j] preserves length of longest sequence and index of last element of sequence
        // j is length of sequence
        // memo[j] is index of last element
        int[] memo = new int[n + 1];
        int lastIndex = 0;
        for (int i = 0; i < n; i++) {
            int lo = 1;
            int hi = lastIndex;
            // binary search of index
            while (lo <= hi) {
                int mid = lo + ((hi - lo) / 2);
                if (a[memo[mid]] < a[i]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            int newLastIndex = lo; //
            memo[newLastIndex] = i; // save index of last element
            if (newLastIndex > lastIndex) {
                // We found a subsequence longer than any we've found yet, update last
                lastIndex = newLastIndex;
            }
        }
        return lastIndex;
    }


    @Test
    public void test() {
        assertEquals(0, lengthOfLIS(new int[]{}));
        assertEquals(1, lengthOfLIS(new int[]{1}));
        assertEquals(2, lengthOfLIS(new int[]{1, 2}));
        assertEquals(2, lengthOfLIS(new int[]{1, 0, 2}));
        assertEquals(4, lengthOfLIS(new int[]{1, 100, 2, 3, 4}));
        assertEquals(4, lengthOfLIS(new int[]{1, 3, 5, 2, 4, 6}));
        // 2,3,7,101
        // 2,3,7,18
        assertEquals(4, lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
