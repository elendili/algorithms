package misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Push multiple numbers, poll returns last 10 biggest.
 * Use only array
 * <p>
 * solved using heapify...
 */
public class DmitriSavvinsInterview {
    @Test
    public void test() {
        BoundedHeap bh = new BoundedHeap(10);
        bh.add(9);
        bh.add(8);
        bh.add(6);
        bh.add(3);
        bh.add(4);
        bh.add(2);
        bh.add(1);
        bh.add(7);
        bh.add(5);
        bh.add(0);
        bh.add(9);
        double[] actual = bh.getAll();
        Assertions.assertEquals(10, actual.length);
        Assertions.assertEquals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 9.0]", Arrays.toString(actual));
    }

    static class BoundedHeap {
        private final int capacity;
        private int size = 0;
        double[] array;

        BoundedHeap(int capacity) {
            this.capacity = capacity;
            this.array = new double[capacity + 1];
        }

        private void swap(int indexOne, int indexTwo) {
            double temp = array[indexOne];
            array[indexOne] = array[indexTwo];
            array[indexTwo] = temp;
        }

        public void add(double item) {
            array[size] = item;
            size++;
            heapifyUp();
            while (size > capacity) {
                poll();
            }
        }

        public double poll() {
            assert size > 0;
            double item = array[0];
            array[0] = array[size - 1];
            size--;
            heapifyDown();
            return item;
        }

        public double[] getAll() {
            double[] out = new double[size];
            double item;
            int i = 0;
            while (size > 0) {
                item = poll();
                out[i++] = item;
            }
            // return back
            for (double v : out) {
                add(v);
            }
            return out;
        }

        private void heapifyUp() {
            int index = size - 1;
            while (getParentIndex(index) >= 0 && array[getParentIndex(index)] > array[index]) {
                swap(getParentIndex(index), index);
                index = getParentIndex(index);
            }
        }

        public void heapifyDown() {
            int index = 0;
            while (hasLeftChild(index)) {
                int smallerChildIndex = getLeftChildIndex(index);
                if (hasRightChild(index)
                        && array[getRightChildIndex(index)] < array[getLeftChildIndex(index)]) {
                    smallerChildIndex = getRightChildIndex(index);
                }

                if (array[index] < array[smallerChildIndex]) {
                    break;
                } else {
                    swap(index, smallerChildIndex);
                }
                index = smallerChildIndex;
            }
        }

        public double peek() {
            if (size == 0) throw new IllegalStateException();
            return array[0];
        }

        private int getLeftChildIndex(int parentIndex) {
            return 2 * parentIndex + 1;
        }

        private int getRightChildIndex(int parentIndex) {
            return 2 * parentIndex + 2;
        }

        private int getParentIndex(int childIndex) {
            return (childIndex - 1) / 2;
        }

        private boolean hasLeftChild(int index) {
            return getLeftChildIndex(index) < size;
        }

        private boolean hasRightChild(int index) {
            return getRightChildIndex(index) < size;
        }

    }
}