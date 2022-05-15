package collections;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermutationsTest {
    @Test
    public void chooseFrom() {
        List<Integer> ar = asList(1, 2, 3);

        assertEquals("[[1], [2], [3]]", Permutations.chooseFrom(1, ar).toString());
        assertEquals("[[1, 2], [1, 3], [2, 1], [2, 3], [3, 1], [3, 2]]",
                Permutations.chooseFrom(2, ar).toString());
        assertEquals("[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]",
                Permutations.chooseFrom(3, ar).toString());

    }

    @Test
    public void chooseFromForBigOnes(){
        // for big ones
        List<Integer>  ar = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        List<List<Integer>> res = Permutations.chooseFrom(5, ar);
        assertEquals(factorial(10).divide(factorial(5)).intValue(), res.size());

        // PERFORMANCE FAIL ON size=20
    }

    public static BigInteger factorial(Object number) {
        return factorial(new BigInteger(String.valueOf(number)));
    }

    public static BigInteger factorial(BigInteger number) {
        BigInteger result = BigInteger.valueOf(1);

        for (long factor = 2; factor <= number.longValue(); factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }

        return result;
    }

}