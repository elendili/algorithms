package leetcode.easy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
// https://leetcode.com/problems/pascals-triangle-ii
public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        // Pascal Triangle value of row 20 in column 3 equals to C(20,3)
        // C(n,k) = n! / (k! × (n-k)!)
        // C(n,k) / C(n,k-1) = coefficient of increase in the same row
        // C(n,k) / C(n,k-1) = (n - k + 1) / k
        List<Integer> out = new ArrayList<>();
        out.add(1);
        if (rowIndex>0) {
            int n = rowIndex;
            for (int i = 1; i < (n + 2) / 2; i++) {
                long prev = out.get(i - 1);
                long v = prev * (n - i + 1) / (i);
                out.add((int)v);
            }
            n = (rowIndex+1)/2;// half of the final size
            for (int i = n-1; i >= 0; i--) {
                out.add(out.get(i));
            }
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals(List.of(1, 3, 3, 1), getRow(3));
        assertEquals(List.of(1), getRow(0));
        assertEquals(List.of(1, 1), getRow(1));
        assertEquals(List.of(1, 5, 10, 10, 5, 1), getRow(5));
        assertEquals(List.of(1, 6, 15, 20, 15, 6, 1), getRow(6));
    }

    @Test
    public void test2(){
        assertEquals(List.of(1,21,210,1330,5985,20349,54264,116280,203490,293930,352716,352716,293930,203490,116280,54264,20349,5985,1330,210,21,1), getRow(21));

        assertEquals(List.of(1,30,435,4060,27405,142506,593775,2035800,5852925,14307150,30045015,54627300,86493225,119759850,145422675,155117520,145422675,119759850,86493225,54627300,30045015,14307150,5852925,2035800,593775,142506,27405,4060,435,30,1), getRow(30));

    }
}
