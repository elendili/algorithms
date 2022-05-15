package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

// https://leetcode.com/problems/third-maximum-number/
public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : nums) {
            set.add(i);
            if (set.size() > 3) {
                set.pollFirst();
            }
        }
        if (set.size() < 3) {
            return set.last();
        } else {
            return set.first();
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, thirdMax(new int[]{1, 1, 2}));
        Assertions.assertEquals(1, thirdMax(new int[]{3, 2, 1}));
        Assertions.assertEquals(2, thirdMax(new int[]{1, 2}));
        Assertions.assertEquals(1, thirdMax(new int[]{1}));
        Assertions.assertEquals(1, thirdMax(new int[]{2, 2, 3, 1}));
        Assertions.assertEquals(1, thirdMax(new int[]{2, 2, 2, 3, 1}));
        Assertions.assertEquals(1, thirdMax(new int[]{1, 1, 1, 3, 2}));
        Assertions.assertEquals(1, thirdMax(new int[]{3, 3, 3, 1, 2}));
    }
}
