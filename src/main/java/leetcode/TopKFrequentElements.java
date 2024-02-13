package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // to store elements frequencies
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int i : nums) {
            freqs.compute(i, (key, v) -> v == null ? 1 : v + 1);
        }
        // use max heap to sort elements by frequencies
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue =
                new PriorityQueue<>(Map.Entry.<Integer, Integer>
                        comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()));
        priorityQueue.addAll(freqs.entrySet());

        List<Integer> out = new ArrayList<>();
        Map.Entry<Integer,Integer> c;
        int i=0;
        while(i<k && (c=priorityQueue.poll())!=null){
            out.add(c.getKey());
            i++;
        }
        return out;
    }

    @Test
    public void test() {
        // [1,1,1,2,2,3], key = 2
        Assertions.assertEquals(asList(-1, 2), topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2));
        Assertions.assertEquals(asList(), topKFrequent(new int[]{1, 2, 3}, 0));
        Assertions.assertEquals(asList(1), topKFrequent(new int[]{1}, 1));
        Assertions.assertEquals(asList(1, 2), topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        Assertions.assertEquals(asList(1, 2, 3), topKFrequent(new int[]{1, 2, 3, 4, 5}, 3));
        Assertions.assertEquals(asList(1, 2, 3), topKFrequent(new int[]{5, 4, 3, 2, 1}, 3));
        Assertions.assertEquals(asList(0, 1), topKFrequent(new int[]{0, 0, 0, 0, 1}, 3));
    }
}
