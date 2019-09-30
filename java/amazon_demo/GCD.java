package amazon_demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GCD {
    private int gcd(int a, int b){
        return a==0? b : gcd(b%a, a);
    }

    public int generalizedGCD(int num, int[] arr){
        int out = arr[0];
        for (;num>0;num--){
            out = gcd(arr[num-1],out);
        }
        return out;
    }

    @Test
    public void test1() {
        int actual = generalizedGCD(5, new int[]{2, 3, 4, 5, 6});
        assertEquals(1, actual);
    }

    @Test
    public void test2() {
        int actual = generalizedGCD(5, new int[]{10, 8, 6, 4, 2});
        assertEquals(2, actual);
    }
}