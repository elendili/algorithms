package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestCommonSubsequence_1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        // make text1 as shortest string
        if (text2.length() < text1.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        int[] previous = new int[text1.length() + 1];
        int[] current = new int[text1.length() + 1];

        for (int col = text2.length() - 1; col > -1; col--) {
            for (int row = text1.length() - 1; row > -1; row--) {
                int res;
                if (text1.charAt(row) == text2.charAt(col)) {
                    res = 1 + previous[row + 1];
                } else {
                    res = Math.max(previous[row], current[row + 1]);
                }
                current[row] = res;
            }
            // swap columns
            int[] tmp = previous;
            previous = current;
            current = tmp;
        }
        return previous[0];
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(3, longestCommonSubsequence("abcde", "ace"));
        assertEquals(3, longestCommonSubsequence("abc", "abc"));
        assertEquals(0, longestCommonSubsequence("abc", "def"));
    }
}
