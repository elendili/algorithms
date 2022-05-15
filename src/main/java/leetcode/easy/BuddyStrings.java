package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/buddy-strings/
public class BuddyStrings {
    public boolean buddyStrings(String a, String b) {
        if (a.isEmpty() || a.length() != b.length()) return false;
        int[] chars = new int[26]; // count of specific chars
        int[] diff = new int[2]; // indexes of diff elements
        int iInDiff = -1; // index in diff array
        boolean charRepetitionFound = a.length() > 26;
        for (int i = 0; i < a.length(); i++) {
            char ac = a.charAt(i);
            if (ac != b.charAt(i)) {
                if (++iInDiff < 2) {
                    diff[iInDiff] = i;
                } else {
                    return false;
                }
            } else {
                if (!charRepetitionFound) {
                    chars[ac - 'a'] += 1;
                    if (chars[ac - 'a'] > 1) {
                        charRepetitionFound = true;
                    }
                }
            }
        }
        if (iInDiff == 1) {
            return a.charAt(diff[0]) == b.charAt(diff[1])
                    && a.charAt(diff[1]) == b.charAt(diff[0]);
        }
        if (iInDiff == 0) {
            return false;
        }
        return charRepetitionFound;
    }

    @Test
    public void test() {
        Assertions.assertTrue(buddyStrings("ab", "ba"));
        Assertions.assertTrue(buddyStrings("aa", "aa"));
        Assertions.assertTrue(buddyStrings("aaaaaaabc", "aaaaaaacb"));
        Assertions.assertTrue(buddyStrings("aaaaaaabc", "aaaaaaabc"));
    }

    @Test
    public void testSmallClone() {
        Assertions.assertFalse(buddyStrings("ab", "ab"));
    }

    @Test
    public void testFalse() {
        Assertions.assertFalse(buddyStrings("abac", "abad"));
    }
}
