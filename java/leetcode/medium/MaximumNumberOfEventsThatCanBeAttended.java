package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import static leetcode.medium.MaximumNumberOfEventsThatCanBeAttended.WithTreeMap.maxEvents;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
public class MaximumNumberOfEventsThatCanBeAttended {

    static class WithTreeMap {
        public static int maxEvents(int[][] events) {
            Arrays.sort(events, Comparator.comparingInt(a -> a[1]));
            TreeMap<Integer, Integer> occupiedRanges = new TreeMap<>();// start of range mapped to end
            for (int[] e : events) {
                int eventStart = e[0];
                int eventEnd = e[1];
                Map.Entry<Integer, Integer> range = occupiedRanges.floorEntry(eventStart); // range which starts before the event start
                if (range == null || eventStart > range.getValue() + 1) {  // create new range of occupied days
                    insertRange(occupiedRanges, eventStart, eventStart);
                } else if (eventEnd > range.getValue()) {  // expand existing range
                    insertRange(occupiedRanges, range.getKey(), range.getValue() + 1);
                }

            }
            int out = occupiedRanges.entrySet().stream().mapToInt(e -> e.getValue() - e.getKey() + 1).sum();
            return out;
        }

        static void insertRange(TreeMap<Integer, Integer> map, int start, int end) {
            Integer endOfNextRange = map.remove(end + 1);
            Integer endOfCurrentRange = endOfNextRange != null ? endOfNextRange : end;
            map.put(start, endOfCurrentRange);
        }

    }

    @Test
    public void test() {
        assertEquals(0, maxEvents(new int[][]{}));
        assertEquals(1, maxEvents(new int[][]{{1, 1}}));
        assertEquals(2, maxEvents(new int[][]{{1, 2}, {1, 1}}));
        assertEquals(3, maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        assertEquals(4, maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 2}}));
        assertEquals(1, maxEvents(new int[][]{{1, 100000}}));
        assertEquals(7, maxEvents(new int[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}}));
    }

    @Test
    public void test2() {
        assertEquals(5, maxEvents(new int[][]
                {{1, 2}, {1, 2}, {3, 3}, {1, 5}, {1, 5}}
        ));
    }

    @Test
    public void test3() {
        assertEquals(5, maxEvents(new int[][]
                {{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}}
        ));
    }

    @Test
    public void test4() {
        assertEquals(4, maxEvents(new int[][]
                {{3, 3}, {1, 3}, {2, 3}, {3, 4}, {3, 4}}
        ));
    }

    @Test
    public void test5() {
        assertEquals(5, maxEvents(new int[][]
                {{7, 11}, {7, 11}, {7, 11}, {9, 10}, {9, 11}}
        ));
    }

    @Test
    public void test6() {
        assertEquals(18, maxEvents(new int[][]
                {{27, 27}, {8, 10}, {9, 11}, {20, 21}, {25, 29},
                        {17, 20}, {12, 12}, {12, 12}, {10, 14}, {7, 7},
                        {6, 10}, {7, 7}, {4, 8}, {30, 31}, {23, 25},
                        {4, 6}, {17, 17}, {13, 14}, {6, 9}, {13, 14}}
        ));
    }

    @Test
    public void test7() {
        assertEquals(19, maxEvents(new int[][]
                {{25, 26}, {19, 19}, {9, 13}, {16, 17}, {17, 18},
                        {20, 21}, {22, 25}, {11, 12}, {19, 23}, {7, 9},
                        {1, 1}, {21, 23}, {14, 14}, {4, 7}, {16, 16},
                        {24, 28}, {16, 18}, {4, 5}, {18, 20}, {16, 16}, {25, 26}}
        ));
    }

    @Test
    public void test_mine() {
        assertEquals(2, maxEvents(new int[][]
                {{1, 1}, {1, 1}, {1, 1}, {1, 2}, {1, 2}}
        ));
        assertEquals(10, maxEvents(new int[][]
                {{1, 10}, {1, 9}, {2, 2}, {3, 3}, {4, 4}, {1, 5}, {6, 10}, {7, 7}, {8, 8}, {9, 9}}
        ));
    }

    @Test
    public void test_big() throws IOException {
        String s = Files.readString(Path.of("/Users/elendili/git-projects/algorithms/java/leetcode/medium/x.txt"));
        int[][] x = Arrays.stream(s.split("],"))
                .map(s1 -> s1.replaceAll("[^0-9,]", ""))
                .map(s1 -> Arrays.stream(s1.split(","))
                        .mapToInt(Integer::valueOf)
                        .toArray()
                ).toArray(int[][]::new);
        assertEquals(99888, maxEvents(x));
    }
}
