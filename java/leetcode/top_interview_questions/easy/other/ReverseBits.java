package leetcode.top_interview_questions.easy.other;
/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/99/others/565/

Reverse bits of a given 32 bits unsigned integer.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        printBinaryAligned(n);
        int out =  Integer.reverse(n);;
        printBinaryAligned(out);
        return out;
    }

    static void printBinaryAligned(int n){
        System.out.println(n+":\t" + String.format("%32s",
                Integer.toBinaryString(n)).replaceAll(" ", "0"));
    }

    @Test
    public void test() {
        assertEquals( -2147483648, reverseBits(1));
        assertEquals( 964176192, reverseBits(43261596));
        assertEquals( -1073741825, reverseBits(-3));
    }
}
