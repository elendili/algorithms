package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/edit-distance
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 to word2.
 * * You have the following three operations permitted on a word:
 * * Insert a character
 * Delete a character
 * Replace a character
 */
public class EditDistance {

    record LevenshteinDistance(String word1, String word2, int[][] memo) {
        public LevenshteinDistance(String word1, String word2) {
            this(word1, word2, createMemo(word1, word2));
        }

        static int[][] createMemo(String word1, String word2) {
            int[][] out = new int[word1.length()][word2.length()];
            for (int i = 0; i < word1.length(); i++) {
                for (int j = 0; j < word2.length(); j++) {
                    out[i][j] = -1;
                }
            }
            return out;
        }

        int calc() {
            int out = calc(0, 0);
            return out;
        }

        int calc(int wi1, int wi2) {
            if (wi1 == word1.length()) {
                return word2.length() - wi2; // chars added to word2 to compensate length difference
            }
            if (wi2 == word2.length()) {
                return word1.length() - wi1; // chars removed from word1 to compensate length difference
            }
            if (memo[wi1][wi2] > -1) { // solution for these indexes already found
                return memo[wi1][wi2];
            }
            int v;
            if(word1.charAt(wi1) == word2.charAt(wi2)){
                v = calc(wi1 + 1, wi2 + 1);
            } else {
                int costOfWordWith1CharReplacement = calc(wi1 + 1, wi2 + 1) + 1;
                int costOfWordWith1CharInsertion = calc(wi1, wi2 + 1) + 1;
                int costOfWordWith1CharDeletion = calc(wi1 + 1, wi2) + 1;

                v = Math.min(costOfWordWith1CharReplacement, Math.min(
                        costOfWordWith1CharInsertion,
                        costOfWordWith1CharDeletion));
            }

            memo[wi1][wi2] = v;
            return v;
        }
    }

    public int minDistance(String word1, String word2) {
        return new LevenshteinDistance(word1, word2).calc();
    }

    @Test
    public void test() {
        assertEquals(3, minDistance("horse", "ros"));
        assertEquals(5, minDistance("intention", "execution"));
    }

    @Test
    public void test2() {

        assertEquals(0, minDistance("", ""));
        assertEquals(1, minDistance("", "a"));
        assertEquals(1, minDistance("a", ""));

        assertEquals(1, minDistance("a", "b"));
        assertEquals(0, minDistance("ab", "ab"));
        assertEquals(1, minDistance("ab", "ac"));
        assertEquals(1, minDistance("ab", "a"));
        assertEquals(1, minDistance("a", "ab"));


        assertEquals(2, minDistance("", "ab"));
        assertEquals(2, minDistance("ab", ""));
        assertEquals(2, minDistance("ab", "cc"));
        assertEquals(2, minDistance("abc", "a"));
        assertEquals(2, minDistance("a", "abc"));
        assertEquals(4, minDistance("dcba", "abcd"));
    }
}
