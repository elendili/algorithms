package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/smallest-integer-divisible-by-k/
 */
public class SmallestIntegerDivisibleByK_1015 {

    /**
     * Finds the length of the smallest positive integer n such that n consists only of 1s
     * and n is divisible by k.
     *
     * 1. If k is divisible by 2 or 5, no such n exists (since n ends in 1).
     * 2. Use modular arithmetic to track remainders: remainder = (remainder * 10 + 1) % k.
     * 3. By Pigeonhole Principle, if a remainder repeats before we hit 0, there's a cycle.
     *    Checking up to k iterations is sufficient.
     */
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }

        int remainder = 0;
        for (int length = 1; length <= k; length++) {
            remainder = (remainder * 10 + 1) % k;
            if (remainder == 0) {
                return length;
            }
        }

        return -1;
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, -1",
            "3, 3",
            "4, -1",
            "5, -1",
            "6, -1",
            "7, 6",
            "8, -1",
            "9, 9",
            "11, 2",
            "13, 6"
    })
    public void test(int k, int expected) {
        assertEquals(expected, smallestRepunitDivByK(k));
    }
}
