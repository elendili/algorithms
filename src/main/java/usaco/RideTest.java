package usaco;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RideTest {
    @Test
    public void test() {
        Assertions.assertTrue(Ride.decideToGo("COMETQ", "HVNGAT"));
        Assertions.assertFalse(Ride.decideToGo("ABSTAR", "USACO"));
    }
}
