package find;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmallestCommonNumber{
    public int findFromArraysByStreams(int[]... arrays){
        return Arrays.stream(arrays)
                // arrays to sets
                .map(intArr -> Arrays.stream(intArr).boxed().collect(Collectors.toSet())) // convert int[] to Set<Integer>
                // get intersection of all integer sets: all common elements
                .reduce((a,b)-> {
                    a.retainAll(b);
                    return a;
                })
                .orElse(new HashSet<>())
                //search minimum in resulted set
                .stream().min(Integer::compareTo) // find minimum
                .orElseThrow(IllegalArgumentException::new);
    }
    @Test
    public void test(){
        Assertions.assertEquals(3, findFromArraysByStreams(new int[]{1,2,3}, new int[]{3,4,5}, new int[]{1,2,3,}));
    }
    @Test
    public void exceptionTest(){
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> findFromArraysByStreams(new int[]{1,2,3}, new int[]{4,5}, new int[]{1,2}));
    }

}