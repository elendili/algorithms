package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        // dfs?
        // sliding window?
        return -1;
    }

    @Test
    public void test(){
        assertEquals(4,characterReplacement("ABAB",2));
        assertEquals(4,characterReplacement("AABABBA",1));
    }
}
