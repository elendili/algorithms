package am_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/*
 * I fucked in task description, because said task was solved for O(nlogn)
 * so I have no chance to go in Am after this.
 * */
public class SecondTaskReal {
    private List<List<Integer>> genBigList() {
        Random random = new Random();
        return IntStream.range(0, 10000).mapToObj(i -> asList(i, random.nextInt(1000))).collect(toList());
    }

    List<List<Integer>> ou(int maxTravelDist,
                           List<List<Integer>> forwardRouteList,
                           List<List<Integer>> returnRouteList) {
        assert maxTravelDist > 0 : "should be bigger than 0";
        assert !forwardRouteList.isEmpty() : "should not be empty";
        assert !returnRouteList.isEmpty() : "should not be empty";
        TreeMap<Integer, List<List<Integer>>> distanceToPairs = new TreeMap<>();
        forwardRouteList.stream()
                .filter(l -> l.get(1) <= maxTravelDist)
                .forEach(l1 -> {
                    returnRouteList.stream()
                            .filter(l -> l.get(1) <= maxTravelDist)
                            .forEach(l2 -> {
                                int sum = l1.get(1) + l2.get(1);
                                if (sum <= maxTravelDist) {
                                    distanceToPairs.compute(sum, (k, oldValue) -> {
                                        List<Integer> toAdd = asList(l1.get(0), l2.get(0));
                                        if (oldValue == null) {
                                            oldValue = new ArrayList<>();
                                        }
                                        oldValue.add(toAdd);
                                        return oldValue;
                                    });
                                }
                            });
                });
        return Optional.ofNullable(distanceToPairs.lastEntry())
                .map(Map.Entry::getValue).orElse(new ArrayList<>());
    }


    private List<List<Integer>> convertSetsToList(Set<Integer> s1, Set<Integer> s2) {
        List<List<Integer>> out = new ArrayList<>();
        s1.forEach(e1 -> s2.forEach(e2 -> out.add(asList(e1, e2))));
        return out;
    }

    List<List<Integer>> ou2(int maxTravelDist,
                            List<List<Integer>> forwardRouteList,
                            List<List<Integer>> returnRouteList) {
        assert maxTravelDist > 0 : "should be bigger than 0";
        assert !forwardRouteList.isEmpty() : "should not be empty";
        assert !returnRouteList.isEmpty() : "should not be empty";
        final ConcurrentSkipListSet<Integer> fDSet = new ConcurrentSkipListSet<>();
        final Map<Integer, Set<Integer>> fDistanceToIdsMap = forwardRouteList.stream()
                .filter(l -> {
                    int d = l.get(1);
                    return d < maxTravelDist && d > 0;
                })
                .collect(toMap(l -> {
                            Integer distance = l.get(1);
                            fDSet.add(distance);
                            return distance;
                        },
                        l -> new HashSet<>(singletonList(l.get(0))),
                        (v1, v2) -> {
                            v1.add(v2.iterator().next());
                            return v1;
                        }));

        final ConcurrentSkipListSet<Integer> rDSet = new ConcurrentSkipListSet<>();
        final Map<Integer, Set<Integer>> rDistanceToIdsMap = returnRouteList.stream()
                .filter(l -> {
                    int d = l.get(1);
                    return d < maxTravelDist && d > 0;
                })
                .collect(toMap(l -> {
                            Integer distance = l.get(1);
                            rDSet.add(distance);
                            return distance;
                        },
                        l -> new HashSet<>(singletonList(l.get(0))),
                        (v1, v2) -> {
                            v1.add(v2.iterator().next());
                            return v1;
                        }));

        final List<List<Integer>> out = new ArrayList<>();
        if (fDSet.first() + rDSet.first() > maxTravelDist) {
            return out;
        }

        int bestDistance = -1;
        Set<Integer> bestForwards = new HashSet<>();
        while (!fDSet.isEmpty() && !rDSet.isEmpty()) {
            Integer small1 = fDSet.pollFirst();
            Iterator<Integer> d = rDSet.descendingIterator();
            while (d.hasNext()) {
                Integer i = d.next();
                Integer localSum = i + small1;
                if (localSum > maxTravelDist) {
                    rDSet.remove(i);
                } else if (localSum > bestDistance) {
                    bestDistance = localSum;
                    bestForwards = new HashSet<>(singletonList(small1));
                } else if (localSum == bestDistance) {
                    bestForwards.add(small1);
                }
            }
        }
        int finalBestDistance = bestDistance;
        bestForwards.forEach(f -> {
            Set<Integer> fKeys = fDistanceToIdsMap.get(f);
            Set<Integer> rKeys = rDistanceToIdsMap.get(finalBestDistance - f);
            out.addAll(convertSetsToList(fKeys, rKeys));
        });

        return out;
    }


