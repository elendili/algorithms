package leetcode.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//https://leetcode.com/problems/majority-element
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int maj_index = 0;
        int count = 1;
        for (int i=1; i<nums.length;i++) {
            int v = nums[i];
            if(nums[maj_index]==v){
                count++;
            } else {
                count--;
                if(count==0){
                    maj_index=i;
                    count=1;
                }
            }
        }
        int out = nums[maj_index];
        return out;
    }

    @Test
    public void test(){
        assertEquals(3, majorityElement(new int[]{3,2,3}));
        assertEquals(2, majorityElement(new int[]{2,2,1,1,1,2,2}));
    }
}
