package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 */
public class ReconstructItinerary {

    class Solver {
        private final Map<String, PriorityQueue<String>> fromToTicketsMap = new HashMap<>();
        private final String start = "JFK";

        Solver(List<List<String>> tickets) {
            for (int i = 0; i < tickets.size(); i++) {
                List<String> l = tickets.get(i);
                fromToTicketsMap
                        .computeIfAbsent(l.get(0), v -> new PriorityQueue<>())
                        .add(l.get(1));
            }
        }

        private LinkedList<String> itinerary = new LinkedList<>();

        List<String> solve() {
            recurse(start);
            return itinerary;
        }

        void recurse(String origin) {
            if (fromToTicketsMap.containsKey(origin)) {
                PriorityQueue<String> toCities = fromToTicketsMap.get(origin);
                while (!toCities.isEmpty()) {
                    String dest = toCities.poll();
                    recurse(dest);
                }
            }
            itinerary.addFirst(origin);
        }
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        return new Solver(tickets).solve();
    }


    @Test
    public void test() {
        List<List<String>> input = asList(asList("MUC", "LHR"), asList("JFK", "MUC"), asList("SFO", "SJC"), asList("LHR", "SFO"));
        Assertions.assertEquals(asList("JFK", "MUC", "LHR", "SFO", "SJC"), findItinerary(input));
    }

