package leetcode.medium;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/editorial/?envType=study-plan-v2&envId=premium-algo-100
 */
public class MinimumCostToConnectSticks_1167 {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < sticks.length; i++) {
            pq.add(sticks[i]);
        }
        int out = 0 ;
        while(pq.size()>1){
            int cost = pq.poll()+pq.poll();
            pq.add(cost);
            out+=cost;
        }
        return out; 
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(14, connectSticks(new int[]{2,4,3}));
        assertEquals(30, connectSticks(new int[]{1,8,3,5}));
        assertEquals(0, connectSticks(new int[]{5}));
    }
}
