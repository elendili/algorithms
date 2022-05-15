package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberOfSubarraysWithBoundedMaximum {
    public int numSubarrayBoundedMax(int[] a, int L, int R) {
        int prevMax = -1;
        int inBound = -1;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > R) {
                prevMax = i;
                continue;
            }
            if (a[i] >= L) {
                inBound = i;
            }
            if (inBound > prevMax) {
                count += inBound - prevMax;
            }
        }
        return count;
    }

    @Test
    public void test() {
        Assertions.assertEquals(2,
                numSubarrayBoundedMax(new int[]{2, 1}, 2, 3));
        Assertions.assertEquals(5,
                numSubarrayBoundedMax(new int[]{3, 2, 1}, 2, 3));
        Assertions.assertEquals(3,
                numSubarrayBoundedMax(new int[]{2, 1, 0}, 2, 3));
        Assertions.assertEquals(7,
                numSubarrayBoundedMax(new int[]{3, 2, 1, 0}, 2, 3));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(3,
                numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
        Assertions.assertEquals(2,
                numSubarrayBoundedMax(new int[]{2, 4, 4, 3}, 2, 3));
        Assertions.assertEquals(7,
                numSubarrayBoundedMax(new int[]{2, 0, 0, 3}, 2, 3));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(5,
                numSubarrayBoundedMax(new int[]{4, 1, 2, 3}, 2, 3));
    }

    @Test
    public void test4() {
        Assertions.assertEquals(3,
                numSubarrayBoundedMax(new int[]{2, 3}, 2, 3));
        Assertions.assertEquals(3,
                numSubarrayBoundedMax(new int[]{2, 3, 4}, 2, 3));
        Assertions.assertEquals(3,
                numSubarrayBoundedMax(new int[]{2, 3, 4, 5}, 2, 3));
        Assertions.assertEquals(5,
                numSubarrayBoundedMax(new int[]{1, 2, 3, 4, 5}, 2, 3));
    }

    @Test
    public void test0() {
        Assertions.assertEquals(1,
                numSubarrayBoundedMax(new int[]{3}, 2, 3));
        Assertions.assertEquals(1,
                numSubarrayBoundedMax(new int[]{2}, 2, 3));
        Assertions.assertEquals(0,
                numSubarrayBoundedMax(new int[]{1}, 2, 3));
        Assertions.assertEquals(0,
                numSubarrayBoundedMax(new int[]{4}, 2, 3));
    }
}
