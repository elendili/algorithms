package collections.sorting;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class CountingSort {
    public static int[] countingSort(int[] array, int numberCeiling) {
        int[] c = new int[numberCeiling + 1];
        int[] out = new int[array.length];
        Arrays.stream(array).forEach(i -> {
            assert i<=numberCeiling:"Number ceiling "+numberCeiling+" exceeded by: "+i;
            c[i] += 1;
        });
        int outIndex = 0;
        for (int v = 0; v < numberCeiling + 1; v++) {
            int count = c[v];
            for (int i = 0; i < count; i++) {
                out[outIndex + i] = v;
            }
            outIndex += count;
        }
        return out;
    }

    @Test
    public void t() {
        assertArrayEquals(new int[]{1, 2, 3}, countingSort(new int[]{2, 3, 1}, 3));
        assertArrayEquals(new int[]{}, countingSort(new int[]{}, 3));
        assertArrayEquals(new int[]{1,2,3,3,3}, countingSort(new int[]{3,1,3,2,3}, 3));
    }
}
