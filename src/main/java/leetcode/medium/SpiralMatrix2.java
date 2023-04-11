package leetcode.medium;

import org.junit.jupiter.api.Test;

import static hackerrank.TestHelper.twoDArrayToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int cellsCount = n*n;
        int[][] out = new int[n][n];
        int x=0,y=0;
        int[] rotation = new int[]{0,1,0,-1};
        int rotationShift = 1;
        int dy=0;
        int dx=1;
        for(int i=1; i<=cellsCount;i++){
            int cv = out[y][x];
            if(cv!=0
                    || y+dy==n
                    || x+dx==n
                    || y+dy==-1
                    || x+dx==-1
            ){
                if(cv!=0){ // step back
                    y-=dy;
                    x-=dx;
                }
                dy=rotation[rotationShift];
                rotationShift=(rotationShift+1)%4;
                dx=rotation[rotationShift];
                if(cv!=0){ // step forward
                    y+=dy;
                    x+=dx;
                }
            }
            out[y][x]=i;
            y+=dy; // next cell
            x+=dx;
        }
        return out;
    }

    @Test
    public void test1(){
       String expected = twoDArrayToString(new int[][]{{1}});
       assertEquals(expected,twoDArrayToString(generateMatrix(1)));
    }
    @Test
    public void test2(){
       String expected = twoDArrayToString(new int[][]{{1,2},{4,3}});
       assertEquals(expected,twoDArrayToString(generateMatrix(2)));
    }
    @Test
    public void test3(){
       String expected = twoDArrayToString(new int[][]{{1,2,3},{8,9,4},{7,6,5}});
       assertEquals(expected,twoDArrayToString(generateMatrix(3)));
    }
    @Test
    public void test4(){
       String expected = twoDArrayToString(new int[][]{
               {1, 2, 3, 4},
               {12,13,14,5},
               {11,16,15,6},
               {10, 9, 8,7},
       });
       assertEquals(expected,twoDArrayToString(generateMatrix(4)));
    }
    @Test
    public void test5(){
       String expected = twoDArrayToString(new int[][]{
               { 1,  2,  3,  4, 5},
               {16, 17, 18, 19, 6},
               {15, 24, 25, 20, 7},
               {14, 23, 22, 21, 8},
               {13, 12, 11, 10, 9},
       });
       assertEquals(expected,twoDArrayToString(generateMatrix(5)));
    }
}
