package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/circular-array-loop/
public class CircularArrayLoop {
    class Solution {
        public boolean circularArrayLoop(int[] a) {
            if (a == null || a.length < 2) {
                return false;
            }
            int n = a.length;
            Map<Integer, Integer> badLoopMeetings = new HashMap<>();
            for (int k, i = 0, j = 0;
                 i < n && j < n; ) {
                i = index(n, i, a[i]);
                int _j = index(n, j, a[j]);
                k = a[_j];
                j = index(n, _j, k);
                if (i == j) { // loop found
                    int size = measureLoop(a, i);
                    if (size > 1) {
                        return true;
                    } else {
                        badLoopMeetings.compute(i, (key, v) -> v == null ? 1 : v + 1);
                        int u = badLoopMeetings.get(i);
                        if (u >= n) {
                            return false;
                        } else {
                            i += u;
                            j += u;
                        }
                    }
                }
            }
            return false;
        }

        // return negative loop length if loop has direction change
        int measureLoop(int[] a, int _i) {
            int out = 0;
            int k, _k, i = _i;
            do {
                _k = a[i];
                i = index(a.length, i, _k);
                k = a[i];
                out += 1;
                if (k > 0 && _k < 0 || k < 0 && _k > 0) {
                    return -out;
                }
            } while (i != _i);
            return out;
        }

        int index(int n, int i, int k) {
            int out = (i + k) % n; // calc new index
            return (n + out) % n; // make positive
        }
    }


    @Test
    public void test() {
        Solution s = new Solution();
        Assertions.assertFalse(s.circularArrayLoop(new int[]{2, -1, 1, -2, -2}));
        Assertions.assertTrue(s.circularArrayLoop(new int[]{1, -1, 2, 4, 4}));
        Assertions.assertFalse(s.circularArrayLoop(new int[]{-1, -2, -3, -4, -5}));
        Assertions.assertTrue(s.circularArrayLoop(new int[]{3, 1, 2}));

        Assertions.assertTrue(s.circularArrayLoop(new int[]{2, -1, 1, 2, 2}));
        Assertions.assertTrue(s.circularArrayLoop(new int[]{1, 1, 1}));
        Assertions.assertTrue(s.circularArrayLoop(new int[]{-1, -1, -1}));
        Assertions.assertFalse(s.circularArrayLoop(new int[]{-1, 1, -1}));
        Assertions.assertFalse(s.circularArrayLoop(new int[]{-1, 2}));
        Assertions.assertFalse(s.circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
    }

}
