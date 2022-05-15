package luxoft.quiz2021July14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestSort {
    @Test
    public void test() {
        List<String> list = Arrays.asList("X", "H", "A", "C", "B", "F");
        List<String> actual = new ArrayList<>();
        list.parallelStream().sorted(Comparator.reverseOrder())
                .forEach(actual::add);

        Assertions.assertEquals("XHFCBA",
                String.join("", actual));
    }
}
