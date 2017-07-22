package find;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class SmallestCommonNumber{
    public int findFromArraysByStreams(int[]... arrays){
        return Arrays.stream(arrays)
                .map(intArr -> Arrays.stream(intArr).boxed().collect(Collectors.toSet())) // convert int[] to Set<Integer>
                .reduce((a,b)-> {
                    a.retainAll(b); // get intersection of all integer sets
                    return a;
                })
                .orElse(new HashSet<>())
                .stream().min(Integer::compareTo) // find minimum
                .orElseThrow(IllegalArgumentException::new);
    }
    @Test
    public void test(){
        Assert.assertEquals(3, findFromArraysByStreams(new int[]{1,2,3}, new int[]{3,4,5}, new int[]{1,2,3,}));
    }
    @Test(expected = IllegalArgumentException.class)
    public void exceptionTest(){
        Assert.assertEquals(3, findFromArraysByStreams(new int[]{1,2,3}, new int[]{4,5}, new int[]{1,2}));
    }

}