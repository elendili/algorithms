package Algorithms_R_Sedgewick;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
A ring buffer, or circular queue, is a FIFO data structure of a fixed size N.
It is useful for transferring data between asynchronous processes or for storing log files.
 When the buffer is empty, the consumer waits until data is deposited;
 when the buffer is full, the producer waits to deposit data.
 Develop an API for a RingBuffer and an implementation that uses an array representation
  (with circular wrap-around).
 */
public class _1_3_39_RingBuffer {
    // I've found that invariant should be on element of array, not on amount of array's elements
    static class RingBuffer<T> {
        private final Object[] a;
        private int ii = 0; // insert index
        private int ai = 0; // access index

        public RingBuffer(int size) {
            this.a = new Object[size];
        }

        synchronized void enqueue(T e) { // important
            while (a[ii] != null) { // important
                try {
                    wait();
                } catch (InterruptedException t) {
                    throw new RuntimeException(t);
                }
            }
            a[ii] = e;
            ii = ++ii % a.length;
            notifyAll(); // important
        }

        synchronized T dequeue() {  // important
            while (a[ai] == null) { // important
                try {
                    wait();
                } catch (InterruptedException t) {
                    throw new RuntimeException(t);
                }
            }
            final T e;
            e = (T) a[ai];
            a[ai] = null;
            ai = ++ai % a.length;
            notifyAll(); // important
            return e;
        }
    }


    @ParameterizedTest
    @CsvSource({
            "1, 5, 100",
            "1, 2, 20",
            "1, 5, 20",
            "3, 5, 100",
            "3, 4, 20",
            "3, 5, 20",
            "100, 4, 100",
            "100, 4, 1000",
            "100, 5, 1000",
            "100, 5, 10000",
            "100, 19, 10000",
            "10, 19, 10000",
    })
    public void concurrentTest(int sizeOfRB, int threadsCount, int elementsCount) {
        final RingBuffer<Integer> rb = new RingBuffer<>(sizeOfRB);
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        List<Integer> actual = new CopyOnWriteArrayList<>(); // important
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < elementsCount; i++) {
            final int j = i;
            futures.add(CompletableFuture.runAsync(() -> rb.enqueue(j), executorService));
            futures.add(CompletableFuture.runAsync(() -> actual.add(rb.dequeue()), executorService));
        }
        futures.forEach(CompletableFuture::join);
        String expected = IntStream.range(0, elementsCount).boxed().collect(Collectors.toCollection(TreeSet::new)).toString();
        Assertions.assertFalse(actual.contains(null));
        Assertions.assertEquals(elementsCount, actual.size());
        Assertions.assertEquals(expected, new TreeSet<>(actual).toString()
        );
    }
}
