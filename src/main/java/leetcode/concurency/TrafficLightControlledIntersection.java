package leetcode.concurency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode.com/problems/traffic-light-controlled-intersection/
 */
public class TrafficLightControlledIntersection {

    private final ReentrantLock lock = new ReentrantLock();
    private int currentRoadId = 1;

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        lock.lock();
        try {
            if (currentRoadId != roadId) {
                turnGreen.run();
                currentRoadId = roadId;
            }
            crossCar.run();
        } finally {
            lock.unlock();
        }

    }
}
