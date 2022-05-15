package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/median-of-two-sorted-arrays/

The solution copied from:
https://medium.com/@hazemu/finding-the-median-of-2-sorted-arrays-in-logarithmic-time-1d3f2ecbeb46
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] a, int[] b) {
        // Make sure we always search the shorter array.
        if (a.length > b.length) {
            int[] t = a;
            a = b;
            b = t;
        }
        // length of left half of virtually 'merged' array
        int leftHalfLength = (a.length + b.length + 1) / 2;
        // Since A is guaranteed to be the shorter array,
        // we know it can contribute 0 or all of its values.
        int aMinCount = 0;
        int aMaxCount = a.length;

        while (aMinCount <= aMaxCount) {
            int aCount = aMinCount + ((aMaxCount - aMinCount) / 2);
            int bCount = leftHalfLength - aCount;

            //
            // Make sure aCount is greater than 0 (because A can contribute 0 values;
            // remember that A is either shorter or of the same length as B). This also 
            // implies bCount will be less than B.Length since it won't be possible 
            // for B to contribute all of its values if A has contributed at least 1
            // value.
            //

            if (aCount > 0 && a[aCount - 1] > b[bCount]) {
                // Decrease A's contribution size; x lies in the right half.
                aMaxCount = aCount - 1;
            } else if (aCount < a.length && b[bCount - 1] > a[aCount]) {
                //
                // Make sure aCount is less than A.Length since A can actually contribute
                // all of its values (remember that A is either shorter or of the same
                // length as B). This also implies bCount > 0 because B has to contribute
                // at least 1 value if aCount < A.Length.
                //

                // Decrease B's contribution size, i.e. increase A's contribution size;
                // y lies in the right half.
                aMinCount = aCount + 1;
            } else {
                //
                // Neither x nor y lie beyond the left half. We found the right aCount.
                // We don't know how x and y compare to each other yet though.
                //

                //
                // If a.length + b.length is odd, the median is the greater of x and y.
                //
                int leftHalfEnd =
                        (aCount == 0)             // A not contributing?
                                ? b[bCount - 1]       // aCount = 0 implies bCount > 0
                                : (bCount == 0)       // B is not contributing?
                                ? a[aCount - 1]   // bCount = 0 implies aCount > 0
                                : Math.max(a[aCount - 1], b[bCount - 1]);

                if ((a.length + b.length) % 2 == 1) {
                    return leftHalfEnd;
                }

                //
                // a.length + b.length is even. To compute the median, we need to find
                // the first element in the right half, which will be the smaller
                // of A[aCount] and B[bCount]. Remember that aCount could be equal
                // to A.Length, bCount could be equal to B.Length (if all the values
                // of A or B are in the left half).
                //
                int rightHalfStart =
                        (aCount == a.length)          // A is all in the left half?
                                ? b[bCount]           // aCount = a.length implies bCount < B.Length
                                : (bCount == b.length)    // B is all in the left half?
                                ? a[aCount]       // bCount = B.Length implies aCount < A.Length
                                : Math.min(a[aCount], b[bCount]);
                return (leftHalfEnd + rightHalfStart) / 2.0;
            }

        }
        throw new IllegalStateException("unreachable code line");
    }

    @Test
    public void normal() {
        Assertions.assertEquals(2, findMedianSortedArrays(new int[]{2}, new int[]{1, 3}));
        Assertions.assertEquals(2, findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        Assertions.assertEquals(2.5, findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        Assertions.assertEquals(2.5, findMedianSortedArrays(new int[]{3, 4}, new int[]{1, 2}));
        Assertions.assertEquals(0, findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0}));
    }

    @Test
    public void fromSite() {
        Assertions.assertEquals(1.5, findMedianSortedArrays(new int[]{2}, new int[]{1}));
        Assertions.assertEquals(100000.5, findMedianSortedArrays(new int[]{100001}, new int[]{100000}));
    }

    @Test
    public void edge() {
        Assertions.assertEquals(1, findMedianSortedArrays(new int[]{}, new int[]{1}));
        Assertions.assertEquals(1, findMedianSortedArrays(new int[]{1}, new int[]{}));
        Assertions.assertEquals(1.5, findMedianSortedArrays(new int[]{1}, new int[]{2}));
        Assertions.assertEquals(1.5, findMedianSortedArrays(new int[]{}, new int[]{1, 2}));
        Assertions.assertEquals(2.5, findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3, 4}));
        Assertions.assertEquals(2, findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{}));
    }
}
