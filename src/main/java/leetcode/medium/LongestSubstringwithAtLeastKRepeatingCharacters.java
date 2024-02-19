package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        int n = s.length();

        int[] freqs = getFreqs(s);
        // find rare symbol location in string
        int rareRightIndex = 0, rareLeftIndex = 0;
        int maxLength = 0;
        while (rareRightIndex < n) {
            // shift bot pointers while only rare symbols met
            while (rareRightIndex < n && freqs[s.charAt(rareRightIndex) - 'a'] < k) {
                rareRightIndex++;
                rareLeftIndex = rareRightIndex;
            }
            // shift only right boundary pointer while only rare symbols met
            do {
                rareRightIndex++;
            } while (rareRightIndex < n && freqs[s.charAt(rareRightIndex) - 'a'] >= k);
            // check if string end is met and beginning hasn't shifted
            if (rareLeftIndex == 0 && rareRightIndex >= n - 1) {
                maxLength = rareRightIndex - rareLeftIndex;
                break;
            }
            // recurse into substring from rareLeftIndex to rareRightIndex
            int sn = rareRightIndex - rareLeftIndex;
            if (sn >= k && sn > maxLength) {
                String substring = s.substring(rareLeftIndex, rareRightIndex);
                int recurseLength = longestSubstring(substring, k);
                maxLength = Math.max(maxLength, recurseLength);
            }
        }
        return maxLength;
    }

    int[] getFreqs(String s) {
        int[] freqs = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int ci = c - 'a';
            freqs[ci] += 1;
        }
        return freqs;
    }

    @Test
    public void test1() {
        Assertions.assertEquals(1, longestSubstring("a", 1));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(0, longestSubstring("a", 2));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(3, longestSubstring("aaabb", 3));
    }

    @Test
    public void test25() {
        Assertions.assertEquals(6, longestSubstring("ababbc", 1));
        Assertions.assertEquals(5, longestSubstring("ababbc", 2));
    }

    @Test
    public void test03() {
        Assertions.assertEquals(0, longestSubstring("ababbc", 3));
        Assertions.assertEquals(0, longestSubstring("ababbc", 4));
        Assertions.assertEquals(0, longestSubstring("ababbc", 5));
        Assertions.assertEquals(0, longestSubstring("ababbc", 6));
        Assertions.assertEquals(0, longestSubstring("ababbc", 7));
    }

    @Test
    public void test10() {
        Assertions.assertEquals(10, longestSubstring("aaaaaaaaaa", 10));
        Assertions.assertEquals(0, longestSubstring("aaaaaaaaab", 10));
        Assertions.assertEquals(0, longestSubstring("baaaaaaaaa", 10));
        Assertions.assertEquals(10, longestSubstring("bcaaaaaacb", 2));
    }

    @Test
    public void test5() {
        Assertions.assertEquals(0, longestSubstring("aabaaba", 3));
        Assertions.assertEquals(0, longestSubstring("aabaaaaba", 5));
    }

    @Test
    public void test_aabcabb() {
        Assertions.assertEquals(0, longestSubstring("aabcabb", 3));
    }
    
    @Test
    public void test_bbaaacbd() {
        Assertions.assertEquals(3, longestSubstring("bbaaacbd", 3));
    }
}
