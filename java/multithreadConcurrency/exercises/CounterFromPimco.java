package multithreadConcurrency.exercises;

public class CounterFromPimco implements Runnable {
    final static int iterations = 4_000_000;
    private int counter;

    public synchronized void inc() {
        counter++;
    }

    public synchronized void dec() {
        counter--;
    }

    public synchronized int get() {
        return counter;
    }

    public void run() {
        for (int i = 0; i < iterations; i++) {
            inc();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CounterFromPimco test2 = new CounterFromPimco();
        Thread t = new Thread(test2);
        t.start();
        for (int i = 0; i < iterations; i++) {
            test2.dec();
        }
        System.out.println(test2.get());
        t.join();
    }

}
