package leetcode.top_interview_questions.medium.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/113/math/818/

Implement pow(x, n), which calculates x raised to the power n (i.e. xn).

Example 1:
Input: x = 2.00000, n = 10
Output: 1024.00000

Example 2:
Input: x = 2.10000, n = 3
Output: 9.26100

Example 3:
Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-231 <= n <= 231-1
-104 <= xn <= 104
 */
public class Pow {
    public double myPow(double x, int n) {
        if (n == 1) {
            return x;
        } else if (n == 0 || x == 1) {
            return 1;
        }
        long exp = Math.abs((long)n);
        double o = x;
        double out = 1;
        while (exp > 0) {
            if (exp % 2 != 0) {
                out = out * o;
            }
            exp /= 2;
            if (exp != 0) {
                o = o * o;
            }
        }
        return n < 0 ? 1 / out : out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(1024, myPow(2, 10));
        Assertions.assertEquals(9.261, myPow(2.1, 3), 0.000001);
        Assertions.assertEquals(0.25, myPow(2, -2));
    }

    @Test
    public void negX() {
        Assertions.assertEquals(4, myPow(-2, 2));
        Assertions.assertEquals(-8, myPow(-2, 3));
    }

    @Test
    public void infinity() {
        Assertions.assertEquals(Double.POSITIVE_INFINITY, myPow(0.5, Integer.MIN_VALUE));
        Assertions.assertEquals(Double.POSITIVE_INFINITY, myPow(-0.5, Integer.MIN_VALUE));
        Assertions.assertEquals(1, myPow(1, Integer.MIN_VALUE));
        Assertions.assertEquals(0, myPow(2, Integer.MIN_VALUE));
        Assertions.assertEquals(0, myPow(-2, Integer.MIN_VALUE));
        Assertions.assertEquals(Double.POSITIVE_INFINITY, myPow(2, Integer.MAX_VALUE));
    }

}
