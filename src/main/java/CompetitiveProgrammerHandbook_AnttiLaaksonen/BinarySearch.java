package CompetitiveProgrammerHandbook_AnttiLaaksonen;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearch {
    int binarySearchViaJumps(int[] array, int x) {
        int n = array.length;
        int k = 0;
        for (int b = n / 2; b >= 1; b /= 2) {
            while (k + b < n && array[k + b] <= x) {
                k = k + b;
            }
        }
        if (array[k] == x) {
            return k;
        }
        return -1;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        int[] a = new int[]{1, 2, 3, 4};
        assertEquals(0, binarySearchViaJumps(a, 1));
        assertEquals(1, binarySearchViaJumps(a, 2));
        assertEquals(2, binarySearchViaJumps(a, 3));
        assertEquals(3, binarySearchViaJumps(a, 4));
    }
}
