package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/integer-to-roman
 */
public class IntegerToRoman {
    /*
    Symbol       Value
    I             1               0
    V             5               1
    X             10              2
    L             50              3
    C             100             4
    D             500             5
    M             1000            6


    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.
     */

    record Roman(int value, String repr) {
    }

    static final Roman[] romans = {
            new Roman(1, "I"),
            new Roman(4, "IV"),
            new Roman(5, "V"),
            new Roman(9, "IX"),
            new Roman(10, "X"),
            new Roman(40, "XL"),
            new Roman(50, "L"),
            new Roman(90, "XC"),
            new Roman(100, "C"),
            new Roman(400, "CD"),
            new Roman(500, "D"),
            new Roman(900, "CM"),
            new Roman(1000, "M"),
    };

    public String intToRoman(int num) {
        // divide on max (1000), if res>=1 then add res*char to out
        int remainder = num;
        StringBuilder sb = new StringBuilder();
        for (int i = romans.length - 1; i > -1; i--) {
            Roman roman = romans[i];
            int divider = roman.value;
            int count = remainder / divider;
            if (count == 0) {
                continue;
            }
            for (int j = 0; j < count; j++) {
                sb.append(roman.repr);
            }
            remainder = remainder % divider;
        }
        return sb.toString();
    }

    @Test
    public void test() {
        Assertions.assertEquals("III", intToRoman(3));
        Assertions.assertEquals("LVIII", intToRoman(58));
    }

    @Test
    public void testIV() {
        Assertions.assertEquals("IV", intToRoman(4));
    }

    @Test
    public void testMCM() {
        Assertions.assertEquals("MCM", intToRoman(1900));
    }

    @Test
    public void testIX() {
        Assertions.assertEquals("IX", intToRoman(9));
    }

    @Test
    public void testMCMXCIV() {
        Assertions.assertEquals("MCMXCIV", intToRoman(1994));
    }

    @Test
    public void testMMMCMXCIX() {
        Assertions.assertEquals("MMMCMXCIX", intToRoman(3999));
    }
}
