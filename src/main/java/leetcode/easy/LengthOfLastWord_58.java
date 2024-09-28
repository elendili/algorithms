package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/length-of-last-word/description
 */
public class LengthOfLastWord_58 {

    public int lengthOfLastWord(String s) {
        int out = 0;
        boolean wordMet = false;
        for (int i = s.length() - 1; i > -1; i--) {
            char c = s.charAt(i);
            if (c != ' ') {
                wordMet = true;
                out++;
            } else {
                if (wordMet) {
                    return out;
                } else {
                    // skip multiple spaces
                }
            }
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(0, lengthOfLastWord("   "));
        assertEquals(1, lengthOfLastWord("a"));
        assertEquals(1, lengthOfLastWord(" a "));
        assertEquals(5, lengthOfLastWord("Hello World"));
        assertEquals(4, lengthOfLastWord("   fly me   to   the moon  "));
        assertEquals(6, lengthOfLastWord("luffy is still joyboy"));
    }
}
