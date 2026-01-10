package leetcode.hard;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/time-taken-to-cross-the-door/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency
 */
public class TimeTakenToCrossTheDoor_2534 {
    static final int EXIT = 1;

    public int[] timeTaken(int[] arrival, int[] state) {
        /*
        You are given a non-decreasing integer array arrival of size n,
        where arrival[integer] is the arrival time of the ith person at the door.
        You are also given an array state of size n,
        where state[integer] is 0 if person integer wants to enter through the door
        or 1 if they want to exit through the door.

        Return an array answer of size n
        where answer[integer] is the second at which the ith person crosses the door.

        // sort arrived to know who goes first
        get group of awaiting people who came at same time,
        process them in a loop, resolving second by second,
        Also, in a loop, every second, check if we need to extend group of awaiting people by newly arrived
        if yes, extend and process them

        add to out 
         */
        int n = arrival.length;
        int[] timeToPersonIndex = new int[n]; // integer is a time, a[integer] is a person using door at time integer
        int outIndex = 0;
        int indexOfFirstArriver = 0; // first arriver of groups that is under processing
        int indexOfNextArriver = 0; // from next documented moment which not under process
        int loopCurMoment = arrival[0];
        boolean[] usedDoor = new boolean[n];
        int prevMomentUsage = EXIT;

        while (indexOfFirstArriver < n) {
            // jump when big gap between moments and people arrived at 1st moment are already passed th door
            if (indexOfNextArriver < n && indexOfFirstArriver == indexOfNextArriver) {
                if (arrival[indexOfNextArriver] - loopCurMoment > 1) {
                    prevMomentUsage = EXIT;
                }
                loopCurMoment = arrival[indexOfNextArriver];
            }


            // define group of awaiters
            if (indexOfNextArriver < n && loopCurMoment == arrival[indexOfNextArriver]) {
                // extend group of arrivers
                while (indexOfNextArriver < n && arrival[indexOfNextArriver] == loopCurMoment) {
                    indexOfNextArriver++;
                }
            }
            // process group
            int groupSize = indexOfNextArriver - indexOfFirstArriver;
            int possibleUserIndex = indexOfFirstArriver;
            if (groupSize > 1) {
                // more than one want to use door
                // search person who wants to move same direction as person on prev moment
                for (int i = indexOfFirstArriver; i < indexOfNextArriver; i++) {
                    if (!usedDoor[i] && state[i] == prevMomentUsage) {
                        possibleUserIndex = i;
                        break;
                    }
                }
            }
            prevMomentUsage = state[possibleUserIndex];
            timeToPersonIndex[outIndex++] = possibleUserIndex;
            usedDoor[possibleUserIndex] = true;
            // move index of first arriver if he already used door
            while (indexOfFirstArriver < n && usedDoor[indexOfFirstArriver]) {
                indexOfFirstArriver++;
            }

            loopCurMoment++;

        }
        System.out.println(Arrays.toString(timeToPersonIndex));
//        int[] timeToPersonIndex = new int[n]; // integer is a time, a[integer] is a person using door at time integer
        int[] personIndexToTimeMoment = new int[n]; // integer is a person index, a[integer] is a time moment
        //        assertEquals("[0, 2, 3, 1, 4]",
        //        assertEquals("[0, 3, 1, 2, 4]",     - expected
        // at moment 3 person 2 entered
        for (int i = 0; i < n; i++) {
            int person = timeToPersonIndex[i];
            personIndexToTimeMoment[person] = i;
        }
        return personIndexToTimeMoment;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("[0, 3, 1, 2, 4]",
                Arrays.toString(timeTaken(new int[]{0, 1, 1, 2, 4}, new int[]{0, 1, 0, 0, 1})));
    }
    @org.junit.jupiter.api.Test
    public void test_from_leetcode() {
        assertEquals("[3, 6, 4, 7, 5, 8]",  // value is time, index is person
                Arrays.toString(timeTaken(new int[]{3,3,4,5,5,5}, new int[]{1,0,1,0,1,0})));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals("[0, 2, 1]",
                Arrays.toString(timeTaken(new int[]{0, 0, 0}, new int[]{1, 0, 1})));
    }

    @org.junit.jupiter.api.Test
    public void test_base() {
        assertEquals("[1, 0]",
                Arrays.toString(timeTaken(new int[]{0, 0}, new int[]{0, 1})));
        assertEquals("[0, 1]",
                Arrays.toString(timeTaken(new int[]{0, 0}, new int[]{0, 0})));
        assertEquals("[0, 1]",
                Arrays.toString(timeTaken(new int[]{0, 0}, new int[]{1, 1})));
        assertEquals("[0, 1]",
                Arrays.toString(timeTaken(new int[]{0, 0}, new int[]{1, 0})));
    }

    @org.junit.jupiter.api.Test
    public void test_base2() {
        assertEquals("[2, 0, 3, 1]",
                Arrays.toString(timeTaken(new int[]{0, 0, 1, 1}, new int[]{0, 1, 0, 1})));
        assertEquals("[0, 2, 1, 3]",
                Arrays.toString(timeTaken(new int[]{0, 0, 1, 1}, new int[]{1, 0, 1, 0})));
        assertEquals("[3, 0, 4, 1, 5, 2]",
                Arrays.toString(timeTaken(new int[]{0, 0, 1, 1, 2, 2}, new int[]{0, 1, 0, 1, 0, 1})));
    }

    @org.junit.jupiter.api.Test
    public void test_base3() {
        assertEquals("[0, 2, 1]",
                Arrays.toString(timeTaken(new int[]{0, 10, 10}, new int[]{0, 0, 1})));
        assertEquals("[3, 0, 4, 1, 5, 2, 7, 6]",
                Arrays.toString(timeTaken(new int[]{0, 0, 0, 1, 1, 1, 10, 10}, new int[]{0, 1, 0, 1, 0, 1, 0, 1})));
    }
}
