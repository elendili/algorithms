package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * https://leetcode.com/problems/word-break-ii/description/
 */
public class WordBreakII {
    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        boolean isWord = false;

        public void addWord(String s) {
            TrieNode cur = this;
            for (int i = 0; i < s.length(); i++) {
                cur = cur.children.compute(s.charAt(i), (k,v)->v==null?new TrieNode():v);
            }
            cur.isWord = true;
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        // use trie? and iterate over children of trie on word meet in recursive fashion

        // fill trie
        TrieNode root = new TrieNode();
        for (String w : wordDict) {
            root.addWord(w);
        }

        // go over word
        List<String> outList = new ArrayList<>();
        recurse(s, root, new ArrayDeque<>(), outList);
        return outList;
    }

    void recurse(String string, final TrieNode trie, Deque<String> current, List<String> out) {
        if (string.isEmpty()) {
            String toAdd = String.join(" ", current);
            out.add(toAdd);
        } else {
            TrieNode cur = trie;
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                TrieNode child = cur.children.get(c);
                if (child == null) {
                    break;
                } else if (child.isWord) {
                    String localWord = string.substring(0, i + 1);
                    current.addLast(localWord);
                    recurse(string.substring(i + 1), trie, current, out);
                    current.removeLast();
                }
                cur = child;
            }
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals("[cat sand dog, cats and dog]",
                wordBreak("catsanddog", asList("cat", "cats", "and", "sand", "dog")).toString());
    }

    @Test
    public void test2() {
        Assertions.assertEquals("[pine apple pen apple, pine applepen apple, pineapple pen apple]",
                wordBreak("pineapplepenapple", asList("apple", "pen", "applepen", "pine", "pineapple")).toString());
    }

    @Test
    public void test3() {
        Assertions.assertEquals("[]",
                wordBreak("catsandog", asList("cats", "dog", "sand", "and", "cat")).toString());
    }
}
