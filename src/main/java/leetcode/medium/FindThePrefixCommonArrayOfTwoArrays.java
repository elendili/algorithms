package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FindThePrefixCommonArrayOfTwoArrays {

    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        boolean[] foundInA = new boolean[n + 1];
        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            int a = A[i];
            foundInA[a] = true;
            for (int j = 0; j <= i; j++) {
                int b = B[j];
                if (foundInA[b]) {
                    out[i] += 1;
                }
            }
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(Arrays.toString(new int[]{1}),
                Arrays.toString(findThePrefixCommonArray(new int[]{1}, new int[]{1})));

        Assertions.assertEquals(Arrays.toString(new int[]{1, 2}),
                Arrays.toString(findThePrefixCommonArray(new int[]{1,2}, new int[]{1,2})));

        Assertions.assertEquals(Arrays.toString(new int[]{0, 1, 3}),
                Arrays.toString(findThePrefixCommonArray(new int[]{2, 3, 1}, new int[]{3, 1, 2})));

        Assertions.assertEquals(Arrays.toString(new int[]{0, 2, 3, 4}),
                Arrays.toString(findThePrefixCommonArray(new int[]{1, 3, 2, 4}, new int[]{3, 1, 2, 4})));

    }
}
