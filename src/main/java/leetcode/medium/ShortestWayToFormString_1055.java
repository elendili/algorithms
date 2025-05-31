package leetcode.medium;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/shortest-way-to-form-string/?envType=study-plan-v2&envId=premium-algo-100
public class ShortestWayToFormString_1055 {

    public int shortestWay(String source, String target) {

        ArrayList<Integer>[] sourceCharIndexes = new ArrayList[26];
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            int ci = c - 'a';
            if (sourceCharIndexes[ci] == null) {
                sourceCharIndexes[ci] = new ArrayList<>();
            }
            sourceCharIndexes[ci].add(i);
        }
        int count = 1;
        int sourceI = 0;
        for (char c : target.toCharArray()) {
            int ci = c - 'a';
            ArrayList<Integer> indices = sourceCharIndexes[ci];
            if (indices == null) {
                return -1;
            }
            int index = Collections.binarySearch(indices, sourceI);
            if (index < 0) {
                index = -index - 1;
            }
            if (index == indices.size()) {
                count++;
                sourceI = indices.get(0);
            } else {
                sourceI = indices.get(index);
            }
            sourceI++;

        }
        return count;
    }


    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals(2, shortestWay("abc", "abcbc"));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(-1, shortestWay("abc", "acdbc"));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals(3, shortestWay("xyz", "xzyxz"));
    }
}
