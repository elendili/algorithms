package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

// https://leetcode.com/problems/smallest-string-with-swaps/
public class SmallestStringWithSwaps {
    static class Solution {

        void union(int[] graph, int i, int j) {
            assert i < graph.length && j < graph.length : "index is bigger than array size";
            graph[find(graph, i)] = find(graph, j);
        }

        int find(int[] graph, int x) {
            if (x != graph[x]) {
                graph[x] = find(graph, graph[x]);
            }
            return graph[x];
        }

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            if (s.length() == 0) {
                return "";
            }
            if (pairs.isEmpty()) {
                return s;
            }
            int n = s.length();
            int[] graph = new int[n];
            for (int i = 0; i < n; i++) {
                graph[i] = i;
            }
            for (List<Integer> pair : pairs) {
                union(graph, pair.get(0), pair.get(1));
            }
            for (List<Integer> pair : pairs) {
                union(graph, pair.get(0), pair.get(1));
            }
//            union(graph, pairs.get(0).get(0), pairs.get(0).get(1)); // because first element calculated wrongly
            Map<Integer, Integer> freqs = new HashMap<>();
            for (int i = 0; i < n; i++) {
                freqs.compute(graph[i], (k, v) -> v == null ? 1 : v + 1);
            }
            Map<Integer, char[]> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                final int _i = i;
                int k = graph[i];
                map.compute(k, (_k, v) -> {
                    int nn = freqs.get(_k);
                    freqs.put(_k, nn - 1);
                    v = v == null ? new char[nn] : v;
                    int j = v.length - nn;
                    v[j] = s.charAt(_i);
                    if (nn == 1) {
                        Arrays.sort(v);
                    }
                    return v;
                });
            }
            StringBuilder out2 = new StringBuilder();
            for (int i = 0; i < n; i++) {
                Integer j = graph[i];
                char[] ca = map.get(j);
                int index = freqs.compute(j, (k, v) -> v == null ? 0 : v + 1) - 1;
                out2.append(ca[index]);
            }
            return out2.toString();
        }

        @Test
        public void test() {
            Assertions.assertEquals("bacd",
                    smallestStringWithSwaps("dcab", asList(asList(0, 3), asList(1, 2))));

            Assertions.assertEquals("abcd",
                    smallestStringWithSwaps("dcab", asList(asList(0, 3), asList(1, 2), asList(0, 2))));

            Assertions.assertEquals("abc",
                    smallestStringWithSwaps("cba", asList(asList(0, 1), asList(1, 2))));

            Assertions.assertEquals("xabca",
                    smallestStringWithSwaps("xcbaa", asList(asList(1, 2), asList(2, 3))));

            Assertions.assertEquals("",
                    smallestStringWithSwaps("", asList(asList(0, 1), asList(1, 2))));

            Assertions.assertEquals("deykuy",
                    smallestStringWithSwaps("udyyek", asList(
                            asList(3, 3),
                            asList(3, 0),
                            asList(3, 1),
                            asList(3, 4),
                            asList(3, 5),
                            asList(5, 1)
                    )));

        }
    }
}
