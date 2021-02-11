import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class rideTest {
    @Test
    public void test() {
        Assertions.assertTrue(ride.decideToGo("COMETQ", "HVNGAT"));
        Assertions.assertFalse(ride.decideToGo("ABSTAR", "USACO"));
    }
}
