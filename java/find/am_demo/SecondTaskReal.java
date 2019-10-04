package find.am_demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/*
* I fucked in task description, because said task was solved for O(nlogn)
* so I have no chance to go in Amazon after this.
* */
public class SecondTaskReal {

    List<List<Integer>> ou(int maxTravelDist,
                           List<List<Integer>> forwardRouteList,
                           List<List<Integer>> returnRouteList) {
        assert maxTravelDist > 0 : "maxTravelDist should be bigger than 0";
        assert !forwardRouteList.isEmpty() : "forwardRouteList should mot be empty";
        assert !returnRouteList.isEmpty() : "returnRouteList should mot be empty";
        Map<Integer, Integer> idToDistanceMapForward = forwardRouteList.stream()
                .filter(l -> l.get(1) <= maxTravelDist)
                .collect(Collectors.toMap(l -> l.get(0), l -> l.get(1)));
        Map<Integer, Integer> idToDistanceMapReturn = returnRouteList.stream()
                .filter(l -> l.get(1) <= maxTravelDist)
                .collect(Collectors.toMap(l -> l.get(0), l -> l.get(1)));

        TreeMap<Integer, List<List<Integer>>> distanceToPairs = new TreeMap<>();

        idToDistanceMapForward.forEach((key, value) -> {
            idToDistanceMapReturn.forEach((key2, value2) -> {
                int sum = value + value2;
                distanceToPairs.compute(sum, (k, oldValue) -> {
                    if (sum > maxTravelDist) {
                        return null;
                    }
                    if (oldValue == null) {
                        List<List<Integer>> out = new ArrayList<>();
                        out.add(asList(key, key2));
                        return out;
                    } else {
                        oldValue.add(asList(key, key2));
                        return oldValue;
                    }
                });
            });
        });
        if (distanceToPairs.lastEntry()==null){
            return new ArrayList<>();
        } else{
            return distanceToPairs.lastEntry().getValue();
        }
    }


    @Test
    public void test1() {
        List<List<Integer>> forwardRouteList = new ArrayList<>();
        forwardRouteList.add(asList(1, 3000));
        forwardRouteList.add(asList(2, 5000));
        forwardRouteList.add(asList(3, 7000));
        forwardRouteList.add(asList(4, 10000));

        List<List<Integer>> returnRouteList = new ArrayList<>();
        returnRouteList.add(asList(1, 2000));
        returnRouteList.add(asList(2, 3000));
        returnRouteList.add(asList(3, 4000));
        returnRouteList.add(asList(4, 5000));

        List<List<Integer>> actual = ou(10_000, forwardRouteList, returnRouteList);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(asList(2, 4));
        expected.add(asList(3, 2));


        Assert.assertEquals(expected, actual);
    }
}
