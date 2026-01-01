package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindTheCelebrity_277 {
    static class Solution extends Relation {
        Solution(String input) {
            super(input);
        }

        /* The knows API is defined in the parent class Relation.
              boolean knows(int a, int b);

                0  1  2  3
             0  x     x
             1  x  x  x  x
             2        x
             3        x  x                   2 is celeb

             if search answer for is A a celeb?
                iterate knows(a, b) ->
                    false -> a might be celeb, b is not a celeb
                    true -> a is not a celeb, b might be celeb
                    if all false -> a might celeb (also need to check: everyone knows him), all b -> are not celeb
                    if any is true -> a is not a celeb, i -> might be celeb

                iterate knows(i, a) -> if all true -> celeb, if any false - not a celeb


              */
        public int findCelebrity(int n) {
            int candidate = 0;
            for(int i = 1; i < n; i++){
                if(knows(candidate, i)){
                    candidate = i;
                }
            }
            // candidate -- last one who is known by previous candidate, but probably doesn't know previous candidate

            for(int i = 0; i < candidate; i++){
                if(knows(candidate, i) || !knows(i, candidate)){
                    return -1;
                }
            }
            for(int i = candidate + 1; i < n; i++){
                if(!knows(i, candidate)){
                    return -1;
                }
            }
            return candidate;
        }


    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiterString = "|",
            textBlock = """
                    id | input                                       | expected 
                    1  | [[1]]                                       | 0        
                    2  | [[]]                                        | 0       
                    3  | [[1,1],[1,1]]                               | -1       
                    4  | [[1,0],[0,1]]                               | -1       
                    5  | [[1,0],[1,1]]                               | 0        
                    6  | [[1,0,0],[1,1,0],[0,1,1]]                   | -1       
                    7  | [[1,1,1],[1,1,0],[0,0,1]]                   | -1       
                    8  | [[1,1,0],[0,1,0],[1,1,1]]                   | 1        
                    9  | [[1,0,1],[1,1,0],[0,1,1]]                   | -1 
                    10 | [[1,1,1],[0,1,1],[0,0,1]]                   | 2
                    11 | [[1,1,1,1],[1,1,1,0],[0,0,1,1],[1,0,1,1]]   | -1       
                    """
    )
    public void test(String id, String input, String expected) {
        Solution sut = new Solution(input);
        int size = sut.relationsMap.size();
        int actual = sut.findCelebrity(size);
        System.out.println("For size=" + size + ", calls count=" + sut.getCallsCounter());
        assertEquals(Integer.parseInt(expected), actual);
        assertTrue(sut.getCallsCounter() <= size * 3, "For size=" + size + ", calls count=" + sut.getCallsCounter());

    }
}

class Relation {
    Map<Integer, Set<Integer>> relationsMap = new HashMap<>();

    public int getCallsCounter() {
        return callsCounter;
    }

    private int callsCounter = 0;

    Relation(String input) {
        //[[1,1,0],[0,1,0],[1,1,1]]
        String input2 = input.replaceAll("^\\[\\[", "").replaceAll("]]$", "");
        String[] rows = input2.split("],\\[");
        for (int ri = 0; ri < rows.length; ri++) {
            String row = rows[ri];
            String[] s = row.split(",");
            for (int ci = 0; ci < s.length; ci++) {
                String relations = s[ci];
                final int fci = ci;
                if (relations.equals("1")) {
                    relationsMap.compute(ri, (k, v) -> {
                        v = v == null ? new HashSet<>() : v;
                        v.add(fci);
                        return v;
                    });
                }
            }
        }
    }

    boolean knows(int a, int b) {
        callsCounter++;
        return relationsMap.get(a).contains(b);
    }
}