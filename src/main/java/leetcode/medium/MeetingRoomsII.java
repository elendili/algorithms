package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 */
public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        if(intervals==null || intervals.length==0){
            return 0;
        }
        // sort meeting by start time, so we can iterate
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // pq with top is a room with interval ending the earliest possible
        // size of the pq is amount of occupied rooms
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // from meetings started earlier to started later
        // room is considered as interval in PQ
        pq.add(intervals[0]);
        for (int i = 1; i < intervals.length && !pq.isEmpty(); i++) {
            int[] finishedEarliest = pq.poll();  // get the meeting finished earliest
            int[] cur = intervals[i];
            if (cur[0] >= finishedEarliest[1]) { // end of prev > start of current
                // merge interval, because current ends after or exactly at finishedEarliest end
                finishedEarliest[1] = cur[1];
            } else {
                // allocate new room for occupation
                pq.add(cur);
            }
            // return room/interval back to queue, so we can choose the earliest available
            pq.add(finishedEarliest);
        }
        return pq.size();
    }

    @Test
    public void test() {
        Assertions.assertEquals(2,
                minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        Assertions.assertEquals(1,
                minMeetingRooms(new int[][]{{7, 10}, {2, 4}}));
        Assertions.assertEquals(0,
                minMeetingRooms(new int[0][]));
    }
}
