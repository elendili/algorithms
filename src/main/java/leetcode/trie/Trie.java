package leetcode.trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
// https://leetcode.com/explore/learn/card/trie/147/basic-operations/1047/
public class Trie {
    public static final int alphabetSize = 26;

    class TrieNode {
        TrieNode[] children = new TrieNode[alphabetSize];
        boolean leaf = false;
    }

    TrieNode root = new TrieNode();

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        char[] ca = word.toCharArray();
        TrieNode current = root;
        for (int i=0;i<ca.length;i++) {
            int childIndex = ca[i] - 97;
            TrieNode curChild = current.children[childIndex];
            TrieNode next;
            if(curChild!=null){
                next=curChild;
            } else {
                next = new TrieNode();
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
        TrieNode tn =  getLastNode(word);
        return tn!=null && tn.leaf;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return getLastNode(prefix)!=null;
    }

    private TrieNode getLastNode(String prefix){
        char[] ca = prefix.toCharArray();
        TrieNode current = root;
        for (int i=0;i<ca.length;i++) {
            TrieNode next = current.children[ca[i] - 97];
            if (next != null) {
                current = next;
            } else {
                return null;
            }
        }
        return current;
    }
    @Test
    public void test(){
        Trie trie = new Trie();
        trie.insert("app");
        Assertions.assertTrue(trie.search("app"));
        Assertions.assertFalse(trie.search("appp"));
        Assertions.assertFalse(trie.search("ap"));
        trie.insert("apple");
        trie.insert("add");
        Assertions.assertFalse(trie.search("apps"));
        Assertions.assertTrue(trie.search("app"));
        Assertions.assertTrue(trie.search("add"));
        Assertions.assertFalse(trie.search("ad"));

        Assertions.assertTrue(trie.startsWith("ad"));
        Assertions.assertTrue(trie.startsWith("add"));
        Assertions.assertTrue(trie.startsWith("a"));
    }
}
