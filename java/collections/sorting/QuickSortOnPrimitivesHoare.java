package collections.sorting;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.sort;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// Lomuto version with pivot = middle
public class QuickSortOnPrimitivesHoare {

    public int[] quickSort(int[] a) {
        quickSort(a, 0, a.length-1);
        System.out.println(stream(a).mapToObj(String::valueOf).collect(joining(", ")));
        return a;
    }

    public void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int pivotIndex = partition(a, lo, hi);
            quickSort(a, lo, pivotIndex-1);
            quickSort(a, pivotIndex + 1, hi);
        }
    }

    void swap(int[] a, int i1, int i2) {
        int t = a[i1];
        a[i1] = a[i2];
        a[i2] = t;
    }

    int partition(int[] a, final int lo, final int hi) {
        int pIndex = lo+(hi-lo)/2;
        int pV = a[pIndex];
        int i=lo;
        int j=hi;
        while(true){
            while(a[i]<pV){
                i++;
            }
            while(a[j]>pV){
                j--;
            }
            if(i>=j || a[i]==a[j]){ // condition a[i]==a[j] is absent on wikipedia
                return j;
            }
            swap(a,i,j);
        }
    }


    @Test
    public void mergeSortedPartsInSameArraySortTest() {
        assertArrayEquals(new int[]{1}, quickSort(new int[]{1}));
        assertArrayEquals(new int[]{1, 2}, quickSort(new int[]{2, 1}));
        assertArrayEquals(new int[]{1, 2, 3}, quickSort(new int[]{3, 2, 1}));
        assertArrayEquals(new int[]{1, 2, 3}, quickSort(new int[]{2, 3, 1}));
        assertArrayEquals(new int[]{1, 2, 3}, quickSort(new int[]{1, 3, 2}));
        assertArrayEquals(new int[]{1, 2, 3, 4}, quickSort(new int[]{4, 3, 2, 1}));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, quickSort(new int[]{3, 2, 5, 4, 1}));
        assertArrayEquals(new int[]{0, 1, 1}, quickSort(new int[]{1, 0, 1}));
        assertArrayEquals(new int[]{0, 1, 1}, quickSort(new int[]{1, 1, 0}));
        assertArrayEquals(new int[]{0, 0, 1, 1}, quickSort(new int[]{1, 1, 0, 0}));
        assertArrayEquals(new int[]{0, 0, 1, 1}, quickSort(new int[]{1, 0, 1, 0}));
        assertArrayEquals(new int[]{0, 0, 1, 1}, quickSort(new int[]{1, 0, 0, 1}));
        assertArrayEquals(new int[]{0, 0, 1, 1}, quickSort(new int[]{0, 1, 0, 1}));
        assertArrayEquals(new int[]{0, 0, 1, 1}, quickSort(new int[]{0, 0, 1, 1}));
        assertArrayEquals(new int[]{0, 0, 1, 1}, quickSort(new int[]{0, 1, 1, 0}));
        assertArrayEquals(new int[]{0, 1, 1, 1}, quickSort(new int[]{1, 1, 1, 0}));
        assertArrayEquals(new int[]{0, 1, 1, 1}, quickSort(new int[]{1, 1, 0, 1}));
        assertArrayEquals(new int[]{0, 1, 1, 1}, quickSort(new int[]{1, 0, 1, 1}));
        assertArrayEquals(new int[]{0, 1, 1, 1}, quickSort(new int[]{0, 1, 1, 1}));
        assertArrayEquals(new int[]{2, 4}, quickSort(new int[]{4, 2}));
        assertArrayEquals(new int[]{1, 1, 2, 2}, quickSort(new int[]{1, 2, 1, 2}));
        assertArrayEquals(new int[]{1, 1, 2, 2}, quickSort(new int[]{2, 1, 2, 1}));
        assertArrayEquals(new int[]{1, 2, 2}, quickSort(new int[]{2, 1, 2}));
        assertArrayEquals(new int[]{1, 1, 2}, quickSort(new int[]{2, 1, 1}));
    }
}
