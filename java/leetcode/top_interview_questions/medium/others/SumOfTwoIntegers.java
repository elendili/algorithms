package leetcode.top_interview_questions.medium.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-medium/114/others/822/
Sum of Two Integers

Solution
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example 1:

Input: a = 1, b = 2
Output: 3
Example 2:

Input: a = -2, b = 3
Output: 1
 */
public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        int _sum = a ^ b;
        int carry = a & b;
        if (carry == 0) {
            return _sum;
        } else {
            return getSum(_sum, carry << 1);
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(9, getSum(7, 2));
        Assertions.assertEquals(-6, getSum(2, -8));
        Assertions.assertEquals(10, getSum(5, 5));
        Assertions.assertEquals(-10, getSum(-2, -8));
    }
}
