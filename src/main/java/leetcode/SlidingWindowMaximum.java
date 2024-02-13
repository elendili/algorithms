package leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// https://leetcode.com/problems/sliding-window-maximum/
public class SlidingWindowMaximum {

    // via TreeMap, worst of it
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return new int[]{};
        }
        if (k == 1 || nums.length == 1) {
            return nums;
        }
        int[] out = new int[nums.length - k + 1];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0, j = 0; i < out.length; i++) {
            if (map.isEmpty()) {
                for (; j < k; j++) {
                    map.compute(nums[j], (_k, v) -> v == null ? 0 : v + 1);
                }
            } else {
                int left = nums[i - 1];
                map.compute(left, (_k, v) -> {
                    if (v != null) {
                        if (v.equals(0)) {
                            v = null;
                        } else {
                            v -= 1;
                        }
                    }
                    return v;
                });
                // add to map
                int right = nums[k - 1 + i];
                map.compute(right, (_k, v) -> v == null ? 0 : v + 1);
            }
            out[i] = map.lastKey();
        }
        return out;
    }

    // via HashMap + Heap (PriorityQueue), so-so
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return new int[]{};
        }
        if (k == 1 || nums.length == 1) {
            return nums;
        }
        int[] out = new int[nums.length - k + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0, j = 0; i < out.length; i++) {
            if (pq.isEmpty()) {
                for (; j < k; j++) {
                    int toAdd = nums[j];
                    map.compute(toAdd, (_k, v) -> v == null ? 0 : v + 1);
                    pq.add(toAdd);
                }
            } else {
                int left = nums[i - 1];
                map.compute(left, (_k, v) -> {
                    if (v != null) {
                        if (v.equals(0)) {
                            v = null;
                        } else {
                            v -= 1;
                        }
                    }
                    return v;
                });
                // add to map
                int right = nums[k - 1 + i];
                map.compute(right, (_k, v) -> v == null ? 0 : v + 1);
                // pq
                while (!map.containsKey(pq.peek())) {
                    pq.remove();
                }
                pq.add(right);
            }
            out[i] = pq.peek();
        }
        return out;
    }

    // via deque
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k < 1) {
            return new int[]{};
        }
        if (k == 1 || n == 1) {
            return nums;
        }
        int[] out = new int[n - k + 1];
        Deque<Integer> indexes = new ArrayDeque<>();
        // first element of deq is index of max element
        for (int i = 0,j=0; i < n; i++) {
            while (!indexes.isEmpty() && indexes.peekFirst() < i - k+1) {
                indexes.removeFirst();
            }
            while (!indexes.isEmpty() && nums[indexes.peekLast()] < nums[i]) {
                indexes.removeLast();
            }
            indexes.addLast(i);

            if(!indexes.isEmpty() && i>=k-1) {
                out[j++] = nums[indexes.peekFirst()];
            }
        }
        return out;
    }

    @Test
    public void test() {
        assertArrayEquals(new int[]{},
                maxSlidingWindow(new int[]{}, 3));
        assertArrayEquals(new int[]{1},
                maxSlidingWindow(new int[]{1}, 3));

        assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7},
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
        assertArrayEquals(new int[]{3, 3, -1, 5, 5, 6, 7},
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 2));
        assertArrayEquals(new int[]{3, 5, 5, 6, 7},
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 4));
        assertArrayEquals(new int[]{5, 5, 6, 7},
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 5));
        assertArrayEquals(new int[]{5, 6, 7},
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 6));
        assertArrayEquals(new int[]{6, 7},
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 7));
        assertArrayEquals(new int[]{7},
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 8));
    }
}
