package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n2 < n1) {
            return false;
        }
        // get char frequencies of first string
        int[] freqs1 = new int[26];
        for (int i = 0; i < n1; i++) {
            freqs1[s1.charAt(i) - 'a'] += 1;
        }

        // search substring with same start and end and only then create freqs
        int[] freqsW = new int[26];
        boolean added = false;
        for (int i = 0; i < n2 - n1 + 1; i++) {
            int j = i;
            for (; j < i + n1; j++) {
                // add to freqs if letter exist in etalon
                char c = s2.charAt(j);
                int ci = c - 'a';
                if (freqs1[ci] > 0) {
                    freqsW[ci] += 1;
                    added = true;
                } else {
                    // drop, because letter is not from etalon string
                    i = j;
                    break;
                }
            }
            if (j == i + n1 && Arrays.equals(freqs1, freqsW)) {
                return true;
            }
            if (added) {
                Arrays.fill(freqsW, 0);
                added = false;
            }
        }

        return false;
    }

    @Test
    public void test() {
        Assertions.assertTrue(checkInclusion("ab", "ba"));
        Assertions.assertTrue(checkInclusion("ab", "cba"));
        Assertions.assertTrue(checkInclusion("ab", "bac"));
        Assertions.assertTrue(checkInclusion("ab", "eidbaooo"));
        Assertions.assertTrue(checkInclusion("ab", "eidoba"));
        Assertions.assertTrue(checkInclusion("ab", "babaab"));

        Assertions.assertFalse(checkInclusion("ab", "eidboaoo"));
        Assertions.assertFalse(checkInclusion("ab", "kl"));
        Assertions.assertFalse(checkInclusion("ab", ""));
        Assertions.assertFalse(checkInclusion("dsfa", "dsf"));

        Assertions.assertTrue(checkInclusion("", ""));
    }
}
