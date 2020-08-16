package leetcode.top_interview_questions.medium.dynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/808/

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
 */
public class UniquePaths {
    /*
     Paths depend on decisions count: how many turns (decision to down or to right (and cross boundary between rows or columns)
     are possible on the path to target?
     I think the amount of decisions is ( rows count - 1 ) + (columns count - 1), let's call it N.

     Basically I have no fucking idea how to jump in words from decisions to Combinations.
     I just found it by discovering combinations on paper.
     Here it's explained better: https://betterexplained.com/articles/navigate-a-grid-using-combinations-and-permutations/

     So, the question is how many ways we can rearrange decisions (to down or to right) on the paths.
     Path can be described as: drdrddrr, where N - total amount of letters, and K is amount of some letter.

    Combinations N choose K: C(N,K)=N!/(K!*(N-K)!)

    To simplify calculation we can use

    (N!/K!)/(N-K)!

    where
       (N-K)! can be easily calculated if K is biggest number from rows/columns
       (N!/K!) can be calculated as N*(N-1)*(N-2)*....*(K+1)

    so we need "factorial" method which can multiply sequence of numbers from K+1 to N,
    which is factorial as is when K=0

    Some discussion is here: https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/808/discuss/22958/Math-solution-O(1)-space
    */
    public int uniquePaths(int m, int n) {
        int N = (m-1)+(n-1);
        int K = Math.max(m,n)-1; // choose maximum to decrease loop in sequence multiplying
        // use long to prevent overflow
        // calculate (N!/K!)
        long denominator = multiplySequence(K,N);
        // calculate (N-K)!
        long divisor = multiplySequence(0,N-K);
        long out = denominator/divisor;
        return (int)out; // after divisions answer is not overflowed
    }

    private long multiplySequence(int from,int to){
        long out=1;
        for(int i=from+1;i<=to;i++){
            out*=i;
        }
        return out;
    }

    @Test
    public void test(){
        Assertions.assertEquals(3, uniquePaths(2, 3));
        Assertions.assertEquals(6, uniquePaths(3,3));
        Assertions.assertEquals(20, uniquePaths(4,4));
        Assertions.assertEquals(120, uniquePaths(4,8));
        Assertions.assertEquals(193536720, uniquePaths(23, 12));
    }
}
