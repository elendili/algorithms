package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;

        // search x in list or element closest to X from bigger side
        int index = Arrays.binarySearch(arr, x);
        // calc indexes
        int startIndex = Math.abs(index) - k - 1;
        int endIndex = Math.abs(index) + k;
        startIndex = Math.max(0, startIndex);
        endIndex = Math.min(n - 1, endIndex);

        while (endIndex - startIndex + 1 > k) {
            int compareResult = compareToX(x, arr[startIndex], arr[endIndex]);
            if (compareResult <= 0) {
                endIndex--;
            } else {
                startIndex++;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = startIndex; i <= endIndex; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    static int compareToX(int x, int a, int b) {
        int diff = Math.abs(a - x) - Math.abs(b - x);
        if (diff != 0) {// a closer
            return diff;
        } else {
            return a - b;
        }
    }

    @Test
    public void test1() {
//        Assertions.assertEquals("[1, 2]",
//                findClosestElements(new int[]{1, 2}, 2, 1).toString());
        Assertions.assertEquals("[1]",
                findClosestElements(new int[]{1, 2}, 1, 1).toString());
        Assertions.assertEquals("[1, 2]",
                findClosestElements(new int[]{1, 2, 3}, 2, 2).toString());
        Assertions.assertEquals("[-100, 0]",
                findClosestElements(new int[]{-100, 0, 100}, 2, 0).toString());
    }

    @Test
    public void test2() {
        Assertions.assertEquals("[1, 2, 3, 4]",
                findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3).toString());
        Assertions.assertEquals("[1, 2, 3, 4]",
                findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1).toString());
        Assertions.assertEquals("[2, 3, 4, 5]",
                findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 100).toString());
    }

    @Test
    public void testX() {
        Assertions.assertEquals("[10]",
                findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 1, 9).toString());
        Assertions.assertEquals("[10, 10, 10]",
                findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 3, 9).toString());
        Assertions.assertEquals("[1, 10, 10, 10]",
                findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 4, 9).toString());
        Assertions.assertEquals("[1, 1, 10, 10, 10]",
                findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 5, 9).toString());
        Assertions.assertEquals("[1, 1, 1, 10, 10, 10]",
                findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 10, 9).toString());
    }

}
