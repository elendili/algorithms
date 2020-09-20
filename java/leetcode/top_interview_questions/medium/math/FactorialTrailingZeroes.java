package leetcode.top_interview_questions.medium.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*

https://leetcode.com/explore/featured/card/top-interview-questions-medium/113/math/816/


Given an integer n, return the number of trailing zeroes in n!.

Follow up: Could you write a solution that works in logarithmic time complexity?

Example 1:
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Example 3:

Input: n = 0
Output: 0


Constraints:

1 <= n <= 104
 */
public class FactorialTrailingZeroes {
    /*
    Intuition is amount of trailing zeroes depends on amount of 5s in input number.
    also powers of 5 affects zeroes counts as well,
    For example 125! has 31 trailing zeroes (checked in Python),
    because 125/5 + 125/25 + 125/125 = 25 + 5 + 1 = 31
     */
    public int trailingZeroes(int n) {
        int out = 0;
        while (n > 0) {
            out += n / 5;
            n = n / 5;
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0, trailingZeroes(0));
        Assertions.assertEquals(0, trailingZeroes(3));
        Assertions.assertEquals(1, trailingZeroes(5));
        Assertions.assertEquals(2, trailingZeroes(10));
        Assertions.assertEquals(2, trailingZeroes(12));
        Assertions.assertEquals(4, trailingZeroes(20));
        Assertions.assertEquals(6, trailingZeroes(25));
        Assertions.assertEquals(7, trailingZeroes(30));
        Assertions.assertEquals(8, trailingZeroes(35));

        Assertions.assertEquals(9, trailingZeroes(40));
        Assertions.assertEquals(10, trailingZeroes(45));

        Assertions.assertEquals(24, trailingZeroes(100));
        Assertions.assertEquals(31, trailingZeroes(125));
    }
}
