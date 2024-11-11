package CompetitiveProgrammerHandbook_AnttiLaaksonen;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Combinations {

    static <T> List<List<T>> combinationsViaBits(List<T> list) {
        List<List<T>> out = new ArrayList<>();
        int n = list.size();
        for (int b = 0; b < (1 << n); b++) {
            List<T> combination = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((b & (1 << i)) > 0) {
                    combination.add(list.get(i));
                }
            }
            out.add(combination);
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("[[]]", combinationsViaBits(asList()).toString());
        assertEquals("[[], [A], [B], [A, B], [C], [A, C], [B, C], [A, B, C]]", combinationsViaBits(asList("A", "B", "C")).toString());
    }

}
