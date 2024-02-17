package leetcode.hard;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/finding-mk-average/
 */
public class FindingMkAverage {
    class MKAverage {

        private final int m;
        private final int k;
        private Queue<Integer> q;
        private TreeMap<Integer, Integer> map;

        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
            this.q = new ArrayDeque<>();
            this.map = new TreeMap<>();
        }

        public void addElement(int num) {
            q.add(num);
            map.compute(num, (k, count) -> count == null ? 1 : count + 1);
            if (q.size() > m) {
                Integer v = q.poll();
                if (v != null) {
                    map.computeIfPresent(v, (k, count) -> {
                        if (count == 1) {
                            return null;
                        }
                        return count - 1;
                    });
                }
            }
        }

        public int calculateMKAverage() {
            if (q.size() < m) {
                return -1;
            }
            int sum = getSumForKShift(map, m, k);
            int count = m - 2 * k;
            return sum / count;
        }

        static int getSumForKShift(TreeMap<Integer, Integer> map, int m, int k) {
            int sum = 0;
            if (m - 2 * k > 0) {
                int currCount = 0;
                for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                    int addCount = e.getValue();
                    int countToSum = getCountToSum(m, k, currCount, addCount);
                    currCount += addCount;
                    int addendum = countToSum * e.getKey();
                    sum += addendum;
                }
            }
            return sum;
        }

        static int getCountToSum(int m, int k, int currCount, int addCount) {
            int nextCount = currCount + addCount;
            int countToSum;
            // jump from before k count to after m-k
            if (currCount < k && nextCount >= m - k) {
                countToSum = m - 2 * k;
            } else if (currCount < k && nextCount >= k) {  // jump from before k count to after k
                countToSum = nextCount - k;
            } else if (currCount < m - k && nextCount >= m - k) {  // jump from before m-k count to after m-k
                countToSum = (m - k) - currCount;
            } else if (nextCount >= k && nextCount <= m - k) {
                countToSum = addCount;
            } else {
                countToSum = 0;
            }
            return countToSum;
        }
    }


    @Test
    public void getCountToSumTest() {
//        static int getCountToSum(int m, int k, int currCount, int addCount){
        // 1,2,3,4,5
        assertEquals(1, MKAverage.getCountToSum(5, 0, 0, 1));

        assertEquals(0, MKAverage.getCountToSum(5, 1, 0, 1));
        assertEquals(1, MKAverage.getCountToSum(5, 1, 1, 1));
        assertEquals(1, MKAverage.getCountToSum(5, 1, 3, 2));
        assertEquals(0, MKAverage.getCountToSum(5, 1, 4, 1));
        assertEquals(3, MKAverage.getCountToSum(5, 1, 0, 10));
        assertEquals(3, MKAverage.getCountToSum(5, 1, 1, 10));

        assertEquals(0, MKAverage.getCountToSum(5, 2, 1, 1));
        assertEquals(1, MKAverage.getCountToSum(5, 2, 2, 1));
        assertEquals(1, MKAverage.getCountToSum(5, 2, 2, 2));
        assertEquals(1, MKAverage.getCountToSum(5, 2, 2, 3));

        assertEquals(1, MKAverage.getCountToSum(5, 2, 1, 10));
        assertEquals(1, MKAverage.getCountToSum(5, 2, 2, 10));
        assertEquals(0, MKAverage.getCountToSum(5, 2, 3, 10));
    }

    @Test
    public void getSumForKShiftTest() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 2); // 1,1
        assertEquals(2, MKAverage.getSumForKShift(map, 2, 0));
        assertEquals(0, MKAverage.getSumForKShift(map, 2, 1));
        map.put(1, 3); // 1,1,1
        assertEquals(1, MKAverage.getSumForKShift(map, 3, 1));
        map.put(2, 2); // 1,1,1,2,2
        assertEquals(7, MKAverage.getSumForKShift(map, 5, 0));
        assertEquals(4, MKAverage.getSumForKShift(map, 5, 1));
        assertEquals(1, MKAverage.getSumForKShift(map, 5, 2));
        assertEquals(0, MKAverage.getSumForKShift(map, 5, 3));
        map.put(3, 1); // 1,1,1, 2,2, 3
        assertEquals(10, MKAverage.getSumForKShift(map, 6, 0));
        assertEquals(6, MKAverage.getSumForKShift(map, 6, 1));
        assertEquals(3, MKAverage.getSumForKShift(map, 6, 2));
        assertEquals(0, MKAverage.getSumForKShift(map, 6, 3));
    }

    @Test
    public void test() {
        MKAverage mka = new MKAverage(3, 1);
        mka.addElement(3);
        mka.addElement(1);
        assertEquals(-1, mka.calculateMKAverage());
        mka.addElement(10);
        assertEquals(3, mka.calculateMKAverage());
        mka.addElement(5);
        mka.addElement(5);
        mka.addElement(5);
        assertEquals(5, mka.calculateMKAverage());
    }

    @Test
    public void test2() {
        /*
["MKAverage","addElement","addElement","calculateMKAverage","addElement","addElement","calculateMKAverage","addElement","addElement","calculateMKAverage","addElement"]
[[3,1],[17612],[74607],[],[8272],[33433],[],[15456],[64938],[],[99741]]
[null,null,null,-1,null,null,25522,null,null,25522,null]
         */
        MKAverage mka = new MKAverage(3, 1);
        mka.addElement(17612);
        mka.addElement(74607);
        assertEquals(-1, mka.calculateMKAverage());
        mka.addElement(8272);
        mka.addElement(33433);
        assertEquals(33433, mka.calculateMKAverage());
        mka.addElement(15456);
        mka.addElement(64938);
        assertEquals(33433, mka.calculateMKAverage());
        mka.addElement(99741);
    }
}
