package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums
 */
public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (l1 == 0 || l2 == 0) {
            return Collections.emptyList();
        }

        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(4,
                Comparator.comparingInt(l -> l.get(2)));
        Set<List<Integer>> visited = new HashSet<>();

        List<List<Integer>> out = new ArrayList<>();
        int i = 0, j = 0;
        out.add(Arrays.asList(nums1[0], nums2[0]));
        while (true) {
            if (j < l2 - 1) {
                List<Integer> insert = Arrays.asList(i, j + 1, nums1[i] + nums2[j + 1]);
                if (visited.add(insert)) {
                    heap.add(insert);
                }
            }
            if (i < l1 - 1) {
                List<Integer> insert = Arrays.asList(i + 1, j, nums1[i + 1] + nums2[j]);
                if (visited.add(insert)) {
                    heap.add(insert);
                }
            }

            List<Integer> minSumIndexes = heap.poll();
            if (minSumIndexes == null) {
                break;
            }
            i = minSumIndexes.get(0);
            j = minSumIndexes.get(1);
            out.add(Arrays.asList(nums1[i], nums2[j]));
            if (out.size() == k) {
                break;
            }
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals("[[1, 2]]",
                kSmallestPairs(new int[]{1}, new int[]{2}, 10).toString());
        assertEquals("[[1, 2], [1, 4], [1, 6]]",
                kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3).toString());
        assertEquals("[[1, 1], [1, 1]]",
                kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2).toString());
        assertEquals("[[1, 3], [2, 3]]",
                kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3).toString());
        assertEquals("[[1, 3], [1, 4], [2, 3], [2, 4]]",
                kSmallestPairs(new int[]{1, 2}, new int[]{3, 4}, 10).toString());
        assertEquals("[]",
                kSmallestPairs(new int[]{1, 2}, new int[]{}, 3).toString());
        assertEquals("[[-2, -1], [-1, -1], [-2, 1], [1, -1], [-1, 1], [1, 1]]",
                kSmallestPairs(new int[]{-2, -1, 1}, new int[]{-1, 1}, 6).toString());
    }
}
