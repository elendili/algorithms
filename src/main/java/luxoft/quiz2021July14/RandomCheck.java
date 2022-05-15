package luxoft.quiz2021July14;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class RandomCheck {
    @Test
    public void test() {
        Object[] a = new Random()
                .ints(100, 1, 5)
                .mapToObj(String::valueOf)
                .parallel()
                .sorted().toArray();
        System.out.println(Arrays.toString(a));
    }
}
