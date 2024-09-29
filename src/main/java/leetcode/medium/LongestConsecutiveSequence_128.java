package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-interview-150
 */
public class LongestConsecutiveSequence_128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i : nums) {
            set.add(i);
        }

        int out = 0;
        int curSeq;
        for (int e:set) {
            if(!set.contains(e-1)){
                curSeq = 1;
                int next = e + 1;
                while (set.contains(next)) {
                    next += 1;
                    curSeq++;
                }
                out = Math.max(out, curSeq);
            }
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(4, longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        assertEquals(9, longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }
}
