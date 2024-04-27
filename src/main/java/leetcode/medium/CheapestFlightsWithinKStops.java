package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
 */
public class CheapestFlightsWithinKStops {
    record FromToCost(int from, int to, int price) {
    }

    interface CheapestFlightsWithinKStopsInterface {
        int findCheapestPrice(int n, int[][] flights, int src, int dst, int k);
    }

    static class BFS implements CheapestFlightsWithinKStopsInterface {

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<FromToCost>> adj = new HashMap<>();
            for (int[] i : flights) {
                adj.computeIfAbsent(i[0], value -> new ArrayList<>()).add(new FromToCost(i[0], i[1], i[2]));
            }

            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);

            Queue<FromToCost> q = new LinkedList<>();
            q.offer(new FromToCost(src, src, 0));
            int stops = 0;

            while (stops <= k && !q.isEmpty()) {
                int sz = q.size();
                // Iterate on current level.
                while (sz-- > 0) {
                    FromToCost temp = q.poll();
                    int node = temp.to;
                    int distance = temp.price;

                    if (!adj.containsKey(node))
                        continue;
                    // Loop over neighbors of popped node.
                    for (FromToCost e : adj.get(node)) {
                        int neighbour = e.to;
                        int price = e.price;
                        if (price + distance >= dist[neighbour])
                            continue;
                        dist[neighbour] = price + distance;
                        q.offer(new FromToCost(node, neighbour, dist[neighbour]));
                    }
                }
                stops++;
            }
            return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
        }
    }

    static class BellmanFord implements CheapestFlightsWithinKStopsInterface {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // Distance from source to all other nodes.
            int[] flightCost = new int[n];
            Arrays.fill(flightCost, Integer.MAX_VALUE);
            flightCost[src] = 0;

            // Run only K+1 times since we want shortest distance in K hops
            for (int i = 0; i <= k; i++) {
                // Create a copy of dist vector.
                int[] flightCostTemp = Arrays.copyOf(flightCost, n);
                for (int[] flight : flights) {
                    int from = flight[0];
                    int to = flight[1];
                    int cost = flight[2];
                    int fromCost = flightCost[from];
                    if (fromCost < Integer.MAX_VALUE) { // get nodes in which actor is able to be considering previous flights
                        // update destination cost using costs of previous flights
                        flightCostTemp[to] = Math.min(flightCostTemp[to], fromCost + cost);
                    }
                }
                // Copy the temp vector into dist.
                flightCost = flightCostTemp;
            }
            return flightCost[dst] == Integer.MAX_VALUE ? -1 : flightCost[dst];
        }
    }
    static class Dijkstra implements CheapestFlightsWithinKStopsInterface {
            public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
                Map<Integer, List<int[]>> adj = new HashMap<>();
                for (int[] i : flights)
                    adj.computeIfAbsent(i[0], value -> new ArrayList<>()).add(new int[] { i[1], i[2] });

                int[] stops = new int[n];
                Arrays.fill(stops, Integer.MAX_VALUE);
                PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
                // {dist_from_src_node, node, number_of_stops_from_src_node}
                pq.offer(new int[] { 0, src, 0 });

                while (!pq.isEmpty()) {
                    int[] temp = pq.poll();
                    int dist = temp[0];
                    int node = temp[1];
                    int steps = temp[2];
                    // We have already encountered a path with a lower cost and fewer stops,
                    // or the number of stops exceeds the limit.
                    if (steps > stops[node] || steps > k + 1)
                        continue;
                    stops[node] = steps;
                    if (node == dst)
                        return dist;
                    if (!adj.containsKey(node))
                        continue;
                    for (int[] a : adj.get(node)) {
                        pq.offer(new int[] { dist + a[1], a[0], steps + 1 });
                    }
                }
                return -1;
            }
    }

    static Stream<CheapestFlightsWithinKStopsInterface> solvers() {
        return Stream.of(new BellmanFord(), new BFS(), new Dijkstra());
    }

    @MethodSource("solvers")
    @ParameterizedTest
    public void test(CheapestFlightsWithinKStopsInterface solver) {
//        Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
//        Output: 700
        assertEquals(700, solver.findCheapestPrice(4, new int[][]{
                {0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}
        }, 0, 3, 1));
//
//        Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
//        Output: 200
        assertEquals(200, solver.findCheapestPrice(3, new int[][]{
                {0, 1, 100}, {1, 2, 100}, {0, 2, 500}
        }, 0, 2, 1));
//      Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
//      Output: 500
        assertEquals(500, solver.findCheapestPrice(3, new int[][]{
                {0, 1, 100}, {1, 2, 100}, {0, 2, 500}
        }, 0, 2, 0));

    }
}
