package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeBasedKeyValueStore_981 {

    class TimeMap {
        Map<String, List<Pair>> storage = new HashMap<>();

        public TimeMap() {

        }

        record Pair(String value, int timestamp) {
        }

        public void set(String key, String value, int timestamp) {
            List<Pair> forKey = storage.computeIfAbsent(key, _ -> new ArrayList<>());
            forKey.add(new Pair(value, timestamp));
        }

        public String get(String key, int timestamp) {
            List<Pair> forKey = storage.get(key);
            if (forKey == null) {
                return "";
            }
            int l = 0, r = forKey.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                Pair v = forKey.get(mid);
                if (v.timestamp >= timestamp) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            l = Math.min(l, forKey.size() - 1);

            Pair p = forKey.get(l);
            String out = "";
            if (p.timestamp() <= timestamp) {
                out = p.value();
            } else if (l > 0) {
                out = forKey.get(l - 1).value();
            }
            return out;
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        assertEquals("bar", timeMap.get("foo", 1));
        assertEquals("bar", timeMap.get("foo", 3));
        timeMap.set("foo", "bar2", 4);
        assertEquals("bar2", timeMap.get("foo", 4));
        assertEquals("bar2", timeMap.get("foo", 5));
        assertEquals("bar", timeMap.get("foo", 3));
        assertEquals("", timeMap.get("foo", 0));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        timeMap.set("foo", "bar2", 4);
        assertEquals("bar", timeMap.get("foo", 3));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        TimeMap timeMap = new TimeMap();
        timeMap.set("a", "bar", 1);
        timeMap.set("x", "b", 3);
        assertEquals("", timeMap.get("b", 3));
        timeMap.set("foo", "bar2", 4);
        assertEquals("bar2", timeMap.get("foo", 4));
        assertEquals("bar2", timeMap.get("foo", 5));
    }
}
