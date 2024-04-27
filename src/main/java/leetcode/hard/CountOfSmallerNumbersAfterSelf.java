package leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
 */
public class CountOfSmallerNumbersAfterSelf {
    private static boolean onlyPositive = true;
    private static int offset = onlyPositive ? 0 : 10_000;
    private static int possibleValuesCount = onlyPositive ? 10 : 2 * offset + 1;

    // via segment tree
    public List<Integer> countSmaller(int[] nums) {
        int[] tree = new int[2 * possibleValuesCount];
        List<Integer> out = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int numberWithOffset = nums[i] + offset;
            int smaller_count = query(0, numberWithOffset, tree);
            out.add(smaller_count);
            update(numberWithOffset, 1, tree);
        }
        Collections.reverse(out);
        return out;
    }


    // fill segment tree
    // update from leaf to root
    private void update(int index, int value, int[] tree) {
        index += possibleValuesCount; // shift the index to the leafs (right part of tree array contains of leaves)
        tree[index] += value;
        while (index > 1) {
            index /= 2;
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
        }
    }

    // return sum of [left, right]
    private int query(final int inLeft, final int inRight, int[] tree) {
        int out = 0;
        int left = inLeft + possibleValuesCount;
        int right = inRight + possibleValuesCount;
        while (left < right) {
            // if left is a right node
            // bring the value and move to parent's right node
            if (left % 2 == 1) {
                out += tree[left];
                left++;
            }
            // else directly move to parent
            left /= 2;
            // if right is a right node
            // bring the value of the left node and move to parent
            if (right % 2 == 1) {
                right--;
                out += tree[right];
            }
            // else directly move to parent
            right /= 2;
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("[2, 1, 1, 0]", countSmaller(new int[]{5, 2, 6, 1}).toString());
        assertEquals("[0, 0, 0]", countSmaller(new int[]{1, 2, 3}).toString());
        assertEquals("[2, 1, 0]", countSmaller(new int[]{3, 2, 1}).toString());
        assertEquals("[3, 2, 1, 0, 0]", countSmaller(new int[]{3, 2, 1, 0, 3}).toString());
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("[3, 2, 1, 0]", countSmaller(new int[]{3, 2, 1, 0}).toString());
    }
}
