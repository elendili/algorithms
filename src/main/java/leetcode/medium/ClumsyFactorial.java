package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/clumsy-factorial/

Normally, the factorial of a positive integer n is the product of all positive integers less than or equal to n.
For example, factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1.

We instead make a clumsy factorial: using the integers in decreasing order, we swap out the multiply operations
for a fixed rotation of operations: multiply (*), divide (/), add (+) and subtract (-) in this order.

For example, clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1.
However, these operations are still applied using the usual order of operations of arithmetic:
we do all multiplication and division steps before any addition or subtraction steps,
and multiplication and division steps are processed left to right.

Additionally, the division that we use is floor division such that 10 * 9 / 8 equals 11.
This guarantees the result is an integer.

Implement the clumsy function as defined above: given an integer N, it returns the clumsy factorial of N.


Example 1:
Input: 4
Output: 7
Explanation: 7 = 4 * 3 / 2 + 1

Example 2:
Input: 10
Output: 12
Explanation: 12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1


Note:

1 <= N <= 10000
-2^31 <= answer <= 2^31 - 1  (The answer is guaranteed to fit within a 32-bit integer.)

 */
public class ClumsyFactorial {

    //  *  /  +  -
    public int clumsy(int N) {
        if (N < 3) {
            return N;
        }
        int[] a = new int[N + (N - 1)];
        int opi = 0;
        int ia = 0;
        for (int i = N; i > 1; --i) {
            a[ia++] = i;
            a[ia++] = -(opi + 1);
            opi = (opi + 1) % 4;
        }
        a[ia] = 1;
        //  apply * /
        for (int i = 0; i < a.length - 2; i++) {
            int d1 = a[i];
            int d2 = a[i + 1];
            int d3 = a[i + 2];
            if (d2 < 0 && d2 > -3) {
                if (d2 == -1) {
                    a[i + 2] = d1 * d3;
                } else if (d2 == -2) {
                    a[i + 2] = d1 / d3;
                }
                a[i] = 0;
                a[i + 1] = 0;
                i++;
            }
        }
        int out = 0;
        int op = 1;
        for (int d : a) {
            if (d != 0) {
                if (d > 0) {
                    out += d * op;
                } else {
                    op = (d == -4) ? -1 : 1;
                }
            }
        }
        return out;
    }

    @Test
    public void test3() {
        Assertions.assertEquals(3 * 2 / 1, clumsy(3));
    }

    @Test
    public void test4() {
        Assertions.assertEquals(7, clumsy(4));
    }

    @Test
    public void test5() {
        // 5*4/3+2-1 = 20/3 + 1 = 6+1=7
        Assertions.assertEquals(7, clumsy(5));
    }

    @Test
    public void test6() {
        // 6*5/4+3-2*1
        Assertions.assertEquals(8, clumsy(6));
    }

    @Test
    public void test7() {
        // 6*5/4+3-2*1
        Assertions.assertEquals((7 * 6 / 5 + 4 - 3 * 2 / 1), clumsy(7));
    }

    @Test
    public void test8() {
        // 6*5/4+3-2*1
        Assertions.assertEquals((8 * 7 / 6 + 5 - 4 * 3 / 2 + 1), clumsy(8));
    }

    @Test
    public void test9() {
        // 6*5/4+3-2*1
        Assertions.assertEquals((9 * 8 / 7 + 6 - 5 * 4 / 3 + 2 - 1), clumsy(9));
    }

    @Test
    public void test10() {
        Assertions.assertEquals(12, clumsy(10));
    }
}
