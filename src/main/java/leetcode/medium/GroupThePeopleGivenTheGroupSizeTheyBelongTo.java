package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        // get freq, and distribute by freq
        // burt what to do when there is more than one group of same size? just fill them in order of ?
        Map<Integer, List<List<Integer>>> map = new TreeMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int groupSize = groupSizes[i];
            List<List<Integer>> groups = map.computeIfAbsent(groupSize, k -> new ArrayList<>());
            int groupSameSizeCount = groups.size();
            List<Integer> group;
            if (groupSameSizeCount == 0 || groups.get(groupSameSizeCount - 1).size() == groupSize) {
                group = new ArrayList<>();
            } else {
                group = groups.remove(groupSameSizeCount - 1);
            }
            group.add(i);
            groups.add(group);
        }
        List<List<Integer>> out = new ArrayList<>();
        for(List<List<Integer>> e:map.values()){
            out.addAll(e);
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("[[5], [0, 1, 2], [3, 4, 6]]", groupThePeople(new int[]{3, 3, 3, 3, 3, 1, 3}).toString());
        assertEquals("[[1], [0, 5], [2, 3, 4]]", groupThePeople(new int[]{ 2,1,3,3,3,2 }).toString());
    }
}
