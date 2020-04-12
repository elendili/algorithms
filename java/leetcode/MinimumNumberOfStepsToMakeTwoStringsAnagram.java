package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
public class MinimumNumberOfStepsToMakeTwoStringsAnagram {
    class Solution {
        final static int asciiIndexOf_a = 97;
        final static int alphabetSize = 26;

        public int minSteps(String s, String t) {
            int[] a = new int[alphabetSize];
            int l = s.length();
            char[] sa = s.toCharArray();
            char[] ta = t.toCharArray();
            for (int i = 0; i < l; i++) {
                int sci = sa[i] - asciiIndexOf_a;
                int tci = ta[i] - asciiIndexOf_a;
                a[sci] += 1;
                a[tci] -= 1;
            }
            int out = 0;
            for (int n : a) {
                if (n > 0) {
                    out += n;
                }
            }
            return out;
        }
    }

    @Test
    public void test() {
        Solution s = new Solution();
        Assertions.assertEquals(1, s.minSteps("bab", "aba"));
        Assertions.assertEquals(5, s.minSteps("leetcode", "practice"));
        Assertions.assertEquals(0, s.minSteps("anagram", "mangaar"));
        Assertions.assertEquals(0, s.minSteps("xxyyzz", "xxyyzz"));
        Assertions.assertEquals(4, s.minSteps("friend", "family"));
    }
}
