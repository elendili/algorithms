package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/description/
 */
public class RemoveColoredPiecesIfBothNeighborsAreTheSameColor {
    public boolean winnerOfGame(String colors) {
        // I think depends on amount of available turns for alice and bob
        // AAA -> 1 turn
        // AAAA -> 2 turns
        // AAAAA -> 3 turns
        // substring of one color => (length - 2) turns
        // count all substrings and their length
        int aTurns = getTurnCountFromStringForChar(colors, 'A');
        int bTurns = getTurnCountFromStringForChar(colors, 'B');
        return aTurns > bTurns;
    }

    int getTurnCountFromStringForChar(String s, char c) {
        int curLength = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                curLength++;
            } else {
                if (curLength > 2) {
                    count += (curLength - 2);
                }
                curLength = 0;
            }
        }
        if (curLength > 2) {
            count += (curLength - 2);
        }
        return count;
    }

    @Test
    public void test(){
        Assertions.assertTrue(winnerOfGame("AAABABB"));
        Assertions.assertTrue(winnerOfGame("AAABB"));
        Assertions.assertFalse(winnerOfGame("AA"));
        Assertions.assertFalse(winnerOfGame("ABBBBBBBAAA"));
    }
    @Test
    public void test2(){
        Assertions.assertFalse(winnerOfGame("AAAABBBB"));
        // 0: AAAABBBB
        // 1: AAABBBB
        // 2: AAABBB
        // 3: AABBB
        // 4: AABB
        // 5: AABB - Alice can't remove
    }
}
