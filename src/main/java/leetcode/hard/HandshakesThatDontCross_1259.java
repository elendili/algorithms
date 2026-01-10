package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * https://leetcode.com/problems/handshakes-that-dont-cross/description/?envType=study-plan-v2&envId=premium-algo-100
 * You are given an even number of people numPeople that stand around a circle and
 * each person shakes hands with someone else so that there are numPeople / 2 handshakes total.
 * <p>
 * Return the number of ways these handshakes could occur such that none of the handshakes cross.
 * <p>
 * Since the answer could be very large, return it modulo 10^9 + 7.
 */
public class HandshakesThatDontCross_1259 {
    /**
     * C(2)=1
     * C(4)=2
     * C(6)=5.  -> C(2)+
     * Initially, there are 2i people. We choose two people to shake hands, so there are 2i−2 remaining people. If there are 2j "left" people, the number of "right" people is 2i−2−2j=2⋅(integer−j−1). In this case, there are dp[j] ways for the "left" people and dp[integer−j−1] for the "right" people to shake hands. By the rule of product, the number of ways for all people to shake hands is dp[j]⋅dp[integer−j−1].
     * <p>
     * To find dp[integer], we should try all possible handshakes that split the people into "left" and "right", integer.e. all possible values for j: dp[integer]=∑
     * j=0
     * integer−1
     * <p>
     * dp[j]⋅dp[integer−j−1].
     */
    private static int m = 1000000007;
    private static final int[] dp = new int[502];
    private static int dpLastIndex = 0;
    static {
        dp[0] = 1;
    }

    public int numberOfWays(int numPeople) {
        int numPairs = numPeople / 2;
        if (dpLastIndex<numPairs) {
            for (int i = 0; i <= numPairs; i++) {
                if (dp[i] > 0) {
                    continue;
                }
                for (int j = 0; j < i; j++) {
                    int leftHalf = dp[j];
                    int rightHalf = dp[i - j - 1];
                    long v = (long) leftHalf * rightHalf;
                    dp[i] += v % m;
                    dp[i] %= m;
                }
            }
            dpLastIndex = numPairs;
        }
        return dp[numPairs];
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected     | numPeople
                    1            | 2  
                    2            | 4  
                    5            | 6  
                    14           | 8  
                    42           | 10  
                    132          | 12  
                    429          | 14  
                    1430         | 16  
                    4862         | 18  
                    16796        | 20  
                    265470434    | 100  
                    591137401    | 1000  
                    685542858    | 140  
                    """
    )
    public void test(int expected, int numPeople) {
        Assertions.assertEquals(expected, numberOfWays(numPeople));
    }
}
