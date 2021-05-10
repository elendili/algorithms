package timus;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static timus.Checker.check;

// JUDGE_ID 316594SC
public class Isenbaev_Number_1837 {
    public static void main(String[] args) throws IOException {
        solution(System.in, System.out);
    }

    static final String startName = "Isenbaev";

    static void solution(InputStream is, PrintStream ps) {
        try {
            StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(is)));
            st.nextToken(); // skip number
            Map<String, Set<Integer>> nameToGroup = new HashMap<>(); // name to group number
            Map<Integer, Set<String>> groupToName = new HashMap<>(); // group to names number
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                String val = st.sval;
                nameToGroup.compute(val, (k, v) -> {
                    v = v == null ? new HashSet<>(3) : v;
                    v.add(st.lineno());
                    return v;
                });
                groupToName.compute(st.lineno(), (k, v) -> {
                    v = v == null ? new HashSet<>(3) : v;
                    v.add(val);
                    return v;
                });
            }
            Map<String, Integer> out = new HashMap<>(); // name to Isenbaev number
            if (nameToGroup.containsKey(startName)) {
                out.put(startName, 0);
                Queue<String> q = new ArrayDeque<>();
                q.add(startName);
                while (!q.isEmpty()) {
                    String name = q.poll();
                    int toIn = out.get(name) + 1;
                    Set<Integer> groups = nameToGroup.remove(name);
                    if (groups != null) {
                        groups.stream()
                                .flatMap(g -> groupToName.get(g).stream())
                                .filter(n -> !out.containsKey(n))
                                .forEach(n -> {
                                    q.add(n);
                                    out.put(n, toIn);
                                });
                    }
                }
            }
            nameToGroup.forEach((k, v) -> out.putIfAbsent(k, -1));

            out.entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .map(e -> e.getKey() + " " + (e.getValue() == -1 ? "undefined" : e.getValue()))
                    .forEach(ps::println);
            ps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() {
        check(Isenbaev_Number_1837::solution,
                "7\n" +
                        "Isenbaev Oparin Toropov\n" +
                        "Ayzenshteyn Oparin Samsonov\n" +
                        "Ayzenshteyn Chevdar Samsonov\n" +
                        "Fominykh Isenbaev Oparin\n" +
                        "Dublennykh Fominykh Ivankov\n" +
                        "Burmistrov Dublennykh Kurpilyanskiy\n" +
                        "Cormen Leiserson Rivest",

                "Ayzenshteyn 2\n" +
                        "Burmistrov 3\n" +
                        "Chevdar 3\n" +
                        "Cormen undefined\n" +
                        "Dublennykh 2\n" +
                        "Fominykh 1\n" +
                        "Isenbaev 0\n" +
                        "Ivankov 2\n" +
                        "Kurpilyanskiy 3\n" +
                        "Leiserson undefined\n" +
                        "Oparin 1\n" +
                        "Rivest undefined\n" +
                        "Samsonov 2\n" +
                        "Toropov 1");
    }

    @Test
    public void test4() {
        check(Isenbaev_Number_1837::solution,
                "5\n" +
                        "Isenbaev A B\n" +
                        "A B C\n" +
                        "D Q P\n" +
                        "C H N\n" +
                        "G N P",

                "A 1\n" +
                        "B 1\n" +
                        "C 2\n" +
                        "D 5\n" +
                        "G 4\n" +
                        "H 3\n" +
                        "Isenbaev 0\n" +
                        "N 3\n" +
                        "P 4\n" +
                        "Q 5");
    }

    @Test
    public void test3() {
        check(Isenbaev_Number_1837::solution,
                "13\n" +
                        "Fominykh Isenbaev BBB\n" +
                        "BBB CCC AAA\n" +
                        "Ayzenshteyn Oparin Samsonov\n" +
                        "Ayzenshteyn Chevdar Samsonov\n" +
                        "Dublennykh Fominykh Ivankov\n" +
                        "Burmistrov Dublennykh Kurpilyanskiy\n" +
                        "Cormen Leiserson Rivest\n" +
                        "Oparin AA AAA\n" +
                        "Isenbaev Oparin Toropov\n" +
                        "AA DD PP\n" +
                        "PP QQ RR\n" +
                        "RR SS TT\n" +
                        "TT Toropov Oparin\n" +
                        "\n"
                ,

                "AA 2\n" +
                        "AAA 2\n" +
                        "Ayzenshteyn 2\n" +
                        "BBB 1\n" +
                        "Burmistrov 3\n" +
                        "CCC 2\n" +
                        "Chevdar 3\n" +
                        "Cormen undefined\n" +
                        "DD 3\n" +
                        "Dublennykh 2\n" +
                        "Fominykh 1\n" +
                        "Isenbaev 0\n" +
                        "Ivankov 2\n" +
                        "Kurpilyanskiy 3\n" +
                        "Leiserson undefined\n" +
                        "Oparin 1\n" +
                        "PP 3\n" +
                        "QQ 4\n" +
                        "RR 3\n" +
                        "Rivest undefined\n" +
                        "SS 3\n" +
                        "Samsonov 2\n" +
                        "TT 2\n" +
                        "Toropov 1");
    }

    @Test
    public void test0() {
        check(Isenbaev_Number_1837::solution,
                "1\n" +
                        "a aa aaa",
                "a undefined\n" +
                        "aa undefined\n" +
                        "aaa undefined");
    }
}

