package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/design-search-autocomplete-system/
 */
public class AutocompleteSystemWithTest {
    class AutocompleteSystem {
        final private TrieNode root;
        private TrieNode currNode;
        private final StringBuilder curSentence;

        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            for (int i = 0; i < sentences.length; i++) {
                addToTrie(sentences[i], times[i]);
            }
            curSentence = new StringBuilder();
            currNode = root;
        }

        public List<String> input(char c) {
            if (c == '#') {
                addToTrie(curSentence.toString(), 1);
                curSentence.setLength(0);
                currNode = root;
                return Collections.emptyList();
            }
            curSentence.append(c);

            if (currNode==null || !currNode.children().containsKey(c)) {
                currNode = null;
                return Collections.emptyList();
            }

            currNode = currNode.children().get(c);
            Comparator<String> comparator = (a, b) -> {
                int hotA = currNode.sentences.get(a);
                int hotB = currNode.sentences.get(b);
                if (hotA == hotB) {
                    return b.compareTo(a);
                }

                return hotA - hotB;
            };
            PriorityQueue<String> heap = new PriorityQueue<>(comparator);

            for (String sentence : currNode.sentences.keySet()) {
                heap.add(sentence);
                if (heap.size() > 3) {
                    heap.remove();
                }
            }

            List<String> out = new ArrayList<>();
            while (!heap.isEmpty()) {
                out.add(heap.remove());
            }
            Collections.reverse(out);
            return out;
        }

        record TrieNode(Map<Character, TrieNode> children, Map<String, Integer> sentences) {
            public TrieNode() {
                this(new HashMap<>(), new HashMap<>());
            }
        }

        private void addToTrie(String sentence, int count) {
            TrieNode node = root;
            for (char c : sentence.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }

                node = node.children.get(c);
                int curTimesOfSentence = node.sentences.getOrDefault(sentence, 0);
                node.sentences.put(sentence, curTimesOfSentence + count);
            }
        }
    }

    @Test
    public void test() {
/*
["AutocompleteSystem", "input", "input", "input", "input"]
[[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
Output
[null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]
 */
        AutocompleteSystem ac = new AutocompleteSystem(
                new String[]{"i love you", "island", "iroman", "i love leetcode"},
                new int[]{5, 3, 2, 2}
        );
        assertEquals("[i love you, island, i love leetcode]", ac.input('i').toString());
        assertEquals("[i love you, i love leetcode]", ac.input(' ').toString());
        assertEquals("[]", ac.input('a').toString());
        assertEquals("[]", ac.input('#').toString());
    }
}
