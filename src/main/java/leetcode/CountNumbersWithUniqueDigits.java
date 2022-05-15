package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/count-numbers-with-unique-digits/
    /*
     Numbers with unique digits for powers of 10:
     0   1
     1   1 + 9
     2   1 + 9 + 9*9
     3   1 + 9 + 9*9 + 9*9*8 = 739
     4   1 + 9 + 9*9 + 9*9*8 + 9*9*8*7 = 5275
     5   1 + 9 + 9*9 + 9*9*8 + 9*9*8*7 + 9*9*8*7*6 = 32491
     6   1 + 9 + 9*9 + 9*9*8 + 9*9*8*7 + 9*9*8*7*6 + 9*9*8*7*6*5 = 168571
     7   1 + 9 + 9*9 + 9*9*8 + 9*9*8*7 + 9*9*8*7*6 + 9*9*8*7*6*5 + 9*9*8*7*6*5*4 = 712891
     8   1 + 9 + 9*9 + 9*9*8 + 9*9*8*7 + 9*9*8*7*6 + 9*9*8*7*6*5 + 9*9*8*7*6*5*4 + 9*9*8*7*6*5*4*3 =
     9   1 + 9 + 9*9 + 9*9*8 + 9*9*8*7 + 9*9*8*7*6 + 9*9*8*7*6*5 + 9*9*8*7*6*5*4 + 9*9*8*7*6*5*4*3 + 9*9*8*7*6*5*4*3*2 =
    10   1 + 9 + 9*9 + 9*9*8 + 9*9*8*7 + 9*9*8*7*6 + 9*9*8*7*6*5 + 9*9*8*7*6*5*4 + 9*9*8*7*6*5*4*3 + 9*9*8*7*6*5*4*3*2 + 9*9*8*7*6*5*4*3*2*1 =

     */
public class CountNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        int out = 0;
        n = Math.min(n, 10);
        for (int i = 0; i <= n; i++) {
            out += combinations(i);
        }
        return out;
    }

    private final static int[] memo = new int[11];

    static {
        memo[0] = 1;
        memo[1] = 9;
    }

    private static int combinations(int n) {
        if (memo[n] > 0) {
            return memo[n];
        }
        memo[n] = (11 - n) * combinations(n - 1);
        return memo[n];
    }


    @Test
    public void test() {
        assertEquals(1, countNumbersWithUniqueDigits(0));
        assertEquals(10, countNumbersWithUniqueDigits(1));
        assertEquals(91, countNumbersWithUniqueDigits(2));
        assertEquals(739, countNumbersWithUniqueDigits(3));
        assertEquals(5275, countNumbersWithUniqueDigits(4));
        assertEquals(32491, countNumbersWithUniqueDigits(5));
        assertEquals(168571, countNumbersWithUniqueDigits(6));
        assertEquals(712891, countNumbersWithUniqueDigits(7));
        assertEquals(2345851, countNumbersWithUniqueDigits(8));
        assertEquals(5611771, countNumbersWithUniqueDigits(9));
        assertEquals(8877691, countNumbersWithUniqueDigits(10));
        assertEquals(8877691, countNumbersWithUniqueDigits(11));
    }
}
