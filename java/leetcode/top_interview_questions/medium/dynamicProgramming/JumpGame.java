package leetcode.top_interview_questions.medium.dynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/807/
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 3 * 10^4
0 <= nums[i][j] <= 10^5
 */
public class JumpGame {
    // let's jump from the right to left,
    // checking availability of position(index) from the end
    // if first position (0-index) is not available return false
    public boolean canJump(int[] a) {
        int lastGoodPosition = a.length-1;
        for(int i=lastGoodPosition;i>-1;i--){
            // if we're able to jump to last good position,
            // implying consequent jumping to the target end position
            // let then shift good position to the left, making it available for jumping from the left
            if(i+a[i]>=lastGoodPosition){
                lastGoodPosition=i;
            }
        }
        return lastGoodPosition==0;
    }
    @Test
    public void test(){
        Assertions.assertEquals(true, canJump(new int[]{2,0}));
        Assertions.assertEquals(true, canJump(new int[]{2,3,1,1,4}));
        Assertions.assertEquals(false,canJump(new int[]{3,2,1,0,4}));
    }
}
