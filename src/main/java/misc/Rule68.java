package misc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Rule68 {
    List<LocalDate> generateDates(LocalDate start, LocalDate end) {
        LocalDate cur = start;
        List<LocalDate> out = new ArrayList<>();
        while (!cur.equals(end)) {
            cur = cur.plusDays(1);
            out.add(cur);
        }
        return out;
    }

    int rule(LocalDate ld) {
        int first = ld.getYear() / 100;
        int second = ld.getYear() % 100;
        int out = ld.getDayOfMonth() + ld.getMonthValue() + first + second;
        return out;
    }

    void range(int firstYEar, int lastYear){
        System.out.println("for years: "+firstYEar+" -- "+lastYear);
        LocalDate start = LocalDate.of(firstYEar, 1, 1);
        LocalDate end = LocalDate.of(lastYear, 12, 31);
        List<LocalDate> dates = generateDates(start, end);
        int totalDaysCount = dates.size();
        List<Integer> datesUpdatedByRule = dates.stream().map(this::rule).toList();
        Map<Integer, Long> freqs = datesUpdatedByRule.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        double probability = (double)freqs.get(68)/totalDaysCount;
        System.out.println("68 probability: "+probability);

        TreeMap<Long, List<Integer>> inversedTreeMap = freqs.entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, TreeMap::new,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
        List<Map.Entry<Long, List<Integer>>> list = inversedTreeMap.descendingMap().entrySet().stream().limit(10).toList();
        System.out.println("most frequent: "+list);

        IntSummaryStatistics intSummaryStatistics = datesUpdatedByRule.stream().mapToInt(i -> i).summaryStatistics();
        System.out.println(intSummaryStatistics);
        System.out.println("------------------");
    }
    @Test
    void test() {
//        range(0,3000);
        range(1939,1939);
        range(2000,2100);
/*

Nine Years' War. 27 September 1688 – 20 September 1697
16+88+9+27=140

Seven Years' War ; Date, 17 May 1756 – 15 February 1763
17+56+17+5=95

French Revolutionary and Napoleonic Wars
20 April 1792— 20 November 1815
17+92+20+4=133

French invasion of Russia
24 June – 14 December 1812
18+12+6+24=60

Cold War
12 March 1947 – 26 December 1991
19+47+3+12=81

WW1
28 июл. 1914.  28+7+19+14  = 68

WW2
1 September 1939 – 2 September 1945
19+39+9+1=68

War on terror
14 September 2001
20+01+9+14=44

Russia-Ukraine war
20 February 2014
20+14+2+20=56
24 February 2022
20+22+2+24=68




 */
    }
}
