package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations {
    public static <T> List<List<T>> chooseFrom(int toChoose, List<T> source) {
        List<List<T>> out = new ArrayList<>();
        if (toChoose > source.size()) {
            throw new IllegalArgumentException("Set size " + source.size() + " less than amount to choose " + toChoose);
        }
        for (int i = 0; i < source.size(); i++) {
            T start = source.get(i);
            if (toChoose <= 1) {
                out.add(new ArrayList<>(Collections.singletonList(start)));
            } else {
                List<T> nSource = new ArrayList<>(source);
                nSource.remove(i);
                List<List<T>> interim = chooseFrom(toChoose - 1, nSource);
                for (List<T> l : interim) {
                    l.add(0, start);
                    out.add(l);
                }
            }
        }
        return out;
    }
}
