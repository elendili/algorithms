package hackerrank.easy;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://www.hackerrank.com/challenges/cats-and-a-mouse/problem
public class CatAndMouse {
    static String catAndMouse(int x, int y, int z) {
        String out;
        int fromZtoX=Math.abs(z-x);
        int fromZtoY=Math.abs(z-y);
        if (fromZtoX==fromZtoY){
            out="Mouse C";
        } else if (fromZtoX>fromZtoY){
            out="Cat B";
        } else{
            out="Cat A";
        }
        return out;
    }


    @Test
    public void test() {
        assertEquals("Cat B", catAndMouse(1,2,3));
        assertEquals("Mouse C", catAndMouse(1,3,2));
    }

}
