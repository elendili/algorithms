package leetcode.top_interview_questions.easy.other;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/99/others/601/
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> out = new ArrayList<>();
        if(numRows==0){
            return out;
        }
        out.add(java.util.Arrays.asList(1));
        if(numRows==1){
            return out;
        }
        out.add(java.util.Arrays.asList(1,1));
        if(numRows==2){
            return out;
        }
        for(int i=2;i<numRows;i++){
            List<Integer> prev = out.get(i-1);
            List<Integer> toAdd = new ArrayList<>();
            toAdd.add(1);
            for(int j=1;j<i;j++) {
                int pair1=prev.get(j-1);
                int pair2=prev.get(j);
                toAdd.add(pair1+pair2);
            }
            toAdd.add(1);
            out.add(toAdd);
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals( java.util.Arrays.asList(
                java.util.Arrays.asList(1),
                java.util.Arrays.asList(1,1),
                java.util.Arrays.asList(1,2,1),
                java.util.Arrays.asList(1,3,3,1),
                java.util.Arrays.asList(1,4,6,4,1),
                java.util.Arrays.asList(1,5,10,10,5,1)
                ),
                generate(6));
    }
}
