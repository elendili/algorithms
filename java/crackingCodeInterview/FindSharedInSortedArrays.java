package crackingCodeInterview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

// can be solved using hashtable for O(n) memory,
// but faster for Mem (and CPU) is to reuse task condition:
// arrays are sorted.

public class FindSharedInSortedArrays {
    int getCommonElements(int[] a1, int[]a2){
        int shared=0;
        int j=0;
        for(int e:a1){
            for(;j<a2.length;j++){
                int e2 = a2[j];
                if(e<=e2){
                    if(e==e2){
                        shared++;
                    }
                    break;
                }
            }
        }
        return shared;
    }
    @Test
    public void test(){
        assertEquals(1,getCommonElements(new int[]{2},new int[]{1,2,3}));
        assertEquals(0,getCommonElements(new int[]{},new int[]{1,2}));
        assertEquals(0,getCommonElements(new int[]{1,2},new int[]{}));
        assertEquals(0,getCommonElements(new int[]{3,4},new int[]{1,2}));
        assertEquals(1,getCommonElements(new int[]{0,2,3,4},new int[]{-1,2}));
        assertEquals(1,getCommonElements(new int[]{1,2,3},new int[]{3,4,5}));
        assertEquals(1,getCommonElements(new int[]{3,4,5},new int[]{1,2,3}));
    }
}
