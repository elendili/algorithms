package luxoft.quiz2021July14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Counter {
    final int amount = 1_000_000;
    private volatile int c = 0;

    void run() {
        for (int i = 0; i < amount; i++) {
            c++;
        }
    }

    @Test
    public void test() throws InterruptedException {
        Counter counter = new Counter();
        Thread th1 = new Thread(counter::run);
        Thread th2 = new Thread(counter::run);
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        Assertions.assertEquals(counter.c, amount * 2);
    }
}
