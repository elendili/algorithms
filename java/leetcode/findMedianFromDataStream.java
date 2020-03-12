package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/find-median-from-data-stream/
public class findMedianFromDataStream {
    class MedianFinder {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        public MedianFinder() {
        }

        public MedianFinder(int... ints) {
            this();
            for (int i : ints) {
                addNum(i);
            }
        }

        public void addNum(int num) {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
            if (minHeap.size() < maxHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }
        }

        public double findMedian() {
            return minHeap.size() > maxHeap.size()
                    ? minHeap.peek()
                    : (minHeap.peek() + maxHeap.peek()) / 2.0;
        }

    }

    @Test
    public void test() {
        Assertions.assertEquals(Double.NEGATIVE_INFINITY,
                new MedianFinder().findMedian());
        Assertions.assertEquals(1,
                new MedianFinder(1).findMedian());
        Assertions.assertEquals(2,
                new MedianFinder(1, 2, 3).findMedian());
        Assertions.assertEquals(1.5,
                new MedianFinder(1, 2).findMedian());
        Assertions.assertEquals(4,
                new MedianFinder(1, 2, 4, 4, 4, 5, 6).findMedian());
        Assertions.assertEquals(1.5,
                new MedianFinder(1, 1, 1, 2, 2, 2).findMedian());
        Assertions.assertEquals(1,
                new MedianFinder(-100, 100, 0, 1, 2).findMedian());
        Assertions.assertEquals(-1,
                new MedianFinder(-100, 0, -1, -1, 2).findMedian());
    }
}
