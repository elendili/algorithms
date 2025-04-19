package leetcode.easy;

import java.util.*;

public class SentenceSimilarity_734 {

    public boolean areSentencesSimilar(String[] sentence1,
                                       String[] sentence2,
                                       List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> pair : similarPairs) {
            String a = pair.get(0);
            String b = pair.get(1);
            map.compute(a, (k, v) -> {
                v = v == null ? new HashSet<>() : v;
                v.add(b);
                return v;
            });
            map.compute(b, (k, v) -> {
                v = v == null ? new HashSet<>() : v;
                v.add(a);
                return v;
            });
        }
        for (int i = 0; i < sentence1.length; i++) {
            String w1 = sentence1[i];
            String w2 = sentence2[i];
            if (w1.equals(w2)) {continue;}
            Set<String> similarToW1 = map.get(w1);
            if (similarToW1==null || !similarToW1.contains(w2)) {
                return false;
            }
        }
        return true;
    }

}
