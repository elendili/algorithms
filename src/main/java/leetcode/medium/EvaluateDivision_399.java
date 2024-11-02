package leetcode.medium;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/evaluate-division
 */
public class EvaluateDivision_399 {
    /*
        a/b=0.5
        a/c=4
        a=b * 0.5
        b=a * 2
        a=c * 4
        c=a * 0.25

        a/b=0.5 =>
        b/a=2.0
        a/c=4
        c/a=0.25

        b/c = ?
        b/c = a * 2 / a * 0.25 => 2/0.25

        keep Map<String, Map<String, Double>> where a = b * 0.5 -> {a:{b:0.5}}
         */
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Map<String, Double>> known = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            // a/b=0.5
            List<String> eq = equations.get(i);
            String a = Objects.requireNonNull(eq.get(0));
            String b = Objects.requireNonNull(eq.get(1));
            // a = b * 0.5
            double v = values[i];
            Map<String, Double> aTerm = known.computeIfAbsent(a, k -> new HashMap<>());
            aTerm.put(b, v);
            // b = a * 1/0.5
            double v2 = 1 / v;
            Map<String, Double> bTerm = known.computeIfAbsent(b, k -> new HashMap<>());
            bTerm.put(a, v2);

        }
        Set<String> visited = new HashSet<>();
        double[] out = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> q = queries.get(i);
            String a = Objects.requireNonNull(q.get(0));
            String b = Objects.requireNonNull(q.get(1));
            double v = processQuery(known, a, b, 1, visited);
            out[i] = v;
        }
        return out;
    }

    double processQuery(final Map<String, Map<String, Double>> known,
                        final String src,
                        final String target,
                        double accProduct,
                        Set<String> visited
    ) {
        Map<String, Double> neighbours = known.get(src);
        if (neighbours == null) {
            return -1;
        }
        if (src.equals(target)) {
            return 1;
        }
        visited.add(src);
        double out=-1;
        Double val = neighbours.get(target);
        if (val != null) {
            out = accProduct * val;
        } else {
            for (Map.Entry<String, Double> pair : neighbours.entrySet()) {
                String next = pair.getKey();
                if (!visited.contains(next)) {
                    out = processQuery(known, next, target, accProduct * pair.getValue(), visited);
                    if (out != -1) {
                        break;
                    }
                }
            }
        }
        visited.remove(src);
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        List<List<String>> equations = asList(asList("a", "b"), asList("b", "c"));
        double[] values = new double[]{2, 3};
        List<List<String>> queries = asList(
                asList("a", "c"),
                asList("b", "a"),
                asList("a", "e"),
                asList("a", "a"),
                asList("x", "x")
        );
        String actual = Arrays.toString(calcEquation(equations, values, queries));
        assertEquals("[6.0, 0.5, -1.0, 1.0, -1.0]", actual);
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        List<List<String>> equations = asList(asList("a", "b"), asList("b", "c"), asList("bc", "cd"));
        double[] values = new double[]{1.5, 2.5, 5.0};
        List<List<String>> queries = asList(
                asList("a", "c"),
                asList("c", "b"),
                asList("bc", "cd"),
                asList("cd", "bc")
        );
        String actual = Arrays.toString(calcEquation(equations, values, queries));
        assertEquals("[3.75, 0.4, 5.0, 0.2]", actual);
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        List<List<String>> equations = asList(asList("a", "b"));
        double[] values = new double[]{0.5};
        List<List<String>> queries = asList(
                asList("a", "b"),
                asList("b", "a"),
                asList("a", "c"),
                asList("x", "y")
        );
        String actual = Arrays.toString(calcEquation(equations, values, queries));
        assertEquals("[0.5, 2.0, -1.0, -1.0]", actual);
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        /*
        a/b=2
        b/c=3                  a/c=b/(b/2)=2    => a=2*c ;
        c/d=4                  a/d= (2*c)/(c/3) = 2/3

        a/c
        a/d
         */
        List<List<String>> equations = asList(
                asList("a", "b"),
                asList("b", "c"),
                asList("c", "d")
        );
        double[] values = new double[]{2, 3, 4};
        List<List<String>> queries = asList(
                asList("a", "c"),
                asList("a", "d")
        );
        String actual = Arrays.toString(calcEquation(equations, values, queries));
        assertEquals("[6.0, 24.0]", actual);
    }
}
