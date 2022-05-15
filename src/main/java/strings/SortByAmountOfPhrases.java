package strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

// for fun
public class SortByAmountOfPhrases {
    IntPredicate p = c -> c == '.' || c == '?' || c == '!';

    List<String> sortByPhrasesCount(List<String> strings) {
        return strings.stream()
                .map(s -> new AbstractMap.SimpleImmutableEntry<>(s, s.chars().filter(p).count()))
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Test
    public void test() {
        Assertions.assertEquals("[., !?, .?!]",
                sortByPhrasesCount(asList(".?!", ".", "!?")).toString());
        Assertions.assertEquals("[12, . 1, . 1 .]",
                sortByPhrasesCount(asList("12", ". 1", ". 1 .")).toString());
    }
}
