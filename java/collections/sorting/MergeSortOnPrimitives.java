package collections.sorting;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortOnPrimitives {

    public int[] mergeSortedArrays(int[] a, int[] b) {
        final int totalLength = a.length + b.length;
        int[] out = new int[totalLength];
        if (a.length == 0) {
            System.arraycopy(b, 0, out, 0, b.length);
            return out;
        }
        if (b.length == 0) {
            System.arraycopy(a, 0, out, 0, a.length);
            return out;
        }
        if (a[0] >= b[b.length - 1]) {
            System.arraycopy(b, 0, out, 0, b.length);
            System.arraycopy(a, 0, out, b.length, a.length);
            return out;
        }
        if (b[0] >= a[a.length - 1]) {
            System.arraycopy(a, 0, out, 0, a.length);
            System.arraycopy(b, 0, out, a.length, b.length);
            return out;
        }

        int ia = 0, ib = 0, io = 0;
        while (ia < a.length && ib < b.length) {
            if (a[ia] <= b[ib]) {
                out[io] = a[ia];
                ia++;
            } else {
                out[io] = b[ib];
                ib++;
            }
            io++;
        }

        for (; ia < a.length; ia++, io++) {
            out[io] = a[ia];
        }
        for (; ib < b.length; ib++, io++) {
            out[io] = b[ib];
        }

        return out;
    }

    public int[] mergeSortedArraysSort(int[] a) {
        if (a.length<2){
            return a;
        }
        int mid = a.length / 2;
        int[] a1 = new int[mid];
        int[] a2 = new int[a.length - mid];
        System.arraycopy(a,0,a1,0,a1.length);
        System.arraycopy(a,mid,a2,0,a2.length);
        int[] a1s = mergeSortedArraysSort(a1);
        int[] a2s = mergeSortedArraysSort(a2);
        int[] out = mergeSortedArrays(a1s, a2s);
        return out;
    }

    public int[] mergeSortedPartsInSameArray(final int[] a, int start,
                                             final int startOfSecondPart,
                                             final int end // not inclusive
    ) {
        if ((end - start) < 2) {
            return a;
        }

        int i1 = 0, i2 = 0, ia = start;
        int[] a1 = new int[startOfSecondPart - start];
        int[] a2 = new int[end - startOfSecondPart];
        System.arraycopy(a, start, a1, 0, a1.length);
        System.arraycopy(a, startOfSecondPart, a2, 0, a2.length);
        while (i1 < a1.length && i2 < a2.length) {
            if (a1[i1] <= a2[i2]) {
                a[ia] = a1[i1];
                i1++;
            } else {
                a[ia] = a2[i2];
                i2++;
            }
            ia++;
        }

        for (; i1 < a1.length; i1++, ia++) {
            a[ia] = a1[i1];
        }
        for (; i2 < a2.length; i2++, ia++) {
            a[ia] = a2[i2];
        }
        return a;

    }

    // change in place: returns same array
    public int[] mergeSortedPartsInSameArraySort(int[] a) {
        return mergeSortedPartsInSameArraySort(a, 0, a.length);
    }

    public int[] mergeSortedPartsInSameArraySort(
            final int[] a,
            final int start,
            final int end) {
        if ((end - start) < 2) {
            return a;
        }
        int mid = start + ((end - start) / 2);
        mergeSortedPartsInSameArraySort(a, start, mid);
        mergeSortedPartsInSameArraySort(a, mid, end);
        mergeSortedPartsInSameArray(a, start, mid, end);
        return a;
    }

    @Test
    public void merge2SortedArraysTest() {
        assertArrayEquals(new int[]{2, 4}, mergeSortedArrays(new int[]{2, 4}, new int[]{}));
        assertArrayEquals(new int[]{}, mergeSortedArrays(new int[]{}, new int[]{}));
        assertArrayEquals(new int[]{1, 3}, mergeSortedArrays(new int[]{}, new int[]{1, 3}));
        assertArrayEquals(new int[]{1, 2, 3}, mergeSortedArrays(new int[]{2, 3}, new int[]{1}));
        assertArrayEquals(new int[]{1, 2, 3}, mergeSortedArrays(new int[]{2}, new int[]{1, 3}));
        assertArrayEquals(new int[]{1, 2, 3}, mergeSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    @Test
    public void mergeSortedPartsInSameArrayTest() {
        assertArrayEquals(new int[]{1, 1, 2, 2},
                mergeSortedPartsInSameArray(new int[]{1, 2, 1, 2}, 0, 2, 4));
        assertArrayEquals(new int[]{1, 2, 3, 4},
                mergeSortedPartsInSameArray(new int[]{2, 4, 1, 3}, 0, 2, 4));
        assertArrayEquals(new int[]{1, 2, 3, 4},
                mergeSortedPartsInSameArray(new int[]{2, 3, 4, 1}, 0, 3, 4));
        assertArrayEquals(new int[]{1, 2, 3, 4},
                mergeSortedPartsInSameArray(new int[]{1, 2, 3, 4}, 0, 0, 4));
        assertArrayEquals(new int[]{2, 4},
                mergeSortedPartsInSameArray(new int[]{4, 2}, 0, 1, 2));
    }

    @Test
    public void mergeSortedPartsInSameArraySortTest() {
        assertArrayEquals(new int[]{2, 4}, mergeSortedPartsInSameArraySort(new int[]{4, 2}));
        assertArrayEquals(new int[]{1, 1, 2, 2},
                mergeSortedPartsInSameArraySort(new int[]{1, 2, 1, 2}));
        assertArrayEquals(new int[]{1, 1, 2, 2},
                mergeSortedPartsInSameArraySort(new int[]{2, 1, 2, 1}));
        assertArrayEquals(new int[]{1, 2, 2},
                mergeSortedPartsInSameArraySort(new int[]{2, 1, 2}));
        assertArrayEquals(new int[]{1, 1, 2},
                mergeSortedPartsInSameArraySort(new int[]{2, 1, 1}));
    }

    @Test
    public void mergeSortedArraysSortTest() {
        assertArrayEquals(new int[]{2, 4},
                mergeSortedArraysSort(new int[]{4, 2}));
        assertArrayEquals(new int[]{1, 1, 2, 2},
                mergeSortedArraysSort(new int[]{1, 2, 1, 2}));
        assertArrayEquals(new int[]{1, 1, 2, 2},
                mergeSortedArraysSort(new int[]{2, 1, 2, 1}));
        assertArrayEquals(new int[]{1, 2, 2},
                mergeSortedArraysSort(new int[]{2, 1, 2}));
        assertArrayEquals(new int[]{1, 1, 2},
                mergeSortedArraysSort(new int[]{2, 1, 1}));
    }
}
