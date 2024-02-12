package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {

    interface TopKFrequent {
        List<String> topKFrequent(String[] words, int k);
    }

    static class PqSolution implements TopKFrequent {
        static final Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey());

        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> fqs = new HashMap<>();
            for (String w : words) {
                fqs.compute(w, (key, v) -> {
                    v = v == null ? 0 : v;
                    return v + 1;
                });
            }
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(comparator);
            fqs.entrySet().forEach(pq::add);

            List<String> out = new ArrayList<>();
            for (int i = 0; i < k && !pq.isEmpty(); i++) {
                out.add(pq.poll().getKey());
            }
            return out;
        }
    }

    static Stream<TopKFrequent> solvers() {
        return Stream.of(new PqSolution());
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("solvers")
    public void test(TopKFrequent solver) {
        assertEquals("[i]", solver.topKFrequent(new String[]{"i"}, 100).toString());

        assertEquals("[i, love]", solver.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2).toString());

        assertEquals("[the, is, sunny, day]", solver.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4).toString());
    }
}
