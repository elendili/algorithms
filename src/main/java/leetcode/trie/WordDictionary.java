package leetcode.trie;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * <p>
 * Implement the WordDictionary class:
 * <p>
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 * https://leetcode.com/explore/learn/card/trie/148/practical-application-i/1052
 */
public class WordDictionary {

    WordDictionary[] children;
    /**
     * means end of inserted word,
     * current instance of class doesn't have a letter from this word,
     * only parents have
     */
    boolean leaf = false;

    public WordDictionary() {
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < children().length; i++) {
            if (children[i] != null) {
                char c = (char) ('a' + i);
                list.add(String.valueOf(c));
            }
        }
        return "[" + String.join(",", list) + "]";
    }

    /**
     * create dict when necessary for lower case alphabet
     */
    private WordDictionary[] children() {
        if (children == null) {
            children = new WordDictionary[26];
        }
        return children;
    }

    public void addWord(String word) {
        if (word.length() > 0) {
            int i = word.charAt(0) - 'a';
            WordDictionary child = children()[i];
            if (child == null) {
                child = new WordDictionary();
                children[i] = child;
            }
            child.addWord(word.substring(1));
        } else {
            leaf = true; // word is ended before
        }
    }

    public boolean search(String word) {
        if (word.length() > 0) {
            char c = word.charAt(0);
            if (c == '.') {
                boolean out = false;
                for (WordDictionary child : children()) {
                    if (child != null && child.search(word.substring(1))) {
                        out = true;
                        break;
                    }
                }
                return out;
            } else {
                int i = c - 'a';
                WordDictionary child = children()[i];
                if (child != null) {
                    return child.search(word.substring(1));
                }
            }
        } else {
            return leaf;
        }
        return false;
    }

    @Test
    public void test() {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.addWord("qaqaqa");
        assertTrue(wordDictionary.search("bad"));
        assertFalse(wordDictionary.search("pad"));
        assertTrue(wordDictionary.search(".ad"));
        assertTrue(wordDictionary.search("b.."));
        assertFalse(wordDictionary.search("qaq"));
        assertTrue(wordDictionary.search("qaqaqa"));
        assertFalse(wordDictionary.search("qaqaq"));
    }
}
