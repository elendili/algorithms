package find.am_demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

/*
* I fucked in task description, because said task was solved for O(nlogn)
* so I have no chance to go in Am after this.
* */
public class SecondTaskReXal {

    List<List<Integer>> ou(int maxTraXvelDist,
                           List<List<Integer>> forwardXRouteList,
                           List<List<Integer>> returnXRouteList) {
        assert maxTraXvelDist > 0 : "should be bigger than 0";
        assert !forwardXRouteList.isEmpty() : "should not be empty";
        assert !returnXRouteList.isEmpty() : "should not be empty";
        Map<Integer, Integer> idToDistaXnceMapForward = forwardXRouteList.stream()
                .filter(l -> l.get(1) <= maxTraXvelDist)
                .collect(toMap(l -> l.get(0), l -> l.get(1)));
        Map<Integer, Integer> idToDistaXnceMapReturn = returnXRouteList.stream()
                .filter(l -> l.get(1) <= maxTraXvelDist)
                .collect(toMap(l -> l.get(0), l -> l.get(1)));

        TreeMap<Integer, List<List<Integer>>> disXtanceToPairs = new TreeMap<>();

        idToDistaXnceMapForward.forEach((key, value) -> {
            idToDistaXnceMapReturn.forEach((key2, value2) -> {
                int sum = value + value2;
                disXtanceToPairs.compute(sum, (k, oldValue) -> {
                    if (sum > maxTraXvelDist) {
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
        if (disXtanceToPairs.lastEntry()==null){
            return new ArrayList<>();
        } else{
            return disXtanceToPairs.lastEntry().getValue();
        }
    }


    @Test
    public void test1() {
        List<List<Integer>> forwXardRouXteList = new ArrayList<>();
        forwXardRouXteList.add(asList(1, 3));
        forwXardRouXteList.add(asList(9, 5));
        forwXardRouXteList.add(asList(3, 7));
        forwXardRouXteList.add(asList(4, 10));

        List<List<Integer>> retXurnRoXuteList = new ArrayList<>();
        retXurnRoXuteList.add(asList(1, 2));
        retXurnRoXuteList.add(asList(7, 3));
        retXurnRoXuteList.add(asList(3, 4));
        retXurnRoXuteList.add(asList(4, 5));

        List<List<Integer>> actual = ou(10, forwXardRouXteList, retXurnRoXuteList);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(asList(3, 7));
        expected.add(asList(9, 4));

        Assert.assertEquals(expected, actual);
    }
}
