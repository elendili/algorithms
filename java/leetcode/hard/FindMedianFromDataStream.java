package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

// https://leetcode.com/problems/find-median-from-data-stream/
public class FindMedianFromDataStream {

    interface MedianFinderInterface {
        MedianFinderInterface addNum(int... num);

        MedianFinderInterface clear();

        double findMedian();
    }

    static class MedianFinder implements MedianFinderInterface {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        public MedianFinder() {
        }

        public void addNum(int num) {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
            if (minHeap.size() < maxHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }
        }

        public MedianFinder clear() {
            minHeap.clear();
            maxHeap.clear();
            return this;
        }

        @Override
        public MedianFinder addNum(int... nums) {
            Arrays.stream(nums).forEach(this::addNum);
            return this;
        }

        public double findMedian() {
            if (minHeap.isEmpty()) {
                throw new IllegalArgumentException("No data to find median");
            }
            return minHeap.size() > maxHeap.size()
                    ? minHeap.peek()
                    : (minHeap.peek() + maxHeap.peek()) / 2.0;
        }

    }

    // use 2 heaps sorted in opposite way in such a way that head of one is the closest element of head of another heap.
    static class MedianFinderAgain implements MedianFinderInterface {
        // head is the smallest value, content is the biggest elements of stream
        private final PriorityQueue<Integer> minQ = new PriorityQueue<>();
        // head is the biggest value, content is from the smallest elements of stream
        private final PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());

        public void addNum(int num) {
            minQ.add(num); // updates head
            maxQ.add(minQ.poll()); // moves smallest head of minHeap to maxHeap, and it should replace head,
            if (maxQ.size() > minQ.size()) {
                minQ.add(maxQ.poll()); // keep minHeap equal in size or bigger than maxHeap
            }
        }

        public MedianFinderAgain clear() {
            minQ.clear();
            maxQ.clear();
            return this;
        }

        public MedianFinderAgain addNum(int... nums) {
            Arrays.stream(nums).forEach(this::addNum);
            return this;
        }

        public double findMedian() {
            if (minQ.isEmpty()) {
                throw new IllegalArgumentException("No data to find median");
            } else {
                // because minHeap is equal in size or bigger than maxHeap
                if (minQ.size() > maxQ.size()) {
                    return minQ.peek();
                } else {
                    return (minQ.peek() + maxQ.peek()) / 2.0;
                }
            }
        }

    }


    public static Stream<Arguments> predicateStream() {
        return Stream.of(
                Arguments.arguments("MedianFinder", new MedianFinder()),
                Arguments.arguments("MedianFinderAgain", new MedianFinderAgain())
        );
    }


    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void testEmpty(String name, MedianFinderInterface cut) {
        Assertions.assertThrows(IllegalArgumentException.class, cut::findMedian);
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void test_NEGATIVE_INFINITY(String name, MedianFinderInterface cut) {
        Assertions.assertEquals(-0.5, cut.addNum(Integer.MAX_VALUE, Integer.MIN_VALUE).findMedian());
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void test(String name, MedianFinderInterface cut) {
        Assertions.assertEquals(1, cut.clear().addNum(1).findMedian());
        Assertions.assertEquals(2, cut.clear().addNum(1, 2, 3).findMedian());
        Assertions.assertEquals(1.5, cut.clear().addNum(1, 2).findMedian());
        Assertions.assertEquals(4, cut.clear().addNum(1, 2, 4, 4, 4, 5, 6).findMedian());
        Assertions.assertEquals(1.5, cut.clear().addNum(1, 1, 1, 2, 2, 2).findMedian());
        Assertions.assertEquals(1, cut.clear().addNum(-100, 100, 0, 1, 2).findMedian());
        Assertions.assertEquals(-1, cut.clear().addNum(-100, 0, -1, -1, 2).findMedian());
    }
}
