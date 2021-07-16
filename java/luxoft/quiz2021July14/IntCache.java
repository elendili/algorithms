package luxoft.quiz2021July14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntCache {
    @Test
    public void test() {
        Assertions.assertTrue(
                Integer.valueOf(-128)
                        == Integer.valueOf(-128)
        );
    }
}
