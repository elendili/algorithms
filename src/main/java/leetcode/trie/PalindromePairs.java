package leetcode.trie;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1138/
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.
 */
public class PalindromePairs {
    class Trie {
        final Trie[] alphabet = new Trie[26];
        int wordIndex = -1; // index of finished word in array
        // word indexes which suffixes are palindromes after traversed prefix
        final List<Integer> wordsIndexesWhichSuffixesArePalindromes = new ArrayList<>();

        // insert reversed
        void insertReversed(String word, int wordIndex) {
            insertReversed(word.toCharArray(), word.length() - 1, wordIndex);
        }

        private void insertReversed(char[] word, int i, int wordIndex) {
            if (i < 0) {
                // mark that parents Tries form inserted word
                this.wordIndex = wordIndex;
                // empty string (suffix) is palindrome as well
                this.wordsIndexesWhichSuffixesArePalindromes.add(wordIndex);
            } else {
                if (isPalindromeBetweenIndexes(word, 0, i)) {
                    this.wordsIndexesWhichSuffixesArePalindromes.add(wordIndex);
                }
                char c = word[i];
                Trie v = alphabet[c - 'a'];
                if (v == null) {
                    v = new Trie();
                }
                alphabet[c - 'a'] = v;
                v.insertReversed(word, i - 1, wordIndex);
            }
        }
    }

    private boolean isPalindromeBetweenIndexes(char[] word, int i, int j) {
        while (i < j) {
            if (word[i++] != word[j--]) return false;
        }
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> out = new ArrayList<>();
        if (words.length > 1) {
            // prepare Trie
            Trie root = new Trie();
            for (int i = 0; i < words.length; i++) {
                String w = words[i];
                root.insertReversed(w, i);
            }
            for (int wi = 0; wi < words.length; wi++) {
                char[] word = words[wi].toCharArray();
                searchPairsInTrie(out, root, wi, word);
            }
        }

        return out;
    }

    private void searchPairsInTrie(List<List<Integer>> out, Trie current,
                                   int wordIndex, char[] word) {
        for (int charIndex = 0; charIndex < word.length && current != null; charIndex++) {
            if (current.wordIndex > -1
                    && current.wordIndex != wordIndex
                    && isPalindromeBetweenIndexes(word, charIndex, word.length - 1)) {
                out.add(Arrays.asList(wordIndex, current.wordIndex));
            }
            current = current.alphabet[word[charIndex] - 'a'];
        }
        if (current != null && !current.wordsIndexesWhichSuffixesArePalindromes.isEmpty()) {
            for (int ci : current.wordsIndexesWhichSuffixesArePalindromes) {
                if (ci != wordIndex) {
                    out.add(Arrays.asList(wordIndex, ci));
                }
            }
        }
    }

    @Test
    public void test() {
        assertEquals(
                "[[0, 1], [1, 0], [2, 4], [3, 2]]",
                palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}).toString());
    }

    @Test
    public void test2() {
        assertEquals(
                "[[0, 1], [1, 0]]",
                palindromePairs(new String[]{"bat", "tab", "cat"}).toString());
    }

    @Test
    public void same_letter() {
        assertEquals(
                "[[0, 1], [0, 2], [1, 0], [1, 2], [2, 0], [2, 1]]",
                palindromePairs(new String[]{"a", "aa", "aaa"}).toString());
    }

    @Test
    public void test_empty() {
        assertEquals(
                "[[0, 1], [1, 0]]",
                palindromePairs(new String[]{"a", ""}).toString());
    }

    @Test
    public void test_empty2() {
        assertEquals(
                "[[0, 1], [1, 0]]",
                palindromePairs(new String[]{"", "a"}).toString());
    }
}
