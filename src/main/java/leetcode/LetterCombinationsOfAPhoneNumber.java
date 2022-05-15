package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class LetterCombinationsOfAPhoneNumber {
    static class Solution {
        final static int asciiIndexOf_2 = 50;
        final static char[][] letters = new char[][]{
                "abc".toCharArray(),
                "def".toCharArray(),
                "ghi".toCharArray(),
                "jkl".toCharArray(),
                "mno".toCharArray(),
                "pqrs".toCharArray(),
                "tuv".toCharArray(),
                "wxyz".toCharArray(),
        };
        static List<String> list = new LinkedList<>();
        static StringBuilder sb = new StringBuilder();

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.isEmpty()) {
                return Collections.emptyList();
            }
            sb.setLength(0);
            list.clear();
            recursive(digits, 0, sb, list);
            return list;
        }

        void recursive(String digits, int indexInString, StringBuilder sb, List<String> out) {
            if (digits.length() == indexInString) {
                out.add(sb.toString());
            } else {
                int indexInLetters = digits.charAt(indexInString) - asciiIndexOf_2;
                for (char c : letters[indexInLetters]) {
                    sb.append(c);
                    recursive(digits, indexInString + 1, sb, out);
                    sb.deleteCharAt(indexInString);
                }
            }
        }
        @Test
        public void test() {
            int o;
            o=123;
            int[][][][][][] a = new int[1][2][3][4][5][6];
            Assertions.assertEquals(emptyList(), letterCombinations(""));
            Assertions.assertEquals(asList("a", "b", "c"), letterCombinations("2"));
            Assertions.assertEquals(asList("w", "x", "y", "z"), letterCombinations("9"));

            Assertions.assertEquals(asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), letterCombinations("23"));
            Assertions.assertEquals(Math.pow(3, 5), letterCombinations("23456").size());
            Assertions.assertEquals(Math.pow(3, 6) * 4 * 4, letterCombinations("23456789").size());
        }

    }
}
