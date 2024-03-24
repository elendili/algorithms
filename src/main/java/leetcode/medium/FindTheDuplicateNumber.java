package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/find-the-duplicate-number
 */
public class FindTheDuplicateNumber {
    // use Floyd's algorithm
    // find cycle, then start of the cycle
    public int findDuplicate(int[] nums) {
        // search cycle
        int hare = nums[0];
        int tortoise = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[hare];
            hare = nums[hare];
        } while (hare != tortoise);

        // move slow pointer at start, slow down fast pointer
        tortoise = nums[0];
        while (hare != tortoise) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, findDuplicate(new int[]{1, 3, 4, 2, 2}));
        Assertions.assertEquals(3, findDuplicate(new int[]{3, 1, 3, 4, 2}));
        Assertions.assertEquals(3, findDuplicate(new int[]{3, 3, 3, 3}));
        Assertions.assertEquals(1, findDuplicate(new int[]{1,1}));
    }
}
