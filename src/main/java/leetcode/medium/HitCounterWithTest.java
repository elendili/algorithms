package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/design-hit-counter/
 */
public class HitCounterWithTest {
    @Test
    public void test() {
        /**
         * ["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
         * [[], [1], [2], [3], [4], [300], [300], [301]]
         * Output
         * [null, null, null, null, 3, null, 4, 3]
         */
        HitCounter hc = new HitCounter();
        hc.hit(1);
        hc.hit(2);
        hc.hit(3);
        assertEquals(3, hc.getHits(4));
        hc.hit(300);
        assertEquals(4, hc.getHits(300));
        assertEquals(3, hc.getHits(301));
    }

    class HitCounter {
        private final java.util.NavigableMap<Integer, Integer> timestampToHitCountMap;
        private final int timeoutSeconds;

        public HitCounter() {
            this.timestampToHitCountMap = new TreeMap<>();
            timeoutSeconds = 300;
        }

        public void hit(int timestamp) {
            timestampToHitCountMap.compute(timestamp, (k, v) -> {
                v = v == null ? 0 : v;
                return v + 1;
            });
        }

        public int getHits(int timestamp) {
            int exclusiveStart = timestamp - timeoutSeconds;
            Map<Integer, Integer> headMap = timestampToHitCountMap.headMap(exclusiveStart, true);
            headMap.clear();
            int out = timestampToHitCountMap.tailMap(exclusiveStart, false).values().stream()
                    .mapToInt(e -> e).sum();
            return out;
        }
    }
}
