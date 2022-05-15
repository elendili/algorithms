package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/564/
Say you have an array prices for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time
(i.e., you must sell the stock before you buy again).

 */
public class BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] p) {
        if(p==null && p.length<2){
            return 0;
        }
        int n = p.length;
        int out=0;
        for (int i=1;i<n;i++) {
            if (p[i-1]<p[i]) {
                out+=p[i]-p[i-1];
            }
        }
        return out;
    }

    @Test
    public void test(){
        assertEquals(2, maxProfit(new int[]{1,3}));
        assertEquals(4, maxProfit(new int[]{7,1,5}));
        assertEquals(3, maxProfit(new int[]{7,10,5}));
        assertEquals(2, maxProfit(new int[]{1,2,1,2}));
        assertEquals(4, maxProfit(new int[]{7,1,5,3}));
        assertEquals(7, maxProfit(new int[]{1,5,3,6}));
        assertEquals(3, maxProfit(new int[]{5,3,6}));
        assertEquals(7, maxProfit(new int[]{7,1,5,3,6,4}));
        assertEquals(4, maxProfit(new int[]{1,2,3,4,5}));
        assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
    }
}

