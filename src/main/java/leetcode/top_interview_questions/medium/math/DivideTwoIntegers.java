package leetcode.top_interview_questions.medium.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/113/math/820/

Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.

 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 1) {
            return dividend;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);

        long remainder = Math.abs((long) dividend);
        long divisor_ = Math.abs((long) divisor);

        if (remainder < divisor_) {
            return 0;
        }

        int out = 0;
        long subtrahend = divisor_;
        int divisorCount = 1;
        while (remainder >= divisor_) {
            // increase subtrahend
            if (remainder > subtrahend << 1) {
                subtrahend <<= 1; // double it
                divisorCount <<= 1; // double divisorCount
            } else
                // decrease subtrahend
                if (divisorCount >= 2 && subtrahend >= divisor) {
                    subtrahend >>= 1; // divide on 2
                    divisorCount >>= 1; // divide on 2
                }
            // apply subtraction
            if (remainder >= subtrahend) {
                remainder -= subtrahend;
                out += divisorCount;
            }
        }
        return negative ? Math.negateExact(out) : out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(3, divide(10, 3));
        Assertions.assertEquals(-3, divide(-10, 3));
        Assertions.assertEquals(4, divide(8, 2));
        Assertions.assertEquals(-2, divide(7, -3));
    }

    @Test
    public void big() {
        Assertions.assertEquals(0, divide(1, Integer.MAX_VALUE));
        Assertions.assertEquals(1, divide(Integer.MAX_VALUE, Integer.MAX_VALUE));
        Assertions.assertEquals(1, divide(Integer.MIN_VALUE, Integer.MIN_VALUE));
        Assertions.assertEquals(0, divide(Integer.MAX_VALUE, Integer.MIN_VALUE));
        Assertions.assertEquals(-1, divide(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void big3() {
        Assertions.assertEquals(-1, divide(Integer.MAX_VALUE, Integer.MIN_VALUE / 2));
    }

    @Test
    public void big4() {
        Assertions.assertEquals(Integer.MAX_VALUE / 2, divide(Integer.MAX_VALUE, 2));
    }

    @Test
    public void big5() {
        Assertions.assertEquals(Integer.MAX_VALUE / 3, divide(Integer.MAX_VALUE, 3));
    }

    @Test
    public void big6() {
        Assertions.assertEquals(Integer.MAX_VALUE, divide(Integer.MIN_VALUE, -1));
    }

    @Test
    public void big2() {
        Assertions.assertEquals(-2, divide(Integer.MIN_VALUE, Integer.MAX_VALUE / 2));
    }
}
