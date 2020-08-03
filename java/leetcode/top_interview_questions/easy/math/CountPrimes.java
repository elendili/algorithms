package leetcode.top_interview_questions.easy.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/102/math/744/
Count the number of prime numbers less than a non-negative number, n.
*/
public class CountPrimes {

    public int countPrimes(int n) {
        return sieveOfEratosthenes(n);
    }

    public int sieveOfEratosthenes(int n) {
        int out=0;
        boolean[] notAPrime = new boolean[n]; // false is unknown
        int prevPrime = 2;
        for (int i = 2; i < n; i++) { // primes?
            if(!notAPrime[i]){// not installed yet
                out+=1; // add prime
                for (int j = i*(prevPrime%2); // jump forward
                     j < n;
                     j += i
                ) {
                    notAPrime[j] = true; // true is not-prime
                }
                prevPrime = i;
            }
        }
        return out;
    }
    @Test
    public void test() {
        // 2, 3, 5, 7, 11
        Assertions.assertEquals(0, countPrimes(2));
        Assertions.assertEquals(1, countPrimes(3));
        Assertions.assertEquals(2, countPrimes(4));
        Assertions.assertEquals(3, countPrimes(6));
        Assertions.assertEquals(4, countPrimes(10));
        Assertions.assertEquals(5, countPrimes(12));
        Assertions.assertEquals(25, countPrimes(100));
        Assertions.assertEquals(168, countPrimes(1000));
        Assertions.assertEquals(664_579, countPrimes(10_000_000));
    }

}
