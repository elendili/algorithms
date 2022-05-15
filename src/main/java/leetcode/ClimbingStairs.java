package leetcode;
/* https://leetcode.com/problems/climbing-stairs/
You are climbing a stair case.
It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps.
In how many distinct ways can you climb to the top?
* */
public class ClimbingStairs {
    // it's fibonacci sequence, basically
    public int climbStairs(int n) {
        int c2=0;
        int c1=1;
        int c=1;
        for(int i=0;i<n;i++){
            c=c1+c2;
            c2=c1;
            c1=c;
        }
        return c;
    }
}