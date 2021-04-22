package leetcode.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// https://leetcode.com/problems/can-place-flowers/
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] a, int n) {
        return spacesCount(a) >= n;
    }

    public int spacesCount(int[] a) {
        final int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (a[i] == 0
                    && (i == 0 || a[i - 1] == 0)
                    && (i == N - 1 || a[i + 1] == 0)
            ) {
                a[i] = 1;
                count += 1;
            }
        }
        return count;
    }

    @Test
    public void testGapsCount() {
        assertEquals(3, spacesCount(new int[]{0, 0, 0, 0, 0}));
        assertEquals(1, spacesCount(new int[]{0}));
        assertEquals(0, spacesCount(new int[]{1}));
        assertEquals(1, spacesCount(new int[]{0, 0}));
        assertEquals(0, spacesCount(new int[]{1, 0}));
        assertEquals(0, spacesCount(new int[]{1, 1}));
        assertEquals(2, spacesCount(new int[]{0, 0, 0}));
        assertEquals(1, spacesCount(new int[]{1, 0, 0}));
        assertEquals(1, spacesCount(new int[]{0, 0, 1}));
        assertEquals(1, spacesCount(new int[]{1, 0, 0, 0, 1}));
        assertEquals(2, spacesCount(new int[]{1, 0, 0, 0, 0}));
        assertEquals(1, spacesCount(new int[]{1, 0, 0, 0, 1}));
    }

    @Test
    public void testGapsCount2() {
        assertEquals(3, spacesCount(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 1}));
    }

    @Test
    public void test() {
        assertTrue(canPlaceFlowers(new int[]{0}, 1));
        assertTrue(canPlaceFlowers(new int[]{0, 0}, 1));
        assertTrue(canPlaceFlowers(new int[]{0, 0, 0}, 1));
        assertTrue(canPlaceFlowers(new int[]{0, 0, 0}, 2));
        assertTrue(canPlaceFlowers(new int[]{0, 0, 0, 0, 0}, 3));
        assertTrue(canPlaceFlowers(new int[]{1, 0, 0}, 1));
        assertTrue(canPlaceFlowers(new int[]{0, 0, 1}, 1));
        assertTrue(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        assertTrue(canPlaceFlowers(new int[]{1, 0, 0, 0, 0}, 2));
        assertTrue(canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 1}, 3));

    }

    @Test
    public void testFalse() {
        assertFalse(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
        assertFalse(canPlaceFlowers(new int[]{1, 0}, 1));
        assertFalse(canPlaceFlowers(new int[]{1, 0}, 1));

        assertFalse(canPlaceFlowers(new int[]{0}, 2));
        assertFalse(canPlaceFlowers(new int[]{0, 0}, 2));
        assertFalse(canPlaceFlowers(new int[]{0, 0, 0}, 3));
    }
}
