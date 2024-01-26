package leetcode.easy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/problems/remove-element/

 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int lastIndex = n - 1;
        for (int i = lastIndex; i >= 0; i--) {
            if (nums[i] == val) {
                // shift
                lastIndex--;
                for (int j = i; j <= lastIndex; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[lastIndex + 1] = -1;
            }
        }
        return lastIndex+1;
    }

    @Test
    public void test() {
        int[] a;

        a = new int[]{};
        assertEquals(0, removeElement(a, 3));

        a = new int[]{3};
        assertEquals(0, removeElement(a, 3));
        assertEquals("[-1]", Arrays.toString(a));

        a = new int[]{2};
        assertEquals(1, removeElement(a, 3));
        assertEquals("[2]", Arrays.toString(a));

        a = new int[]{3, 2};
        assertEquals(1, removeElement(a, 3));
        assertEquals("[2, -1]", Arrays.toString(a));

        a = new int[]{3, 2, 3};
        assertEquals(1, removeElement(a, 3));
        assertEquals("[2, -1, -1]", Arrays.toString(a));

        a = new int[]{3, 2};
        assertEquals(1, removeElement(a, 2));
        assertEquals("[3, -1]", Arrays.toString(a));

        a = new int[]{3, 2, 2, 3};
        assertEquals(2, removeElement(a, 3));
        assertEquals("[2, 2, -1, -1]", Arrays.toString(a));

        a = new int[]{3, 0, 4, 2};
        assertEquals(3, removeElement(a, 2));
        assertEquals("[3, 0, 4, -1]", Arrays.toString(a));

        a = new int[]{2, 3, 0, 4, 2};
        assertEquals(3, removeElement(a, 2));
        assertEquals("[3, 0, 4, -1, -1]", Arrays.toString(a));

        a = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        assertEquals(5, removeElement(a, 2));
        assertEquals("[0, 1, 3, 0, 4, -1, -1, -1]", Arrays.toString(a));
    }
}
