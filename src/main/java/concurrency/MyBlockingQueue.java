package concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static helpers.ConcurrencyHelpers.sleep;

public class MyBlockingQueue<E> {
    private final LinkedList<E> list = new LinkedList<>();
    final int limit;

    public MyBlockingQueue(int limit) {
        this.limit = limit;
    }

    synchronized void blockingAdd(E e) {
        while (list.size() >= limit) {
            try {
                wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        list.add(e);
        notifyAll();
    }

    synchronized E blockingPoll() {
        while (list.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        E out = list.poll();
        notifyAll();
        return out;
    }

}

class MyBlockingQueueTest {
    @Test
    public void test() throws InterruptedException {
        final int count = 10_000;
        final ThreadLocalRandom tlr = ThreadLocalRandom.current();
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(5);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Integer> out = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(2);
        // supplier
        threadPool.submit(() -> {
            for (int i = 0; i < count; i++) {
                sleep(tlr.nextInt(2));
                queue.blockingAdd(i);
            }
            latch.countDown();
        });
        // consumer
        threadPool.submit(() -> {
            for (int i = 0; i < count; i++) {
                sleep(tlr.nextInt(2));
                out.add(queue.blockingPoll());
            }
            latch.countDown();
        });
        latch.await();
        String expected = IntStream.range(0, count).boxed().toList().toString();
        Assertions.assertEquals(expected, out.toString());
    }
}
