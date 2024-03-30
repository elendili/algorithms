package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * https://leetcode.com/problems/length-of-the-longest-valid-substring/
 */
public class LengthOfTheLongestValidSubstring {

    // try to use Trie
    public static final int alphabetSize = 26;

    static class TrieNode {
        TrieNode[] children = new TrieNode[alphabetSize];
        boolean isLeaf = false;

        TrieNode parent;

        void addWord(String word) {
            TrieNode cur = this;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                TrieNode child = cur.children[i] == null ? new TrieNode() : cur.children[i];
                cur.children[i] = child;
                child.parent = cur;
                cur = child;
            }
            cur.isLeaf = true;
        }

        // returns index of right edge of window
        public int subStringStartsFromForbidden(final String s, final int left, final int right) {
            int index = left;
            TrieNode curTrie = this;
            while (index <= right) {
                curTrie = curTrie.getTrie(s.charAt(index));
                if (curTrie == null) {
                    return right;
                } else if (curTrie.isLeaf) {
                    return index - 1;
                }
                index++;
            }
            return right;
        }

        TrieNode getTrie(char c) {
            return this.children[c - 'a'];
        }

    }

    public int longestValidSubstring(String word, List<String> forbidden) {
        TrieNode trieRoot = new TrieNode();
        for (String fWord : forbidden) {
            trieRoot.addWord(fWord);
        }
        int n = word.length();
        int maxLength = 0;

        int left = n - 1, right = n - 1;
        for (; left > -1; left--) {
            right = trieRoot.subStringStartsFromForbidden(word, left, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    @Test
    public void testStringIsForbidden() {
        Assertions.assertEquals(0, longestValidSubstring("a", asList("a")));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(2, longestValidSubstring("aa", asList("b")));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(2, longestValidSubstring("aab", asList("b")));
        Assertions.assertEquals(2, longestValidSubstring("aab", asList("ab")));
    }

    @Test
    public void test4() {
        Assertions.assertEquals(2, longestValidSubstring("baa", asList("ba")));
    }

    @Test
    public void test5() {
        Assertions.assertEquals(3, longestValidSubstring("cbaaaaa", asList("aaa", "cb")));
    }

    @Test
    public void test6() {
        Assertions.assertEquals(3, longestValidSubstring("cbaaacbaaacb", asList("aaa", "cb")));
    }

    @Test
    public void test7() {
        Assertions.assertEquals(1, longestValidSubstring("cba", asList("ba", "cb")));
        Assertions.assertEquals(1, longestValidSubstring("cba", asList("ba", "cb", "cba")));
        Assertions.assertEquals(1, longestValidSubstring("cba", asList("ba", "cb", "c", "b")));
        Assertions.assertEquals(0, longestValidSubstring("cba", asList("ba", "cb", "c", "b", "a")));
    }

    @Test
    public void test10() {
        Assertions.assertEquals(4, longestValidSubstring("cbaaaabc", asList("aaa", "cb")));
    }

    @Test
    public void test11() {
        Assertions.assertEquals(4, longestValidSubstring("leetcode", asList("de", "le", "e")));
    }

    @Test
    public void test12() {
        Assertions.assertEquals(4, longestValidSubstring("aaaabaaacc", asList("aaa", "aabaa", "baaac")));
        Assertions.assertEquals(4, longestValidSubstring("aaaabaaacc", asList("bcca", "aaa", "aabaa", "baaac")));
    }
}
