package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static java.util.Arrays.asList;

/**
 * https://leetcode.com/problems/minimum-array-length-after-pair-removals/description/
 */
public class MinimumArrayLengthAfterPairRemovals {

    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int currentCount = 1;
        for (int i = 1; i < n; i++) {
            if (!nums.get(i).equals(nums.get(i - 1))) {
                pq.add(currentCount);
                currentCount = 1;
            } else {
                currentCount += 1;
            }
        }
        pq.add(currentCount);

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a-1 > 0) {
                pq.add(a-1);
            }
            if (b-1 > 0) {
                pq.add(b-1);
            }
        }
        int out = pq.isEmpty() ? 0 : pq.poll();
        return out;
    }

    @Test
    public void test1349() {
        Assertions.assertEquals(0, minLengthAfterRemovals(asList(1, 3, 4, 9)));
    }

    @Test
    public void test2369() {
        Assertions.assertEquals(0, minLengthAfterRemovals(asList(2, 3, 6, 9)));
    }

    @Test
    public void test112() {
        Assertions.assertEquals(1, minLengthAfterRemovals(asList(1, 1, 2)));
    }

    @Test
    public void test11() {
        Assertions.assertEquals(2, minLengthAfterRemovals(asList(1, 1)));
    }

    @Test
    public void test13334() {
        Assertions.assertEquals(1, minLengthAfterRemovals(asList(1, 3, 3, 3, 4)));
    }

    @Test
    public void test1333444() {
        Assertions.assertEquals(1, minLengthAfterRemovals(asList(1, 3, 3, 3, 4, 4, 4)));
    }

    @Test
    public void test_111111122() {
        Assertions.assertEquals(5, minLengthAfterRemovals(asList(1, 1, 1, 1, 1, 1, 1, 2, 2)));
    }
    @Test
    public void test_114455() {
        Assertions.assertEquals(0, minLengthAfterRemovals(asList(1,1,4,4,5,5)));
    }
}