    @Test
    public void test() {
        List<List<Integer>> f = asList(asList(1, 3), asList(2, 2), asList(5, 1), asList(6, 8), asList(7, 9), asList(8, 4), asList(9, 5), asList(3, 7), asList(4, 10), asList(10, 6));
        List<List<Integer>> r = asList(asList(1, 2), asList(2, 8), asList(7, 3), asList(5, 1), asList(4, 5), asList(6, 5), asList(3, 4), asList(8, 7), asList(9, 40), asList(10, 2));
        Assertions.assertEquals("[[1, 3], [10, 5], [2, 4], [2, 6], [8, 7], [9, 10], [9, 1]]",
                ou(7, f, r).stream().sorted(comparing(Object::toString)).collect(toList()).toString());

        f = asList(asList(0, 498), asList(1, 323), asList(2, 966), asList(3, 636), asList(4, 805), asList(5, 623), asList(6, 563), asList(7, 633), asList(8, 115), asList(9, 354));
        r = asList(asList(0, 972), asList(1, 128), asList(2, 179), asList(3, 797), asList(4, 892), asList(5, 885), asList(6, 110), asList(7, 644), asList(8, 617), asList(9, 167));
        Assertions.assertEquals("[[6, 1]]", ou(700, f, r).toString());

        Assertions.assertEquals("[[1, 1], [1, 3]]",
                ou(6, asList(asList(1, 2), asList(2, 7), asList(3, 1)),
                        asList(asList(1, 3), asList(2, 8), asList(3, 3)))
                        .toString());

        Assertions.assertEquals("[]",
                ou(6, asList(asList(1, 4), asList(2, 7)),
                        asList(asList(1, 3), asList(2, 8)))
                        .toString());
    }

    @Test
    public void test2() {
        List<List<Integer>> f = asList(asList(1, 3), asList(2, 2), asList(5, 1), asList(6, 8), asList(7, 9), asList(8, 4), asList(9, 5), asList(3, 7), asList(4, 10), asList(10, 6));
        List<List<Integer>> r = asList(asList(1, 2), asList(2, 8), asList(7, 3), asList(5, 1), asList(4, 5), asList(6, 5), asList(3, 4), asList(8, 7), asList(9, 40), asList(10, 2));
        Assertions.assertEquals("[[1, 3], [10, 5], [2, 4], [2, 6], [8, 7], [9, 10], [9, 1]]",
                ou2(7, f, r).stream().sorted(comparing(Object::toString)).collect(toList()).toString());

        f = asList(asList(0, 498), asList(1, 323), asList(2, 966), asList(3, 636), asList(4, 805), asList(5, 623), asList(6, 563), asList(7, 633), asList(8, 115), asList(9, 354));
        r = asList(asList(0, 972), asList(1, 128), asList(2, 179), asList(3, 797), asList(4, 892), asList(5, 885), asList(6, 110), asList(7, 644), asList(8, 617), asList(9, 167));
        Assertions.assertEquals("[[6, 1]]", ou2(700, f, r).toString());

        Assertions.assertEquals("[[1, 1], [1, 3]]",
                ou2(6, asList(asList(1, 2), asList(2, 7), asList(3, 1)),
                        asList(asList(1, 3), asList(2, 8), asList(3, 3)))
                        .toString());

        Assertions.assertEquals("[]",
                ou2(6, asList(asList(1, 4), asList(2, 7)),
                        asList(asList(1, 3), asList(2, 8)))
                        .toString());
    }

    @Benchmark
    public void bench() {
        ou(700, genBigList(), genBigList());
    }

    @Benchmark
    public void bench2() {
        ou2(700, genBigList(), genBigList());
    }

    @Test
    public void benchmark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getSimpleName())
                .warmupIterations(10)
                .warmupTime(TimeValue.milliseconds(10))
                .measurementIterations(20)
                .measurementTime(TimeValue.milliseconds(50))
                .mode(Mode.Throughput)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
