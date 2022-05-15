package yandex.Feb52021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/*

// гостиница
// день заезда и отъезда известно
// список гостей
// А съехал и B  выехал => 1
//вернуть максимальное число гостей в отеле за один день
// days count > 10^9

 */
public class MaxGuestsCountInHotel {
    // how I solved it in the interview
    int maxGuestsCountInHotel(int[][] checkInAndOut) {
        Set<Integer> uniqDays = new TreeSet<>();
        Map<Integer, Integer> checkinMap = new HashMap<>();// check in count map. day->count
        Map<Integer, Integer> checkoutMap = new HashMap<>();// check in count map. day->count

        for (int i = 0; i < checkInAndOut.length; i++) {
            int in = checkInAndOut[i][0];
            int out = checkInAndOut[i][1];
            uniqDays.add(in);
            uniqDays.add(out);

            checkinMap.compute(in, (k, v) -> v == null ? 1 : v + 1);
            checkoutMap.compute(out, (k, v) -> v == null ? 1 : v + 1);
        }
        int max = 0;
        int acc = 0;
        for (Integer day : uniqDays) {
            acc += checkinMap.getOrDefault(day, 0) - checkoutMap.getOrDefault(day, 0);
            max = Math.max(acc, max);
        }
        return max;
    }

    // how I solved it at the evening
    int maxGuestsCountInHotel_byAdvice(int[][] checkInAndOut) {
        // save accumulated checkins and checkouts for the day
        TreeMap<Integer, Integer> ts = new TreeMap<>();
        for (int[] guest : checkInAndOut) {
            ts.compute(guest[0], (k, v) -> v == null ? 1 : v + 1);
            ts.compute(guest[1], (k, v) -> v == null ? -1 : v - 1);
        }
        int max = 0;
        int acc = 0;
        for (int dayDelta : ts.values()) {
            acc += dayDelta;
            max = Math.max(max, acc);
        }
        return max;
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, maxGuestsCountInHotel(new int[][]{
                {1, 5}, {3, 4}}));
        Assertions.assertEquals(1, maxGuestsCountInHotel(new int[][]{
                {1, 2}, {2, 4}}));
        Assertions.assertEquals(3, maxGuestsCountInHotel(new int[][]{
                {1, 3}, {2, 4}, {3, 10}, {1, 2}, {1, 2}}));
        Assertions.assertEquals(0, maxGuestsCountInHotel(new int[][]{}));
    }

    @Test
    public void test2() {
//        Assertions.assertEquals(2, maxGuestsCountInHotel_byAdvice(new int[][]{
//                {1, 5}, {3, 4}}));
//        Assertions.assertEquals(1, maxGuestsCountInHotel_byAdvice(new int[][]{
//                {1, 2}, {2, 4}}));
        Assertions.assertEquals(3, maxGuestsCountInHotel_byAdvice(new int[][]{
                {1, 3}, {2, 4}, {3, 10}, {1, 2}, {1, 2}}));
        Assertions.assertEquals(0, maxGuestsCountInHotel_byAdvice(new int[][]{}));
    }
}
