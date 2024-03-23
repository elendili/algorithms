package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/largest-number/
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        // sort numbers in a way that most significant number bigger
        // than most significant number of opponent
        Comparator<Integer> comparator = this::compare;
        PriorityQueue<Integer> pq = new PriorityQueue<>(comparator);
        for (int n : nums) {
            pq.add(n);
        }
        StringBuilder sb = new StringBuilder();
        if (!pq.isEmpty() && pq.peek() == 0) {
            return "0";
        }
        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }
        return sb.toString();
    }

    int compare(int a, int b) {
        // a=9 vs b=90 ->
        // 990 vs 909
        long aFirst = merge(a, b);
        long bFirst = merge(b, a);
        return Long.compare(bFirst, aFirst);
    }

    long merge(int a, int b) {
        // 9 vs 90 ->
        // 909 vs 990
        long lb = (long) ((b == 0) ? 0 : Math.log10(b)) + 1;
        long shift = (long) Math.pow(10, lb);
        long out = a * shift + b;
        return out;
    }

    @Test
    public void test() {
        // 10^9 = 1_000_000_000
        // 2^31-1 = 2_147_483_647
        Assertions.assertEquals("21", largestNumber(new int[]{1, 2}));
        Assertions.assertEquals("10", largestNumber(new int[]{10}));
        Assertions.assertEquals("210", largestNumber(new int[]{10, 2}));
        Assertions.assertEquals("2147483647", largestNumber(new int[]{Integer.MAX_VALUE}));
    }

    @Test
    public void testMax() {
        Assertions.assertEquals("92147483647", largestNumber(new int[]{Integer.MAX_VALUE, 9}));
        Assertions.assertEquals("921474836472147483647", largestNumber(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, 9}));
    }

    @Test
    public void testZero() {
        Assertions.assertEquals("9876543210", largestNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
    }

    @Test
    public void testZeroZero() {
        Assertions.assertEquals("0", largestNumber(new int[]{0, 0}));
    }
}
