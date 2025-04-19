package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/single-row-keyboard/?envType=study-plan-v2&envId=premium-algo-100
 */
public class SingleRowKeyboard_1165 {
    public int calculateTime(String keyboard, String word) {
        int[] map = new int[26];
        char[] ak = keyboard.toCharArray();
        for (int i = 0; i < keyboard.length(); i++) {
            map[ak[i] - 'a'] = i;
        }

        int out = 0;
        int curIndex = 0;
        for (char c: word.toCharArray()) {
            int nextIndex = map[c - 'a'];
            int move = Math.abs(nextIndex - curIndex);
            out += move;
            curIndex = nextIndex;
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(4, calculateTime("abcdefghijklmnopqrstuvwxyz", "cba"));
        assertEquals(73, calculateTime("pqrstuvwxyzabcdefghijklmno", "leetcode"));
    }
}