    @Test
    public void test2() {
        List<List<String>> input = asList(asList("JFK", "SFO"), asList("JFK", "ATL"), asList("SFO", "ATL"), asList("ATL", "JFK"), asList("ATL", "SFO"));

        Assertions.assertEquals(asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"), findItinerary(input));
    }

    @Test
    public void test3() {
        List<List<String>> input = asList(asList("JFK", "KUL"), asList("JFK", "NRT"), asList("NRT", "JFK"));
        Assertions.assertEquals(asList("JFK", "NRT", "JFK", "KUL"), findItinerary(input));
    }

    @Test
    public void test4() {
        List<List<String>> input =
                asList(asList("AXA", "EZE"), asList("EZE", "AUA"), asList("ADL", "JFK"), asList("ADL", "TIA"), asList("AUA", "AXA"), asList("EZE", "TIA"), asList("EZE", "TIA"), asList("AXA", "EZE"), asList("EZE", "ADL"), asList("ANU", "EZE"), asList("TIA", "EZE"), asList("JFK", "ADL"), asList("AUA", "JFK"), asList("JFK", "EZE"), asList("EZE", "ANU"), asList("ADL", "AUA"), asList("ANU", "AXA"), asList("AXA", "ADL"), asList("AUA", "JFK"), asList("EZE", "ADL"), asList("ANU", "TIA"), asList("AUA", "JFK"), asList("TIA", "JFK"), asList("EZE", "AUA"), asList("AXA", "EZE"), asList("AUA", "ANU"), asList("ADL", "AXA"), asList("EZE", "ADL"), asList("AUA", "ANU"), asList("AXA", "EZE"), asList("TIA", "AUA"), asList("AXA", "EZE"), asList("AUA", "SYD"), asList("ADL", "JFK"), asList("EZE", "AUA"), asList("ADL", "ANU"), asList("AUA", "TIA"), asList("ADL", "EZE"), asList("TIA", "JFK"), asList("AXA", "ANU"), asList("JFK", "AXA"), asList("JFK", "ADL"), asList("ADL", "EZE"), asList("AXA", "TIA"), asList("JFK", "AUA"), asList("ADL", "EZE"), asList("JFK", "ADL"), asList("ADL", "AXA"), asList("TIA", "AUA"), asList("AXA", "JFK"), asList("ADL", "AUA"), asList("TIA", "JFK"), asList("JFK", "ADL"), asList("JFK", "ADL"), asList("ANU", "AXA"), asList("TIA", "AXA"), asList("EZE", "JFK"), asList("EZE", "AXA"), asList("ADL", "TIA"), asList("JFK", "AUA"), asList("TIA", "EZE"), asList("EZE", "ADL"), asList("JFK", "ANU"), asList("TIA", "AUA"), asList("EZE", "ADL"), asList("ADL", "JFK"), asList("ANU", "AXA"), asList("AUA", "AXA"), asList("ANU", "EZE"), asList("ADL", "AXA"), asList("ANU", "AXA"), asList("TIA", "ADL"), asList("JFK", "ADL"), asList("JFK", "TIA"), asList("AUA", "ADL"), asList("AUA", "TIA"), asList("TIA", "JFK"), asList("EZE", "JFK"), asList("AUA", "ADL"), asList("ADL", "AUA"), asList("EZE", "ANU"), asList("ADL", "ANU"), asList("AUA", "AXA"), asList("AXA", "TIA"), asList("AXA", "TIA"), asList("ADL", "AXA"), asList("EZE", "AXA"), asList("AXA", "JFK"), asList("JFK", "AUA"), asList("ANU", "ADL"), asList("AXA", "TIA"), asList("ANU", "AUA"), asList("JFK", "EZE"), asList("AXA", "ADL"), asList("TIA", "EZE"), asList("JFK", "AXA"), asList("AXA", "ADL"), asList("EZE", "AUA"), asList("AXA", "ANU"), asList("ADL", "EZE"), asList("AUA", "EZE"));
        Assertions.assertEquals(
                "[JFK, ADL, ANU, ADL, ANU, AUA, ADL, AUA, ADL, AUA, ANU, AXA, ADL, AUA, ANU, AXA, ADL, AXA, ADL, AXA, ANU, AXA, ANU, AXA, EZE, ADL, AXA, EZE, ADL, AXA, EZE, ADL, EZE, ADL, EZE, ADL, EZE, ANU, EZE, ANU, EZE, AUA, AXA, EZE, AUA, AXA, EZE, AUA, AXA, JFK, ADL, EZE, AUA, EZE, AXA, JFK, ADL, JFK, ADL, JFK, ADL, JFK, ADL, TIA, ADL, TIA, AUA, JFK, ANU, TIA, AUA, JFK, AUA, JFK, AUA, TIA, AUA, TIA, AXA, TIA, EZE, AXA, TIA, EZE, JFK, AXA, TIA, EZE, JFK, AXA, TIA, JFK, EZE, TIA, JFK, EZE, TIA, JFK, TIA, JFK, AUA, SYD]", findItinerary(input).toString());
    }

    @Test
    public void test5() {
        List<List<String>> input =
                asList(asList("AXA", "AUA"), asList("BNE", "ANU"), asList("EZE", "ANU"), asList("TIA", "JFK"), asList("TIA", "BNE"), asList("ANU", "BNE"), asList("BNE", "AUA"), asList("BNE", "ADL"), asList("AXA", "ADL"), asList("EZE", "AUA"), asList("AUA", "AXA"), asList("ADL", "AXA"), asList("ADL", "TIA"), asList("JFK", "ANU"), asList("EZE", "JFK"), asList("JFK", "AUA"), asList("BNE", "EZE"), asList("TIA", "ANU"), asList("TIA", "AUA"), asList("JFK", "TIA"), asList("EZE", "ANU"), asList("AXA", "JFK"), asList("AUA", "OOL"), asList("AUA", "AXA"), asList("ANU", "BNE"), asList("ANU", "EZE"), asList("ANU", "TIA"), asList("JFK", "EZE"), asList("ADL", "ANU"), asList("AXA", "BNE"), asList("BNE", "ADL"), asList("ANU", "EZE"), asList("ANU", "JFK"), asList("BNE", "AUA"), asList("ANU", "AUA"), asList("ANU", "AXA"), asList("TIA", "BNE"), asList("AUA", "EZE"), asList("JFK", "ANU"), asList("AXA", "TIA"), asList("EZE", "ANU"), asList("AUA", "BNE"), asList("AUA", "AXA"), asList("AUA", "TIA"));
        Assertions.assertEquals(
                "[JFK, ANU, AUA, AXA, ADL, ANU, AXA, AUA, AXA, BNE, ADL, AXA, JFK, ANU, BNE, ADL, TIA, ANU, BNE, ANU, EZE, ANU, EZE, ANU, JFK, AUA, AXA, TIA, AUA, BNE, AUA, EZE, ANU, TIA, BNE, AUA, TIA, BNE, EZE, JFK, TIA, JFK, EZE, AUA, OOL]", findItinerary(input).toString());
    }


    @Test
    public void test6() {
        List<List<String>> input =
                asList(asList("AUA", "EZE"), asList("JFK", "AUA"), asList("EZE", "ANU"), asList("AXA", "JFK"), asList("AUA", "JFK"), asList("ADL", "TIA"), asList("AUA", "AXA"), asList("ADL", "AUA"), asList("AUA", "AXA"), asList("AXA", "TIA"), asList("EZE", "AXA"), asList("AXA", "AUA"), asList("ANU", "JFK"), asList("ADL", "EZE"), asList("ADL", "ANU"), asList("EZE", "ADL"), asList("AXA", "AUA"), asList("JFK", "EZE"), asList("AUA", "JFK"), asList("AUA", "JFK"), asList("AXA", "JFK"), asList("ADL", "EZE"), asList("AUA", "EZE"), asList("AUA", "JFK"), asList("AUA", "JFK"), asList("JFK", "AXA"), asList("TIA", "AXA"), asList("JFK", "ADL"), asList("ADL", "AXA"), asList("AXA", "AUA"), asList("AUA", "AXA"), asList("JFK", "AXA"), asList("TIA", "JFK"), asList("ANU", "AXA"), asList("JFK", "AXA"), asList("EZE", "AXA"), asList("AXA", "EZE"), asList("JFK", "ADL"), asList("EZE", "AXA"), asList("ANU", "AXA"), asList("AXA", "JFK"), asList("JFK", "AUA"), asList("AXA", "EZE"), asList("ADL", "AXA"), asList("AUA", "ADL"), asList("AUA", "ADL"), asList("AXA", "AUA"), asList("ADL", "ANU"), asList("ANU", "AUA"), asList("ADL", "ANU"), asList("JFK", "ADL"), asList("TIA", "AUA"), asList("EZE", "AXA"), asList("ANU", "TIA"), asList("AXA", "ADL"), asList("JFK", "ANU"), asList("ADL", "ANU"), asList("TIA", "AUA"), asList("ADL", "ANU"), asList("AUA", "EZE"), asList("JFK", "ANU"), asList("AUA", "ANU"), asList("ADL", "AUA"), asList("JFK", "ADL"), asList("TIA", "JFK"), asList("AXA", "ANU"), asList("AXA", "ANU"), asList("JFK", "AXA"), asList("AXA", "JFK"), asList("ANU", "AXA"), asList("ADL", "EZE"), asList("JFK", "ANU"), asList("JFK", "AUA"), asList("AXA", "ANU"), asList("JFK", "TIA"), asList("ANU", "AXA"), asList("JFK", "AUA"), asList("AXA", "AUA"), asList("ADL", "EZE"), asList("ADL", "AXA"), asList("JFK", "TIA"), asList("EZE", "AUA"), asList("AUA", "ADL"), asList("JFK", "ADL"), asList("EZE", "TIA"), asList("TIA", "AXA"), asList("AUA", "ADL"), asList("JFK", "AUA"), asList("JFK", "AUA"), asList("AUA", "ANU"), asList("JFK", "ADL"), asList("AXA", "AUA"), asList("EZE", "JFK"), asList("ANU", "ADL"), asList("ADL", "EZE"), asList("ANU", "JFK"), asList("EZE", "ANU"), asList("AUA", "ANU"), asList("JFK", "EZE"), asList("EZE", "JFK"), asList("AXA", "AUA"), asList("ANU", "AUA"), asList("TIA", "JFK"), asList("ANU", "AXA"), asList("TIA", "ANU"), asList("AUA", "ADL"), asList("ANU", "AXA"), asList("AXA", "ADL"), asList("AUA", "AXA"), asList("ADL", "ANU"), asList("EZE", "AXA"), asList("EZE", "ANU"), asList("AXA", "EZE"), asList("AUA", "ADL"), asList("AUA", "ADL"), asList("TIA", "JFK"), asList("JFK", "AUA"), asList("ADL", "AUA"), asList("AXA", "TIA"), asList("AXA", "TIA"), asList("AXA", "ANU"), asList("AUA", "ADL"), asList("AUA", "AXA"), asList("ADL", "AUA"), asList("ANU", "TIA"), asList("AUA", "AXA"), asList("ANU", "TIA"), asList("AXA", "AUA"), asList("EZE", "JFK"), asList("ADL", "AUA"), asList("ADL", "EZE"), asList("AXA", "JFK"), asList("JFK", "TIA"), asList("AUA", "AXA"), asList("AUA", "AXA"), asList("JFK", "TIA"), asList("EZE", "ADL"), asList("AUA", "ADL"), asList("ANU", "AXA"), asList("EZE", "ADL"), asList("ADL", "ANU"), asList("TIA", "EZE"), asList("ANU", "AUA"), asList("AUA", "HBA"), asList("JFK", "AUA"), asList("TIA", "AUA"), asList("TIA", "ADL"), asList("AXA", "ADL"), asList("EZE", "AUA"), asList("EZE", "JFK"), asList("ANU", "TIA"), asList("ANU", "JFK"), asList("EZE", "AUA"), asList("EZE", "AUA"), asList("ADL", "JFK"), asList("ADL", "AUA"), asList("ADL", "AXA"), asList("AXA", "JFK"), asList("AXA", "AUA"), asList("ANU", "AUA"), asList("ANU", "JFK"), asList("TIA", "EZE"), asList("ANU", "AUA"), asList("ADL", "JFK"), asList("JFK", "ANU"), asList("ADL", "JFK"), asList("ANU", "AUA"), asList("JFK", "ADL"), asList("AXA", "ANU"), asList("AXA", "JFK"), asList("JFK", "ADL"), asList("EZE", "JFK"), asList("TIA", "AUA"), asList("ANU", "ADL"), asList("AUA", "AXA"), asList("ADL", "AXA"), asList("AUA", "JFK"), asList("ADL", "AXA"), asList("EZE", "ANU"), asList("TIA", "EZE"), asList("EZE", "JFK"), asList("ADL", "EZE"), asList("ANU", "JFK"), asList("AUA", "JFK"), asList("ANU", "JFK"), asList("EZE", "ADL"), asList("AUA", "ADL"), asList("EZE", "ANU"), asList("AXA", "EZE"), asList("AXA", "ADL"), asList("JFK", "TIA"), asList("AUA", "ADL"), asList("EZE", "TIA"), asList("AXA", "EZE"), asList("EZE", "AUA"), asList("JFK", "TIA"), asList("AUA", "AXA"), asList("TIA", "ANU"), asList("AXA", "JFK"), asList("TIA", "EZE"), asList("EZE", "ANU"), asList("ANU", "EZE"), asList("JFK", "TIA"), asList("JFK", "EZE"), asList("TIA", "AXA"), asList("ANU", "EZE"), asList("AUA", "TIA"), asList("AXA", "EZE"), asList("ANU", "AUA"), asList("AUA", "EZE"), asList("AUA", "EZE"), asList("ADL", "ANU"), asList("AUA", "AXA"), asList("AXA", "ADL"), asList("JFK", "EZE"));

        Assertions.assertEquals(
                "[JFK, ADL, ANU, ADL, ANU, ADL, ANU, AUA, ADL, ANU, AUA, ADL, ANU, AUA, ADL, ANU, AUA, ADL, ANU, AUA, ADL, ANU, AUA, ADL, AUA, ADL, AUA, ADL, AUA, ADL, AUA, ADL, AUA, ADL, AUA, ANU, AUA, ANU, AXA, ADL, AXA, ADL, AXA, ADL, AXA, ADL, AXA, ADL, AXA, ANU, AXA, ANU, AXA, ANU, AXA, ANU, AXA, ANU, AXA, AUA, ANU, AXA, AUA, AXA, AUA, AXA, AUA, AXA, AUA, AXA, AUA, AXA, AUA, AXA, AUA, AXA, AUA, AXA, EZE, ADL, AXA, EZE, ADL, EZE, ADL, EZE, ADL, EZE, ANU, EZE, ANU, EZE, ANU, JFK, ADL, EZE, ANU, JFK, ADL, EZE, ANU, JFK, ADL, EZE, ANU, JFK, ADL, EZE, AUA, AXA, EZE, AUA, AXA, EZE, AUA, AXA, EZE, AUA, EZE, AUA, EZE, AXA, EZE, AXA, JFK, ADL, JFK, ADL, JFK, ADL, JFK, ANU, JFK, ANU, JFK, ANU, TIA, ADL, TIA, ANU, TIA, ANU, TIA, AUA, EZE, AXA, JFK, ANU, TIA, AUA, EZE, AXA, JFK, AUA, EZE, AXA, JFK, AUA, JFK, AUA, JFK, AUA, JFK, AUA, JFK, AUA, JFK, AUA, JFK, AUA, JFK, AXA, JFK, AXA, JFK, AXA, JFK, AXA, JFK, EZE, JFK, EZE, JFK, EZE, JFK, EZE, JFK, TIA, AUA, TIA, AXA, TIA, AXA, TIA, AXA, TIA, EZE, JFK, TIA, EZE, JFK, TIA, EZE, TIA, EZE, TIA, JFK, TIA, JFK, TIA, JFK, TIA, JFK, TIA, AUA, HBA]", findItinerary(input).toString());
    }


    @Test
    public void test7() {
        List<List<String>> input = asList(asList("JFK", "SFO"), asList("JFK", "ATL"), asList("SFO", "JFK"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"), asList("ATL", "AAA"), asList("AAA", "BBB"), asList("BBB", "ATL"));
        Assertions.assertEquals(
                "[JFK, SFO, JFK, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL, AAA, BBB, ATL]", findItinerary(input).toString());
    }


}
