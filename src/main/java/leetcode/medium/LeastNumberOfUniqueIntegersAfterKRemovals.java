package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/description/
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        // convert to frequencies, then put counts to heap, and erase till k happen
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int i : arr) {
            freqs.compute(i, (_k, v) -> v == null ? 1 : v + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(freqs.values());

        int out = pq.size();
        while (k > 0 && !pq.isEmpty()) {
            int count = pq.poll();
            if (count <= k) {
                out--;
                k -= count;
            } else {
                break;
            }
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, findLeastNumOfUniqueInts(new int[]{5, 5, 4}, 1));
        Assertions.assertEquals(2, findLeastNumOfUniqueInts(new int[]{5, 5, 4, 4}, 1));
        Assertions.assertEquals(1, findLeastNumOfUniqueInts(new int[]{5, 5, 4, 4}, 2));
        Assertions.assertEquals(1, findLeastNumOfUniqueInts(new int[]{5, 5, 4, 4}, 3));
        Assertions.assertEquals(0, findLeastNumOfUniqueInts(new int[]{5, 5, 4, 4}, 4));
        Assertions.assertEquals(2, findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 3));
        Assertions.assertEquals(0, findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 100));
    }
}
