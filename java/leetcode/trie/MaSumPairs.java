package leetcode.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/explore/learn/card/trie/148/practical-application-i/1058/
 * <p>
 * Design a map that allows you to do the following:
 * <p>
 * Maps a string key to a given value.
 * Returns the sum of the values that have a key with a prefix equal to a given string.
 * Implement the MapSum class:
 * <p>
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 */
public class MaSumPairs {
    class MapSum {
        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean hasChild = false;
            int val = 0;

            @Override
            public String toString() {
                return val + ","+hasChild;
            }
        }

        private final TrieNode head = new TrieNode();

        public MapSum() {

        }

        public void insert(String key, int val) {
            char[] ca = key.toCharArray();
            TrieNode cur = head;
            for (char c : ca) {
                TrieNode next = cur.children[c - 'a'];
                if (next == null) {
                    cur.hasChild = true;
                    cur = cur.children[c - 'a'] = new TrieNode();
                } else {
                    cur = next;
                }
            }
            cur.val = val;
        }

        public int sum(String prefix) {
            char[] ca = prefix.toCharArray();
            TrieNode cur = head;
            for (char c : ca) {
                cur = cur.children[c - 'a'];
                if (cur == null) {
                    break;
                }
            }
            int out = traverse(cur);
            return out;
        }

        private int traverse(TrieNode cur) {
            if (cur == null) {
                return 0;
            }
            int out = cur.val;
            for (TrieNode child : cur.children) {
                out += traverse(child);
            }
            return out;
        }
    }

    @Test
    public void test1() {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        assertEquals(3, mapSum.sum("ap"));           // return 3 (apple = 3)
        mapSum.insert("app", 2);
        assertEquals(3+2, mapSum.sum("ap"));           // return 5 (apple + app = 3 + 2 = 5)
        mapSum.insert("", 7);
        assertEquals(3+2, mapSum.sum("ap"));
        assertEquals(3+2+7, mapSum.sum(""));
    }

    @Test
    public void test2() {
        MapSum mapSum = new MapSum();
        assertEquals(0, mapSum.sum(""));
        mapSum.insert("", 1);
        assertEquals(1, mapSum.sum(""));
        mapSum.insert("z", 2);
        assertEquals(1+2, mapSum.sum(""));
        assertEquals(2, mapSum.sum("z"));
        assertEquals(0, mapSum.sum("za"));
    }

    @Test
    public void test3() {
        MapSum mapSum = new MapSum();
        mapSum.insert("z", 2);
        assertEquals(2, mapSum.sum("z"));
        assertEquals(0, mapSum.sum("a"));
        assertEquals(0, mapSum.sum("za"));
        assertEquals(0, mapSum.sum("zaa"));
    }

}
