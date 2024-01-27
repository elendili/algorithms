package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * https://leetcode.com/problems/word-break/
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
public class WordBreak {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord = false;
        String val;

        @Override
        public String toString() {
            if (val == null) {
                return "root";
            }
            return val;
        }
    }

    void recursiveWordToTrie(TrieNode trie, String word) {
        if (word.isEmpty()) {
            trie.endOfWord = true;
        } else {
            char c = word.charAt(0);
            int index = c - 'a';
            TrieNode trieChild = trie.children[index];
            if (trieChild == null) {
                trieChild = new TrieNode();
                trieChild.val = "" + c;
                trie.children[index] = trieChild;
            }
            recursiveWordToTrie(trieChild, word.substring(1));
        }

    }

    TrieNode dictToTrie(List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            recursiveWordToTrie(root, word);
        }
        return root;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        TrieNode root = dictToTrie(wordDict);

        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || dp[i - 1]) {
                TrieNode curr = root;
                for (int j = i; j < s.length(); j++) {
                    char c = s.charAt(j);
                    int cIndex = c - 'a';
                    if (curr.children[cIndex]==null) {
                        // No words exist
                        break;
                    }

                    curr = curr.children[cIndex];
                    if (curr.endOfWord) {
                        dp[j] = true;
                    }
                }
            }
        }
        return dp[s.length()-1];
    }

    @Test
    public void test() {
        assertTrue(wordBreak("aba", Arrays.asList("a", "ab")));
        assertTrue(wordBreak("aba", Arrays.asList("a", "ba")));
        assertTrue(wordBreak("aba", Arrays.asList("a", "b")));
        assertFalse(wordBreak("abad", Arrays.asList("bad", "ab")));
        assertTrue(wordBreak("ab", Arrays.asList("ab")));
        assertFalse(wordBreak("ab", Arrays.asList("a", "c")));
        assertFalse(wordBreak("ab", Arrays.asList()));

        assertTrue(wordBreak("leetcode", Arrays.asList("leet", "code")));
        assertTrue(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        assertFalse(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    @Test
    public void test2() {
        assertFalse(wordBreak("aaaaaaa", Arrays.asList("aaaa", "aa")));
    }

    @Test
    public void test3() {
        assertFalse(wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
    }
}
