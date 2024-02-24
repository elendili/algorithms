package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> windowFreq = new HashMap<>();
        int start = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            windowFreq.compute(s.charAt(end), (k, v) -> v == null ? 1 : v + 1);
            while (windowFreq.size() > 2) {
                windowFreq.computeIfPresent(s.charAt(start), (k, v) -> v == 1 ? null : v - 1);
                start++;
            }
            int curLength = end - start + 1;
            maxLength = Math.max(maxLength, curLength);
        }
        return maxLength;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0,
                lengthOfLongestSubstringTwoDistinct(""));
        Assertions.assertEquals(1,
                lengthOfLongestSubstringTwoDistinct("a"));
        Assertions.assertEquals(2,
                lengthOfLongestSubstringTwoDistinct("aa"));
        Assertions.assertEquals(3,
                lengthOfLongestSubstringTwoDistinct("eceba"));
        Assertions.assertEquals(5,
                lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }
}
