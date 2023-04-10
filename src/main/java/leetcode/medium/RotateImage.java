package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

//
public class RotateImage {
    public void rotate(int[][] a) {
        int n = a.length;
        if(n>1) {
            int maxY = n / 2 - 1; // 6->2, 5-> 1, 4 -> 1, 3 ->0, 2->0, 1->-1
            for (int y = 0; y <= maxY; y++) {
                int maxX = n-1-y;
                for (int x = y; x < maxX; x++) {
                    rotateCellValueClockwise(a, y, x);
                }
            }
        }
    }

    public void rotateCellValueClockwise(int[][] a,
                                         int y, int x) {
        int v = a[y][x];
        int lastIndex = a.length-1;
        // to right
        // y,x -> x,lastIndex-y
        // x,lastIndex-y -> lastIndex-y,lastIndex-x
        // lastIndex-y,lastIndex-x -> lastIndex-x,y
        v = setAndGetPreviousValue(a, x, lastIndex-y, v);
        v = setAndGetPreviousValue(a, lastIndex-y, lastIndex-x, v);
        v = setAndGetPreviousValue(a, lastIndex-x, y, v);
        v = setAndGetPreviousValue(a, y, x, v);
    }
    public int setAndGetPreviousValue(int[][] a,
                         int y, int x, int v) {
        int out = a[y][x];
        a[y][x]=v;
        return out;
    }
    String twoDArrayToString(int[][]a){
        return Arrays.stream(a)
                .map(row->Arrays.toString(row))
                .collect(Collectors.joining("\n"));
    }
    @Test
    public void test1(){
        int[][] actualInplace= new int[][]{{9}};
        rotate(actualInplace);
        assertEquals(twoDArrayToString(new int[][]{{9}}),
                        twoDArrayToString(actualInplace));
    }

    @Test
    public void test2(){
        int[][] actualInplace= new int[][]{{1,2},{3,4}};
        rotate(actualInplace);
        assertEquals(twoDArrayToString(new int[][]{{3,1},{4,2}}),
                twoDArrayToString(actualInplace));
    }

    @Test
    public void test3(){
        int[][] actualInplace= new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        rotate(actualInplace);
        assertEquals(twoDArrayToString(new int[][]{{7,4,1},{8,5,2},{9,6,3}}),
                twoDArrayToString(actualInplace));
    }
    @Test
    public void test4(){
        int[][] actualInplace= new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        rotate(actualInplace);
        assertEquals(twoDArrayToString(new int[][]{{7,4,1},{8,5,2},{9,6,3}}),
                twoDArrayToString(actualInplace));
    }
}
