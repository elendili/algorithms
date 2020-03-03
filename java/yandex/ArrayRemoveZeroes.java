package yandex;

import org.junit.Assert;
import org.junit.Test;

public class ArrayRemoveZeroes {
    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public int[] removeInPlace(int[] a, int toRemove) {
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != toRemove) {
                if (i > j) {
                    swap(a, i, j);
                }
                j++;
            }
        }
        int[] b = new int[j];
        System.arraycopy(a, 0, b, 0, j);
        return b;
    }

    public int[] removeInNewOne(int[] a, int toRemove) {
        int[] b = new int[a.length];
        for (int i = 0,j=0; i < a.length; i++) {
            if (a[i] != toRemove) {
                b[j++]=a[i];
            }
        }
        return b;
    }

    @Test
    public void inPlaceTest() {
        Assert.assertArrayEquals(new int[]{1, 2}, removeInPlace(new int[]{1, 0, 0, 2},0));
        Assert.assertArrayEquals(new int[]{1}, removeInPlace(new int[]{0, 0, 0, 1},0));
        Assert.assertArrayEquals(new int[]{1, 2, 3}, removeInPlace(new int[]{1, 2, 3, 0},0));
    }

    @Test
    public void removeInNewOneTest() {
        Assert.assertArrayEquals(new int[]{1, 2,0,0}, removeInNewOne(new int[]{1, 0, 0, 2},0));
        Assert.assertArrayEquals(new int[]{1,0,0,0}, removeInNewOne(new int[]{0, 0, 0, 1},0));
        Assert.assertArrayEquals(new int[]{1, 2, 3,0}, removeInNewOne(new int[]{1, 2, 3, 0},0));
    }
}
