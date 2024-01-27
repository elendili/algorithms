package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/problems/h-index
Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.
 */
public class HIndex {

    public int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);
        int maxH = 0;
        for (int i = 0; i < citations.length; i++) {
            int v = citations[i];
            int uncheckedCount = n - i;
            if (v <= uncheckedCount) {
                maxH = Math.max(maxH, v);
            } else {
                maxH = Math.max(maxH, uncheckedCount);
            }
        }
        return maxH;
    }

    @Test
    public void test() {
        assertEquals(0, hIndex(new int[]{0}));
        assertEquals(1, hIndex(new int[]{100}));
        assertEquals(3, hIndex(new int[]{3, 0, 6, 1, 5}));
        assertEquals(1, hIndex(new int[]{1, 3, 1}));
        assertEquals(3, hIndex(new int[]{1, 2, 3, 4, 5}));
        assertEquals(2, hIndex(new int[]{1, 2, 2, 4, 5}));
    }

}
