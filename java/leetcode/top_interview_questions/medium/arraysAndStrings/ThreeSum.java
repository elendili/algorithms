package leetcode.top_interview_questions.medium.arraysAndStrings;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/776/
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Note:
The solution set must not contain duplicate triplets.
Example:
Given array nums = [-1, 0, 1, 2, -1, -4],
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
public class ThreeSum {
    /*
        Such as combination of 2 numbers defines all 3 (sum should be equal to 0), we can save these 2 in hashset.
        To make less objects, we can reuse Long as a storage of integers.
    */
    long toLong(int x, int y) {
        return (((long) x) << 32) | (y & 0xffffffffL);
    }

    public List<List<Integer>> threeSum(int[] a) {
        if (a.length < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> out = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:a){
            map.compute(i,(k,v)->v==null?1:++v);
        }
        Set<Long> visited = new HashSet<>();
        for (int i = 0; i < a.length - 2; i++) {
            final int x = a[i];
            for (int j = i + 1; j < a.length - 1; j++) {
                final int y = a[j];
                long key1 = toLong(x, y);
                long key2 = toLong(y, x);
                if (!visited.contains(key1) && !visited.contains(key2)) {
                    final int z = -(x + y);
                    if (map.containsKey(z)) {
                        if((z==x||z==y)&&map.get(z)==1){
                            continue;
                        }
                        if((z==x && z==y)&&map.get(0)<3){
                            continue;
                        }
                        out.add(Arrays.asList(x, y, z));
                        visited.add(key1);
                        visited.add(key2);
                        visited.add(toLong(x, z));
                        visited.add(toLong(y, z));
                    }
                }
            }
        }
        return out;
    }


    int fromLongFirst(long l) {
        return (int) (l >> 32);
    }

    int fromLongSecond(long l) {
        return (int) l;
    }

    @Test
    public void test() {
        assertSetsEquals(Arrays.asList(Arrays.asList(-2,0,2)), threeSum(new int[]{-2,0,0,2,2}));
        assertSetsEquals(Collections.emptyList(), threeSum(new int[]{-2, -1, 2, 1}));
        assertSetsEquals(Collections.emptyList(), threeSum(new int[]{1, -2, -1, 2}));
        assertSetsEquals(Collections.emptyList(), threeSum(new int[]{1, 2, -2, -1}));
        assertSetsEquals(Arrays.asList(Arrays.asList(-2, 1, 1)), threeSum(new int[]{1, 1, -2}));
        assertSetsEquals(Arrays.asList(Arrays.asList(-2, 1, 1)), threeSum(new int[]{3, -2, 1, 1}));
        assertSetsEquals(Arrays.asList(Arrays.asList(-2, 1, 1)), threeSum(new int[]{1, -2, 1, 1}));
        assertSetsEquals(Arrays.asList(Arrays.asList(-1, 0, 1), Arrays.asList(-1, 2, -1)), threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        assertSetsEquals(Arrays.asList(Arrays.asList(-1, 0, 1)), threeSum(new int[]{-1, -1, 0, 1, 1}));
        assertSetsEquals(Collections.emptyList(), threeSum(new int[]{1, -1}));
        assertSetsEquals(Arrays.asList(Arrays.asList(0, -1, 1)), threeSum(new int[]{1, -1, 0}));
        assertSetsEquals(Arrays.asList(Arrays.asList(1, -3, 2)), threeSum(new int[]{1, 2, -3}));
        assertSetsEquals(Arrays.asList(Arrays.asList(0, -2, 2), Arrays.asList(2, 2, -4)), threeSum(new int[]{2, 2, -2, -4, 0}));
        assertSetsEquals(Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, -1, 1),
                Arrays.asList(0, -2, 2), Arrays.asList(-1, -1, 2), Arrays.asList(1, 1, -2)
        ), threeSum(new int[]{-2, -2, -1, -1, 0, 0, 0, 1, 1, 2, 2}));

        assertSetsEquals(Arrays.asList(Arrays.asList(0, -1, 1), Arrays.asList(0, -2, 2)),
                threeSum(new int[]{2, 1, -2, -1, 0}));
    }

    void assertSetsEquals(List<List<Integer>> expected, List<List<Integer>> actual) {
        List<String> expectedS = expected.stream().map(l -> l.stream()
                .sorted().collect(Collectors.toList()).toString())
                .sorted().collect(Collectors.toList());
        List<String> actualS = actual.stream().map(l -> l.stream().sorted().collect(Collectors.toList()).toString())
                .sorted().collect(Collectors.toList());
        assertEquals(expectedS, actualS);
    }

    @Test
    public void testStoringInLong() {
        assertEquals(-99, fromLongFirst(toLong(-99, 2)));
        assertEquals(2, fromLongSecond(toLong(-99, 2)));

        assertEquals(2, fromLongFirst(toLong(2, -99)));
        assertEquals(-99, fromLongSecond(toLong(2, -99)));

        assertEquals(-2, fromLongFirst(toLong(-2, -99)));
        assertEquals(-99, fromLongSecond(toLong(-2, -99)));
    }
}
