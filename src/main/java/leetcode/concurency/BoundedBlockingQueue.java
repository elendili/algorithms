package leetcode.concurency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;

public class BoundedBlockingQueue {
    private final Queue<Integer> data;
    private final int capacity;

    BoundedBlockingQueue(int capacity) {
        this.data = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    void enqueue(Integer element) throws InterruptedException {
        synchronized (data) {
            while (data.size() >= capacity) {
                data.wait();
            }
            data.add(element);
            data.notifyAll();
        }
    }

    Integer dequeue() throws InterruptedException {
        synchronized (data) {
            while (data.isEmpty()) {
                data.wait();
            }
            Integer out = data.poll();
            data.notifyAll();
            return out;
        }
    }

    int size() {
        return data.size();
    }
}


class BoundedBlockingQueueTest {
    @Test
    public void testBBQ() throws InterruptedException {
        int capacity = 2;
        final BoundedBlockingQueue bbq = new BoundedBlockingQueue(capacity);
        int threadCount = 6;
        ExecutorService e = Executors.newFixedThreadPool(threadCount);
        AtomicInteger ai = new AtomicInteger(1);
        List<Integer> out = new CopyOnWriteArrayList<>();
        int loopCount = threadCount * 10;
        e.submit(() -> {
            for (int i = 0; i < loopCount; i++) {
                assert bbq.size() <= capacity;
                try {
                    out.add(bbq.dequeue());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        e.submit(() -> {
            for (int i = 0; i < loopCount; i++) {
                try {
                    bbq.enqueue(ai.getAndIncrement());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                assert bbq.size() <= capacity;
            }
        });
        e.shutdown();
        e.awaitTermination(100, TimeUnit.MILLISECONDS);
        System.out.println(out);
        Assertions.assertEquals(loopCount, out.size());
//        Assertions.assertTrue(isSortedAndUnique(out));
    }


}

class SortingCheck {
    static boolean isSortedAndUnique(List<Integer> integers) {
        return isSortedAndUnique(integers, 0);
    }

    static boolean isSortedAndUnique(List<Integer> integers, int index) {
        if (integers.isEmpty() || index >= integers.size() - 1) {
            return true;
        } else {
            if (integers.get(index) >= integers.get(index + 1)) {
                return false;
            }
            return isSortedAndUnique(integers, index + 1);
        }
    }

    @Test
    public void testSorted() {
        Assertions.assertTrue(isSortedAndUnique(asList(1, 2, 3)));
        Assertions.assertTrue(isSortedAndUnique(asList(1, 2)));
        Assertions.assertTrue(isSortedAndUnique(asList(1)));
    }

    @Test
    public void testUnSorted() {
        Assertions.assertFalse(isSortedAndUnique(asList(1, 1)));
        Assertions.assertFalse(isSortedAndUnique(asList(2, 1)));
        Assertions.assertFalse(isSortedAndUnique(asList(2, 1, 0)));
        Assertions.assertFalse(isSortedAndUnique(asList(1, 1, 2)));
        Assertions.assertFalse(isSortedAndUnique(asList(1, 2, 2)));
        Assertions.assertFalse(isSortedAndUnique(asList(1, 2, 2, 3)));
    }
}