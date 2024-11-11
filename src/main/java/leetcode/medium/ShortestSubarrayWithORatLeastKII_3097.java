package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/?envType=daily-question&envId=2024-11-11
 */
public class ShortestSubarrayWithORatLeastKII_3097 {
    int[] freqs;
    int curOr = 0;

    public int minimumSubarrayLength(int[] nums, int k) {
        if (k == 0) {
            return 1;
        }
        int minLength = Integer.MAX_VALUE;
        curOr = 0;
        freqs = new int[32];
        for (int left = 0, right = 0; right < nums.length; right++) {
            if (nums[right] >= k) {
                return 1;
            }
            // add right
            int v = nums[right];
//            System.out.println("before Add: " + curOr);
            add(v);
//            System.out.println("after Add: " + curOr);
            while (left <= right && curOr >= k) {
                minLength = Math.min(minLength, right - left + 1);
                remove(nums[left]);
                left++;
            }
//            System.out.println("after Remove: " + curOr);
        }
        if (minLength == Integer.MAX_VALUE) {
            return -1;
        }
        return minLength;
    }

    void add(int v) {
        curOr |= v;
        updateWindow(v, true);
    }


    void remove(int v) {
        int out = curOr;
        for (int i = 0; i < freqs.length; i++) {
            if (((1 << i) & v) > 0 && freqs[i] == 1) {
                out = (1 << i) ^ out;
            }
        }
        curOr = out;
        updateWindow(v, false);
    }

    void updateWindow(int v, boolean add) {
        for (int i = 0; i < freqs.length; i++) {
            if (((1 << i) & v) > 0) {
                int addendum = add ? 1 : -1;
                freqs[i] += addendum;
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(1, minimumSubarrayLength(new int[]{1, 2, 3}, 2));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(3, minimumSubarrayLength(new int[]{2, 1, 8}, 10));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals(1, minimumSubarrayLength(new int[]{1, 2}, 0));
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        assertEquals(-1, minimumSubarrayLength(new int[]{1, 2}, 1 << 30));
        assertEquals(-1, minimumSubarrayLength(new int[]{1, 1 << 29}, 1 << 30));
        assertEquals(1, minimumSubarrayLength(new int[]{1 << 30, 1 << 30}, 1 << 30));
        assertEquals(1, minimumSubarrayLength(new int[]{1 << 30, 1 << 30}, 11));
    }

    @org.junit.jupiter.api.Test
    public void test5() {
        assertEquals(5, minimumSubarrayLength(new int[]{16, 8, 4, 2, 1}, 31));
    }

    @org.junit.jupiter.api.Test
    public void test6() {
        assertEquals(3, minimumSubarrayLength(new int[]{1, 2, 32, 21}, 55));
    }
}
