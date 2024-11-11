package CompetitiveProgrammerHandbook_AnttiLaaksonen;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Permutations {
    <T> List<List<T>> permutations(List<T> list) {
        List<List<T>> out = new ArrayList<>();
        search(list, new ArrayList<>(), new boolean[list.size()], out);
        return out;
    }

    <T> void search(List<T> list, List<T> permutation, boolean[] chosen, List<List<T>> out) {
        int n = list.size();
        if (permutation.size() == n) {
            out.add(new ArrayList<>(permutation));
        } else {
            for (int i = 0; i < n; i++) {
                if (chosen[i]) continue;
                chosen[i] = true;
                permutation.add(list.get(i));
                search(list, permutation, chosen, out);
                chosen[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("[[]]", permutations(asList()).toString());
        assertEquals("[[A, B, C], [A, C, B], [B, A, C], [B, C, A], [C, A, B], [C, B, A]]", permutations(asList("A", "B", "C")).toString());
    }
}
