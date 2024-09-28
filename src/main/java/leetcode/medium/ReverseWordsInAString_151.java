package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string
 */
public class ReverseWordsInAString_151 {

    public String reverseWords(String s) {
        int n = s.length();
        // read from end, on meet ' ' or string end
        // put word backwards into stringbuilder
        boolean wordMet = false;
        int wordStartIndex = n - 1;
        int wordEndIndex = n;
        StringBuilder out = new StringBuilder(n);
        for (int i = n - 1; i >= -1; i--) {
            if (i == -1 || s.charAt(i) == ' ') {// not a word or start of the string
                // process dropping word
                if (wordMet) {
                    String word = s.substring(wordStartIndex, wordEndIndex);
                    if(!out.isEmpty()){
                        out.append(' ');
                    }
                    out.append(word);
                }
                wordMet = false;
            } else {
                // iterate over word
                if (!wordMet) {
                    wordMet = true;
                    wordStartIndex = i;
                    wordEndIndex = i + 1;
                } else {
                    wordStartIndex = i;
                }
            }
        }
        return out.toString();
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("blue is sky the", reverseWords("the sky is blue"));
        assertEquals("world hello", reverseWords("  hello world  "));
        assertEquals("example good a", reverseWords("a good   example"));
    }
}
