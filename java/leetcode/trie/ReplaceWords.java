package leetcode.trie;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/problems/replace-words/

In English, we have a concept called root, which can be followed
by some other word to form another longer word - let's call this word successor.
For example, when the root "an" is followed by the successor word "other",
we can form a new word "another".

Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root, replace it with the root that has the shortest length.

Return the sentence after the replacement.

 */
public class ReplaceWords {

    static class RecursiveTrieNode {
        final RecursiveTrieNode[] children = new RecursiveTrieNode[26];
        boolean leaf = false;

        @Override
        public String toString() {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < children.length; i++) {
                if (children[i] != null) {
                    char c = (char) ('a' + i);
                    list.add(String.valueOf(c));
                }
            }
            return String.join(",", list);
        }

        /**
         * insert word into Trie
         */
        public void insert(String word) {
            char[] ca = word.toCharArray();
            RecursiveTrieNode current = this;
            for (int i = 0; i < ca.length; i++) {
                int childIndex = ca[i] - 97;
                RecursiveTrieNode curChild = current.children[childIndex];
                RecursiveTrieNode next;
                if (curChild != null) {
                    next = curChild;
                } else {
                    next = new RecursiveTrieNode();
                }
                current.children[childIndex] = next;
                current = next;
            }
            current.leaf = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            RecursiveTrieNode tn = getLastNodeForString(word);
            return tn != null && tn.leaf;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean hasWordWhichStartsWithPrefix(String prefix) {
            return getLastNodeForString(prefix) != null;
        }

        private RecursiveTrieNode getLastNodeForString(String string) {
            char[] ca = string.toCharArray();
            RecursiveTrieNode current = this;
            for (int i = 0; i < ca.length; i++) {
                RecursiveTrieNode next = current.children[ca[i] - 97];
                if (next != null) {
                    current = next;
                } else {
                    return null;
                }
            }
            return current;
        }

        public String getSmallestPrefixForString(String string) {
            char[] ca = string.toCharArray();
            RecursiveTrieNode current = this;
            for (int i = 0; i < ca.length; i++) {
                if (current.leaf) {
                    return string.substring(0, i);
                }
                RecursiveTrieNode next = current.children[ca[i] - 97];
                if (next != null) {
                    current = next;
                } else {
                    return string;
                }
            }
            return string;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        RecursiveTrieNode ptn = new RecursiveTrieNode();
        dictionary.forEach(ptn::insert);
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String replacement=ptn.getSmallestPrefixForString(word);
            words[i] = replacement;
        }
        String out = String.join(" ", words);
        return out;
    }

    @Test
    public void test() {
        assertEquals("the cat was rat by the bat",
                replaceWords(asList("cat", "bat", "rat"),
                        "the cattle was rattled by the battery"));
    }

    @Test
    public void test2() {
        assertEquals("a a b c",
                replaceWords(asList("a", "b", "c"),
                        "aadsfasf absbs bbab cadsfafs"));
    }

    @Test
    public void test3() {
        assertEquals("a a a a a a a a bbb baba a",
                replaceWords(asList("a", "aa", "aaa", "aaaa"),
                        "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"));
    }

    @Test
    public void test4() {
        assertEquals("the cat was rat by the bat",
                replaceWords(asList("catt", "cat", "bat", "rat"),
                        "the cattle was rattled by the battery"));
    }

    @Test
    public void test5() {
        assertEquals("it is ab that this solution is ac",
                replaceWords(asList("ac", "ab"),
                        "it is abnormal that this solution is accepted"));
    }
}
