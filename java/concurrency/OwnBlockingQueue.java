package concurrency;

import helpers.Helpers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OwnBlockingQueue<E> {
    private final LinkedList<E> list = new LinkedList<>();
    final int limit;

    public OwnBlockingQueue(int limit) {
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

class OwnBlockingQueueTest {
    @Test
    public void test() throws InterruptedException {
        final int count = 10_000;
        final ThreadLocalRandom tlr = ThreadLocalRandom.current();
        OwnBlockingQueue<Integer> queue = new OwnBlockingQueue<>(5);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Integer> out = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(2);
        threadPool.submit(() -> {
            for (int i = 0; i < count; i++) {
                Helpers.sleep(tlr.nextInt(2));
                queue.blockingAdd(i);
            }
            latch.countDown();
        });
        threadPool.submit(() -> {
            for (int i = 0; i < count; i++) {
                Helpers.sleep(tlr.nextInt(2));
                out.add(queue.blockingPoll());
            }
            latch.countDown();
        });
        latch.await();
        String expected = IntStream.range(0, count).boxed().collect(Collectors.toList()).toString();
        Assertions.assertEquals(expected, out.toString());
    }
}
