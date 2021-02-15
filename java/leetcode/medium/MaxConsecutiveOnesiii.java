package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/max-consecutive-ones-iii/
public class MaxConsecutiveOnesiii {

    /* Here I use sliding window from queue imitation, but unfortunately the great solution is much simpler:
        public int longestOnes(int[] A, int K) {
        int left = 0;
        for (int right = 0; right < A.length; right++) {
            K -= 1 - A[right];
            if (K < 0) {
                K += 1 - A[left++];
            }
        }
        return A.length - left;
    }
*/
    public int longestOnes(int[] a, int k) {
        int outMax = 0;
        int countOf1 = 0; // count of ones in local space between zeroes or array boundaries
        int window = 0; // local count of 1s after last not-replaced zero
        int[] windowQueue = new int[k];  // save count of 1s before every replaced zero (plus 1 for replaced zero)
        int size = 0; // queue filled size
        int iq = 0;   // index in queue
        for (int i : a) {
            countOf1 += i;
            if (i == 0) {
                if (k > 0) { // use queue only if k>0
                    if (size == k) {
                        window -= windowQueue[iq]; // subtract count of dropped ones from window
                    } else {
                        size += 1; // track size (element will be added later)
                    }
                    countOf1 += 1; // take current replacement in consideration
                    window += windowQueue[iq] = countOf1; // add count to queue and to window
                    iq = (iq + 1) % k;
                }
                countOf1 = 0; // reset of count
            }
            outMax = Math.max(window + countOf1, outMax);
        }
        return outMax;
    }

    @Test
    public void test_k0() {
        Assertions.assertEquals(1, longestOnes(new int[]{0, 1}, 0));
        Assertions.assertEquals(1, longestOnes(new int[]{1, 0}, 0));
        Assertions.assertEquals(2, longestOnes(new int[]{1, 1}, 0));
    }

    @Test
    public void test00() {
        Assertions.assertEquals(1, longestOnes(new int[]{0}, 1));
        Assertions.assertEquals(1, longestOnes(new int[]{0, 0}, 1));
    }

    @Test
    public void test01() {
        Assertions.assertEquals(2, longestOnes(new int[]{0, 1}, 1));
    }

    @Test
    public void test0() {
        Assertions.assertEquals(2, longestOnes(new int[]{1, 0, 0}, 1));
    }

    @Test
    public void test000() {
        Assertions.assertEquals(0, longestOnes(new int[]{0, 0, 0}, 0));
        Assertions.assertEquals(1, longestOnes(new int[]{0, 0, 0}, 1));
        Assertions.assertEquals(2, longestOnes(new int[]{0, 0, 0}, 2));
        Assertions.assertEquals(3, longestOnes(new int[]{0, 0, 0}, 3));
    }

    @Test
    public void test_k2() {
//        Assertions.assertEquals(3, longestOnes(new int[]{1, 0, 0}, 2));
        Assertions.assertEquals(3, longestOnes(new int[]{0, 0, 1}, 2));
        Assertions.assertEquals(3, longestOnes(new int[]{0, 1, 0}, 2));
    }

    @Test
    public void test_k3_1() {
        Assertions.assertEquals(4, longestOnes(new int[]{1, 0, 0, 0, 0}, 3));
        Assertions.assertEquals(4, longestOnes(new int[]{0, 0, 1, 0, 0}, 3));
    }

    @Test
    public void test_k3() {
        Assertions.assertEquals(4, longestOnes(new int[]{0, 0, 0, 1, 0}, 3));
        Assertions.assertEquals(4, longestOnes(new int[]{0, 1, 0, 0, 0}, 3));
        Assertions.assertEquals(4, longestOnes(new int[]{0, 0, 0, 0, 1}, 3));
        Assertions.assertEquals(5, longestOnes(new int[]{0, 1, 0, 1, 0}, 3));
        Assertions.assertEquals(5, longestOnes(new int[]{1, 1, 0, 1, 0}, 3));
        Assertions.assertEquals(5, longestOnes(new int[]{0, 1, 0, 1, 1}, 3));
        Assertions.assertEquals(5, longestOnes(new int[]{1, 0, 1, 0, 0}, 3));
    }

    @Test
    public void test001() {
        Assertions.assertEquals(2, longestOnes(new int[]{0, 0, 1}, 1));
    }

    @Test
    public void test011() {
        Assertions.assertEquals(3, longestOnes(new int[]{0, 1, 1}, 1));
    }

    @Test
    public void test101() {
        Assertions.assertEquals(3, longestOnes(new int[]{1, 0, 1}, 1));
    }

    @Test
    public void test111() {
        Assertions.assertEquals(3, longestOnes(new int[]{1, 1, 1}, 1));
    }

    @Test
    public void testOriginal() {
        Assertions.assertEquals(6, longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    @Test
    public void testOriginal2() {
        Assertions.assertEquals(10, longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    @Test
    public void testOriginal3() {
        Assertions.assertEquals(4, longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 0));
    }
}
