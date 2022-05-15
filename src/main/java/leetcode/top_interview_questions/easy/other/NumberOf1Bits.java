package leetcode.top_interview_questions.easy.other;
/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/99/others/565/
Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).

Note:

Note that in some languages such as Java, there is no unsigned integer type.
In this case, the input will be given as signed integer type and should not affect your implementation,
as the internal binary representation of the integer is the same whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation.
Therefore, in Example 3 above the input represents the signed integer -3.

Follow up:

If this function is called many times, how would you optimize it?
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberOf1Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }


    @Test
    public void test() {
        assertEquals( 0, hammingWeight(0));
        assertEquals( 1, hammingWeight(1));
        assertEquals( 3, hammingWeight(11));
        assertEquals( 1, hammingWeight(1 << 8));
        assertEquals( 31, hammingWeight(-3));
        assertEquals( 32, hammingWeight(-1));
    }
}
