package find;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class GreatestCommonDivisor {

    public int recursiveEuclidianGCD(int a, int b){ // no arguments check for performance sake
        if (b == 0) return a;
        return recursiveEuclidianGCD(b, a % b);
    }

    public int loopEuclidianGCD(int a, int b){
        if (a==0 || b==0) return 0; // arguments checks
        for (int c=b; c!=0; c=a%b,a=b,b=c);
        return a;
    }

    public BigInteger embdededGCD(BigInteger a, BigInteger b){
        return a.gcd(b);
    }

    @Test
    public void euclidianGcdTest(){
        assertEquals(3, recursiveEuclidianGCD(9,12));
        assertEquals(1, recursiveEuclidianGCD(17,12));
        assertEquals(1, recursiveEuclidianGCD(1,12));

        assertEquals(3, loopEuclidianGCD(9,12));
        assertEquals(1, loopEuclidianGCD(17,13));
        assertEquals(1, loopEuclidianGCD(1,12));
    }


}
