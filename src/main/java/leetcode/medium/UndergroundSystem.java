package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/design-underground-system/
 */
public class UndergroundSystem {
    record EnterLog(String stationName, int t) {
    }

    final Map<Integer, EnterLog> userIdToEnterLog;
    final Map<String, Map<String, int[]>> fromStationToStationDurations;

    public UndergroundSystem() {
        userIdToEnterLog = new HashMap<>();
        fromStationToStationDurations = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        userIdToEnterLog.put(id, new EnterLog(stationName, t));
    }


    public void checkOut(int id, String stationName, int t) {
        EnterLog enterLog = userIdToEnterLog.get(id);
        int duration = t - enterLog.t();

        String startStationName = enterLog.stationName();
        fromStationToStationDurations.putIfAbsent(startStationName, new HashMap<>());
        Map<String, int[]> endStationToDurationData = fromStationToStationDurations.get(startStationName);
        endStationToDurationData.compute(stationName, (k, v) -> {
            if (v == null) {
                v = new int[]{duration, 1};
            } else {
                v[0] = v[0] + duration;
                v[1] = v[1] + 1;
            }
            return v;
        });
    }

    public double getAverageTime(String startStation, String endStation) {
        Map<String, int[]> endStationToDurationData = fromStationToStationDurations.get(startStation);
        int[] data = endStationToDurationData.get(endStation);
        if (data == null) {
            return 0;
        }
        return ((double) data[0]) / data[1];
    }
}
