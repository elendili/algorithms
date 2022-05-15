package leetcode.top_interview_questions.easy.other;
/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/99/others/762/
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 2^31.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HammingDistance {
    // you need to treat n as an unsigned value
    public int hammingDistance(int x, int y) {
        int xored =x^y;
        return Integer.bitCount(xored);
    }


    @Test
    public void test() {
        assertEquals( 0, hammingDistance(4,4));
        assertEquals( 1, hammingDistance(1,3));
        assertEquals( 2, hammingDistance(1,4));
    }
}
