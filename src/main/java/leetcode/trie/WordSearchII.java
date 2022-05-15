package leetcode.trie;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1056/
 * <p>
 * Given an m x n board of characters and a list of strings words,
 * return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 */
public class WordSearchII {

    static class Trie {
        Trie[] alphabet = new Trie[26];
        String word; // set when backtrace Tries form words
        Trie parent; // to remove this from parent when word is found
        int childCount = 0;

        void insert(String string) {
            insert(string, 0);
        }

        void insert(String string, int i) {
            if (i == string.length()) {
                this.word = string;
            } else {
                // define child from current char
                int cindex = string.charAt(i) - 'a';
                Trie child = alphabet[cindex];
                child = child == null ? new Trie() : child;
                // define relations between parent and child
                child.parent = this;
                alphabet[cindex] = child;
                childCount += 1;
                // next step
                child.insert(string, i + 1);
            }
        }

        Trie getTrieForCharacter(char c) {
            int cindex = c - 'a';
            if (cindex > 25 || cindex < 0) {
                return null;
            }
            return alphabet[cindex];
        }

        void removeWordLeafFromTrie() {
            if (this.word != null) {
                removeWordLeafFromTrie(this.word, this.word.length());
                this.word = null;
            }
        }

        private void removeWordLeafFromTrie(String word, int i) {
            if (i > -1) {
                if (parent != null) {
                    if (this.childCount == 0) {
                        // total remove from parent when current has no child
                        parent.alphabet[word.charAt(i - 1) - 'a'] = null;
                        parent.childCount -= 1;
                    }
                    parent.removeWordLeafFromTrie(word, i - 1);
                }
            }
        }
    }

    private void findWordsOnBoard(char[][] board, int i, int j, Trie trie, List<String> out) {
        if (trie != null) {
            if (trie.word != null) {
                out.add(trie.word);
                trie.removeWordLeafFromTrie();
            }
            if (i > -1
                    && i < board.length &&
                    j > -1 &&
                    j < board[i].length
            ) {
                char c = board[i][j];
                board[i][j] = (char) (c + 100); // prevent search same char
                findWordsOnBoard(board, i + 1, j, trie.getTrieForCharacter(c), out);
                findWordsOnBoard(board, i - 1, j, trie.getTrieForCharacter(c), out);
                findWordsOnBoard(board, i, j + 1, trie.getTrieForCharacter(c), out);
                findWordsOnBoard(board, i, j - 1, trie.getTrieForCharacter(c), out);
                board[i][j] = c; // restore char
            }
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        // fill Trie with words
        Trie root = new Trie();
        for (String word : words) {
            if (word.length() <= board.length * board[0].length) {
                root.insert(word);
            }
        }
        List<String> out = new ArrayList<>();
        // iterate over every symbol of board and try to search words matching in Trie
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                findWordsOnBoard(board, i, j, root, out);
            }
        }
        return out;
    }

    @Test
    public void testTrie() {
        Trie root = new Trie();
        assertNull(root.word);
        assertEquals(0, root.childCount);
        root.removeWordLeafFromTrie();
        String word = "abc";
        root.insert(word);
        assertNull(root.word);
        Trie cur = root;
        for (char c : word.toCharArray()) {
            assertNotNull(cur = cur.getTrieForCharacter(c));
        }
        assertNotNull(cur.word);
        assertEquals(0, cur.childCount);
        assertEquals(1, root.childCount);
        cur.removeWordLeafFromTrie();
        assertEquals(0, root.childCount);
    }

    @Test
    public void test() {
        assertEquals(asList("oath", "eat"),
                findWords(new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}}, new String[]{"oath", "pea", "eat", "rain"}));
    }

    @Test
    public void test_abcb() {
        assertEquals(List.of(),
                findWords(new char[][]{{'a', 'b'}, {'c', 'd'}}, new String[]{"abcb"}));
    }

    @Test
    public void test_abdc() {
        assertEquals(asList("ac", "acdb", "ab", "abdc", "bdca", "bacd", "cabd", "cdba", "db", "dbac", "dc", "dcab"),
                findWords(new char[][]{{'a', 'b'}, {'c', 'd'}},
                        new String[]{"ab", "abdc", "ac", "acdb", "bdca", "bacd", "cabd", "cdba", "dcab", "dbac", "dc", "db", "dbd"}));
    }

    @Test
    public void testH_ab() {
        assertEquals(asList("ab", "ba"),
                findWords(new char[][]{{'a', 'b'}}, new String[]{"ab", "ba"}));
    }

    @Test
    public void testH_abab() {
        assertEquals(List.of(),
                findWords(new char[][]{{'a', 'b'}}, new String[]{"abab"}));
    }

    @Test
    public void test_a() {
        assertEquals(List.of("a"),
                findWords(new char[][]{{'a'}}, new String[]{"a", "a"}));
    }

    @Test
    public void testH_abc() {
        assertEquals(asList("a", "ab", "b", "bc", "ba", "c", "cb"),
                findWords(new char[][]{{'a', 'b', 'c'}}, new String[]{"a", "b", "c", "ab", "ba", "bc", "cb"}));
    }

    @Test
    public void testV_ab() {
        assertEquals(asList("ab", "ba"),
                findWords(new char[][]{{'a'}, {'b'}}, new String[]{"ab", "ba"}));
    }

}
