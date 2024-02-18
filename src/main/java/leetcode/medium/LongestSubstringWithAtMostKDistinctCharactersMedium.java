package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
public class LongestSubstringWithAtMostKDistinctCharactersMedium {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // freqs and sliding window?
        Map<Character, Integer> freqs = new HashMap<>();

        int start = 0;
        int maxWindowSize = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            freqs.compute(c, (key, v) -> v == null ? 1 : v + 1);

            if (freqs.size() > k) {
                // shrink window from the left
                freqs.computeIfPresent(s.charAt(start), (key, v) -> v == 1 ? null : v - 1);
                start++;
            }
            maxWindowSize = Math.max(maxWindowSize, end - start + 1);
        }
        return maxWindowSize;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0, lengthOfLongestSubstringKDistinct("", 1));
        Assertions.assertEquals(0, lengthOfLongestSubstringKDistinct("aa", 0));
        Assertions.assertEquals(3, lengthOfLongestSubstringKDistinct("eceba", 2));
        Assertions.assertEquals(4, lengthOfLongestSubstringKDistinct("eceba", 3));
        Assertions.assertEquals(5, lengthOfLongestSubstringKDistinct("eceba", 4));
        Assertions.assertEquals(2, lengthOfLongestSubstringKDistinct("aa", 1));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(1, lengthOfLongestSubstringKDistinct("aba", 1));
        Assertions.assertEquals(3, lengthOfLongestSubstringKDistinct("aba", 2));
        Assertions.assertEquals(1, lengthOfLongestSubstringKDistinct("abc", 1));
        Assertions.assertEquals(2, lengthOfLongestSubstringKDistinct("abb", 1));
        Assertions.assertEquals(3, lengthOfLongestSubstringKDistinct("abbb", 1));
    }
}
