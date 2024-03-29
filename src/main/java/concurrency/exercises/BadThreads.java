package concurrency.exercises;

public class BadThreads {

    static volatile String message;

    private static class CorrectorThread extends Thread {
        public void run() {
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
            // Key statement 1:
            message = "Mares do eat oats.";
        }
    }

    public static void main(String args[]) throws InterruptedException {
        (new CorrectorThread()).start();
        message = "Mares do not eat oats.";
        Thread.sleep(200);
        // Key statement 2:
        System.out.println(message);
    }
}