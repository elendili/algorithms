package leetcode.top_interview_questions.medium.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/113/math/819/
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
 */
public class Sqrt {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        } else if (x < 4) {
            return 1;
        } else if (x < 9) {
            return 2;
        }
        // use binary search
        int step = 1;
        int start = 3, end = x / 3;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            long pow2 = (long) mid * mid;
            if (pow2 < x) {
                start = mid + step;
            } else if (pow2 > x) {
                end = mid - step;
            } else {
                return mid;
            }
        }
        return start - step;
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, mySqrt(3));
        Assertions.assertEquals(2, mySqrt(4));
        Assertions.assertEquals(2, mySqrt(8));
        Assertions.assertEquals(3, mySqrt(9));
        Assertions.assertEquals(3, mySqrt(10));
        Assertions.assertEquals(3, mySqrt(15));
        Assertions.assertEquals(4, mySqrt(16));
        Assertions.assertEquals(4, mySqrt(24));
        Assertions.assertEquals(5, mySqrt(25));
        Assertions.assertEquals(5, mySqrt(26));
        Assertions.assertEquals(5, mySqrt(35));
        Assertions.assertEquals(6, mySqrt(36));
        Assertions.assertEquals(10, mySqrt(100));
        Assertions.assertEquals(10, mySqrt(101));
        Assertions.assertEquals(11, mySqrt(121));
    }

    @Test
    public void big() {
        Assertions.assertEquals(46340, mySqrt(Integer.MAX_VALUE));
    }
}
