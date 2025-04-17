package leetcode.medium;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/maximum-distance-in-arrays/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class MaximumDistanceInArrays_624 {
    public int maxDistance(List<List<Integer>> arrays) {
        // get max and min from each array
        int globMin1 = Integer.MAX_VALUE;
        int globMin2 = Integer.MAX_VALUE;
        int globMax1 = Integer.MIN_VALUE;
        int globMax2 = Integer.MIN_VALUE;
        int globMinSrc = 0, globMaxSrc = 0;
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> e = arrays.get(i);
            int first = e.get(0);
            if(first<globMin1) {
                globMin2= globMin1;
                globMin1 = first;
                globMinSrc = i;
            } else if (first<globMin2){
                globMin2= first;
            }
            int last = e.get(e.size()-1);
            if(last>globMax1) {
                globMax2= globMax1;
                globMax1 = last;
                globMaxSrc = i;
            } else if (last>globMax2){
                globMax2= last;
            }
        }
        int out;
        if(globMaxSrc==globMinSrc) {
            out = Math.max(Math.abs(globMax2 - globMin1), Math.abs(globMax1 - globMin2));
        } else {
            out = Math.abs(globMax1 - globMin1);
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(4, maxDistance(List.of(List.of(1,2,3),List.of(4,5),List.of(1,2,3))));
        assertEquals(0, maxDistance(List.of(List.of(1),List.of(1))));
    }
    @org.junit.jupiter.api.Test
    public void test2(){
        assertEquals(4, maxDistance(List.of(List.of(1,4),List.of(0,5))));
        assertEquals(7, maxDistance(List.of(List.of(1,7),List.of(0,5))));
    }
}
