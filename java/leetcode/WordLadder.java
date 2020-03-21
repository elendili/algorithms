package leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
// https://leetcode.com/problems/word-ladder
public class WordLadder {
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            // prepare distances
            wordList = Stream.concat(wordList.stream(), Stream.of(beginWord)).collect(Collectors.toList());
            Map<String, Set<String>> dists = makeDistancesMap(wordList);
            if (!dists.containsKey(endWord)) {
                return 0;
            }
            LinkedList<Set<String>> q = new LinkedList<>();
            q.add(dists.get(beginWord));
            int d = 1;
            while (!q.isEmpty()) {
                Set<String> set = q.poll();
                d += 1;
                if (set.contains(endWord)) {
                    return d;
                }
                Set<String> set2 = set.stream()
                        .flatMap(s -> dists.containsKey(s) ? dists.remove(s).stream() : Stream.empty())
                        .collect(Collectors.toSet());
                if (set2.isEmpty()) {
                    return 0;
                }
                q.add(set2);
            }
            return 0;
        }

        Map<String, Set<String>> makeDistancesMap(List<String> wordList) {
            Map<String, Set<String>> d = new HashMap<>();
            for (int i = 0; i < wordList.size() - 1; i++) {
                String w1 = wordList.get(i);
                for (int j = i + 1; j < wordList.size(); j++) {
                    String w2 = wordList.get(j);
                    int di = distance(w1, w2);
                    if (di == 1) {
                        d.compute(w1, (k, v) -> {
                            v = (v == null) ? new HashSet<>() : v;
                            v.add(w2);
                            return v;
                        });
                        d.compute(w2, (k, v) -> {
                            v = (v == null) ? new HashSet<>() : v;
                            v.add(w1);
                            return v;
                        });
                    }
                }
            }
            return d;
        }

        int distance(String w1, String w2) {
            if (w1.length() != w2.length()) {
                return Integer.MAX_VALUE;
            }
            int out = 0;
            for (int i = 0; i < w1.length(); i++) {
                if (w1.charAt(i) != w2.charAt(i)) {
                    out += 1;
                }
            }
            return out;
        }
    }

    @Test
    public void test() {
        assertEquals(0, new Solution().ladderLength("hit", "hot", asList()));
        // hit->hot
        assertEquals(2, new Solution().ladderLength("hit", "hot", asList("hot", "hat", "hut", "het")));
        // git->hit->hot
        assertEquals(3, new Solution().ladderLength("git", "hot", asList("gat", "hot", "het", "hit")));
        // "hit" -> "hot" -> "dot" -> "dog" -> "cog"
        assertEquals(5, new Solution().ladderLength("hit", "cog", asList("hot", "dot", "dog", "lot", "log", "cog")));

        assertEquals(0, new Solution().ladderLength("hit", "cog", asList("hot", "dog", "log", "cog")));
        assertEquals(0, new Solution().ladderLength("hit", "cog", asList("hot", "dot", "dog", "lot", "log")));
    }
}
