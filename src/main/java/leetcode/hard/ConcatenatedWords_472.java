package leetcode.hard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/concatenated-words/description/
 */
public class ConcatenatedWords_472 {
    interface ConcatenatedWords {
        List<String> findAllConcatenatedWordsInADict(String[] words);
    }

    static class ConcatenatedWordsImpl implements ConcatenatedWords {

        class Trie {
            final Trie[] children = new Trie[26];
            boolean leaf;

            void add(String word) {
                Trie cur = this;
                for (int i = 0; i < word.length(); i++) {
                    int index = word.charAt(i) - 'a';
                    if (cur.children[index] == null) {
                        cur.children[index] = new Trie();
                    }
                    cur = cur.children[index];
                }
                cur.leaf = true;
            }

        }

        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            Trie rootTrie = new Trie();
            int minWordLength = Integer.MAX_VALUE;
            int maxWordLength = Integer.MIN_VALUE;
            for (String w : words) {
                rootTrie.add(w);
                minWordLength = Math.min(minWordLength, w.length());
                maxWordLength = Math.max(maxWordLength, w.length());
            }
            List<String> out = new ArrayList<>();
            // cases: first word can be part of another word
            // cases: there should be at least 2 inclusion of word
            Deque<Integer> stack = new ArrayDeque<>();
            boolean[] dp = new boolean[maxWordLength];
            for (String w : words) {
                int l = w.length();
                if (l >= minWordLength * 2) {
                    // search possible substrings which included in Trie
                    stack.clear();
                    stack.addFirst(0);
                    Arrays.fill(dp, true);
                    stopWord:
                    while (!stack.isEmpty()) {
                        int start = stack.pollLast();
                        Trie cur = rootTrie;
                        for (int i = start; i < w.length() + 1; i++) {
                            if (i < w.length()) {
                                if (cur.leaf && dp[start]) {
                                    stack.add(i); // not the end of the word, but sub-word found, need to proceed search
                                }
                                char c = w.charAt(i);
                                int trieChildrenIndex = c - 'a';
                                if (cur.children[trieChildrenIndex] == null) {
                                    dp[start] = false;
                                    break;
                                }
                                cur = cur.children[trieChildrenIndex];
                            } else {
                                // word is ended
                                if (cur.leaf) {
                                    if (start != 0) { // the sub-word found is not the entire word
                                        out.add(w);
                                        break stopWord;
                                    } else {
                                        dp[start] = false;
                                    }
                                }
                            }
                        }
                    }

                }
            }
            return out;
        }

    }

    public static Stream<ConcatenatedWords> implementationsSource() {
        return Stream.of(new ConcatenatedWordsImpl());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void testSimple(ConcatenatedWords impl) {
        assertEquals("[]", impl.findAllConcatenatedWordsInADict(
                new String[]{"a"}
        ).toString());
        assertEquals("[]", impl.findAllConcatenatedWordsInADict(
                new String[]{"ab", "a"}
        ).toString());
        assertEquals("[]", impl.findAllConcatenatedWordsInADict(
                new String[]{"ab", "a", "ba"}
        ).toString());
        assertEquals("[ab, ba]", impl.findAllConcatenatedWordsInADict(
                new String[]{"ab", "a", "ba", "b"}
        ).toString());
        assertEquals("[ab]", impl.findAllConcatenatedWordsInADict(
                new String[]{"ab", "b", "a"}
        ).toString());
        assertEquals("[aa]", impl.findAllConcatenatedWordsInADict(
                new String[]{"a", "aa"}
        ).toString());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void testSimpleRepeat(ConcatenatedWords impl) {
        assertEquals("[]", impl.findAllConcatenatedWordsInADict(
                new String[]{"ccc", "ccccc", "ccccccc"}
        ).toString());
        assertEquals("[cccccc]", impl.findAllConcatenatedWordsInADict(
                new String[]{"cc", "ccccc", "cccccc"}
        ).toString());

        assertEquals("[ab, ababba]", impl.findAllConcatenatedWordsInADict(
                new String[]{"ab", "a", "b", "ababba"}
        ).toString());
        assertEquals("[ab, ababba, bab, ba]", impl.findAllConcatenatedWordsInADict(
                new String[]{"ab", "a", "b", "ababba", "bab", "ba"}
        ).toString());
        assertEquals("[abbaba]", impl.findAllConcatenatedWordsInADict(
                new String[]{"ab", "ababa", "abbaba", "ba"}
        ).toString());

    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(ConcatenatedWords impl) {
        assertEquals("[catsdogcats, dogcatsdog, ratcatdogcat]", impl.findAllConcatenatedWordsInADict(
                new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}
        ).toString());
        assertEquals("[catdog]", impl.findAllConcatenatedWordsInADict(
                new String[]{"cat", "dog", "catdog"}
        ).toString());
        assertEquals("[catdog, caticdog, catdogdogcat]", impl.findAllConcatenatedWordsInADict(
                new String[]{"cat", "dog", "catic", "catdog", "caticdog", "catdogdogcat"}
        ).toString());

    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void testOverload(ConcatenatedWords impl) {
        assertEquals("[aa, aaa, aaaa, aaaaa, aaaaaa, aaaaaaa, aaaaaaaa, aaaaaaaaa, aaaaaaaaaa, aaaaaaaaaaa, aaaaaaaaaaaa, aaaaaaaaaaaaa, aaaaaaaaaaaaaa, aaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa]", impl.findAllConcatenatedWordsInADict(
                new String[]{"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaaa", "aaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaz"}
        ).toString());

    }
}
