package leetcode.top_interview_questions.medium;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/
 */
public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        int out=0;
        AtomicBoolean found = new AtomicBoolean();
        for (int n : nums) {
            map.compute(target - n, (k, oppC) -> {
                if (oppC != null && oppC >= 1) {
                    oppC -= 1;
                    found.set(true);
                }
                return oppC;
            });
            if(found.getAndSet(false)){
                out++;
            } else { // no pair is found
                map.compute(n, (k1, v1) -> v1 == null ? 1 : v1 + 1);
            }
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals(0, maxOperations(new int[]{1, 1, 1}, 1));
        assertEquals(0, maxOperations(new int[]{1, 2}, 5));
        assertEquals(1, maxOperations(new int[]{1, 2}, 3));
        assertEquals(2, maxOperations(new int[]{1, 2, 3, 4}, 5));
        assertEquals(1, maxOperations(new int[]{3, 1, 3, 4, 3}, 6));
        assertEquals(2, maxOperations(new int[]{3, 3, 3, 3, 3}, 6));
        assertEquals(3, maxOperations(new int[]{3, 3, 3, 3, 3,3}, 6));
        assertEquals(2, maxOperations(new int[]{3, -3, 4, -4}, 0));
        assertEquals(2, maxOperations(new int[]{1, -3, 2, -4}, -2));
        assertEquals(1, maxOperations(new int[]{2, 2, 0}, 2));
        assertEquals(0, maxOperations(new int[]{2, 2, 0}, 0));
        assertEquals(1, maxOperations(new int[]{0, 0, 0}, 0));
        assertEquals(2, maxOperations(new int[]{0, 0, 1, -1}, 0));
    }
}
