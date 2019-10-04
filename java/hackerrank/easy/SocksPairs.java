package hackerrank.easy;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SocksPairs {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        Map<Integer,Double> map = new HashMap<>();
        Arrays.stream(ar).forEach(color->
                map.compute(color,(k,oldV)-> {
                    if (oldV == null) {
                        return 0.5;
                    } else {
                        return oldV + 0.5;
                    }
                }));
       return map.values().stream().mapToInt(Double::intValue).sum();
    }
    @Test
    public void test(){
        assertEquals(2,sockMerchant(7,new int[]{1,2,1,2,1,3,2}));
    }

}
