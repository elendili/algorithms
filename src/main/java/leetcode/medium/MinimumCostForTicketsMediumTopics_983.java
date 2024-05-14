package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-cost-for-tickets/editorial/
 */
public class MinimumCostForTicketsMediumTopics_983 {
    interface MinimumCostForTicketsMediumTopics {
        int mincostTickets(int[] days, int[] costs);
    }

    static class MinimumCostForTicketsMediumTopicsImpl implements MinimumCostForTicketsMediumTopics {
        @Override
        public int mincostTickets(int[] days, int[] costs) {
            int n = days.length;
            int lastDay = days[n - 1];
            int[] memo = new int[lastDay + 1]; // array of min costs for days from 1 to the latest
            int i = 0;
            for (int day = 1; day <= lastDay; day++) {
                if (day < days[i]) {
                    memo[day] = memo[day - 1];
                } else {
                    i++;
                    int ifBought1DayPass = costs[0] + memo[day - 1]; // cost if yesterday we bought 1-day pass
                    int beforeDayIndex = day < 7 ? 0 : day - 7; // day when bought 7-day pass
                    int ifBought7DayPass = costs[1] + memo[beforeDayIndex]; // cost if 7 days ago we bought 7-day pass
                    beforeDayIndex = day < 30 ? 0 : day - 30; // day when bought 30-day pass
                    int ifBought30DayPass = costs[2] + memo[beforeDayIndex]; // cost if 30 days ago we bought 30-day pass
                    memo[day] = Math.min(ifBought1DayPass, Math.min(ifBought7DayPass, ifBought30DayPass));
                }
            }
            return memo[lastDay];
        }

    }

    public static Stream<MinimumCostForTicketsMediumTopics> implementationsSource() {
        return Stream.of(new MinimumCostForTicketsMediumTopicsImpl());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(MinimumCostForTicketsMediumTopics impl) {
        assertEquals(2, impl.mincostTickets(
                new int[]{1},
                new int[]{2, 7, 15}
        ));
        assertEquals(15, impl.mincostTickets(
                new int[]{1, 7, 8, 9, 10, 20, 25, 26},
                new int[]{2, 7, 15}
        ));
        assertEquals(11, impl.mincostTickets(
                new int[]{1, 4, 6, 7, 8, 20},
                new int[]{2, 7, 15}
        ));
        assertEquals(17, impl.mincostTickets(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31},
                new int[]{2, 7, 15}
        ));
        assertEquals(2, impl.mincostTickets(
                new int[]{1, 365},
                new int[]{1, 7, 15}
        ));
    }
}
