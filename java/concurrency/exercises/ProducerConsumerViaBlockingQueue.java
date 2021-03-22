package concurrency.exercises;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ProducerConsumerViaBlockingQueue {
    public static void main(String[] s) {
//        BlockingQueue<String> bq = new ArrayBlockingQueue<>(10);
        BlockingQueue<String> bq = new SynchronousQueue<>();
        (new Thread(new Producer2(bq))).start();
        (new Thread(new Consumer2(bq))).start();
    }
}

class Producer2 implements Runnable {
    private BlockingQueue<String> drop;

    public Producer2(BlockingQueue<String> drop) {
        this.drop = drop;
    }

    public void run() {
        String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        Random random = new Random();
        try {
            for (int i = 0;
                 i < importantInfo.length;
                 i++) {
                drop.put(importantInfo[i]);
                Thread.sleep(random.nextInt(100));
            }
            drop.put("DONE");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer2 implements Runnable {
    private BlockingQueue<String> drop;

    public Consumer2(BlockingQueue<String> drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        try {
            for (String message = drop.take();
                 !message.equals("DONE");
                 message = drop.take()) {
                System.out.format("MESSAGE RECEIVED: %s%n", message);
                Thread.sleep(random.nextInt(100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

