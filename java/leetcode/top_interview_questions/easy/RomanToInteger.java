package leetcode.top_interview_questions.easy;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/102/math/878/

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

 */
public class RomanToInteger {
    private static Map<Character, Integer> romanToArabicChars = new HashMap<>();

    static {
        romanToArabicChars.put('I', 1);
        romanToArabicChars.put('V', 5);
        romanToArabicChars.put('X', 10);
        romanToArabicChars.put('L', 50);
        romanToArabicChars.put('C', 100);
        romanToArabicChars.put('D', 500);
        romanToArabicChars.put('M', 1000);
    }

    public int romanToInt(String s) {
        int out = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int cValue = romanToArabicChars.get(c);
            if (i < n - 1) {
                char nextChar = s.charAt(i + 1);
                int nextValue = romanToArabicChars.get(nextChar);
                if(nextValue>cValue){
                    cValue = nextValue-cValue;
                    i++;
                }
            }
            out += cValue;
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals(3, romanToInt("III"));
        assertEquals(4, romanToInt("IV"));
        assertEquals(9, romanToInt("IX"));
        assertEquals(58, romanToInt("LVIII"));
        assertEquals(1994, romanToInt("MCMXCIV"));
    }
}

