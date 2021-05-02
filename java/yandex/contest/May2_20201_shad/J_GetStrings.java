package yandex.contest.May2_20201_shad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class J_GetStrings {
    public static void main(String[] args) {
        process(System.in, System.out);
    }

    public static void process(final InputStream in,
                               final PrintStream out
    ) {
        Comparator<String> comparator =
                Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.reverseOrder());
        Set<String> strings = new TreeSet<>(comparator);
        try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
            int count = Integer.parseInt(r.readLine());
            for (int i = 0; i < count; i++) {
                String l = r.readLine();
                strings.add(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        strings.forEach(out::println);
    }


    @Test
    public void test() {
        check("b\n" +
                        "aa\n" +
                        "abb\n" +
                        "baba\n",
                "4\n" +
                        "b\n" +
                        "abb\n" +
                        "baba\n" +
                        "aa");

        check("z\n" +
                        "y\n" +
                        "yy\n" +
                        "zzz\n" +
                        "zyz\n",
                "5\n" +
                        "z\n" +
                        "yy\n" +
                        "y\n" +
                        "zyz\n" +
                        "zzz");

        check("dcabdabccb\n" +
                        "dbccdacaac\n" +
                        "cdaadbcbdc\n" +
                        "ccaaaccdab\n" +
                        "abcddbbaab\n",
                "5\n" +
                        "dbccdacaac\n" +
                        "abcddbbaab\n" +
                        "ccaaaccdab\n" +
                        "cdaadbcbdc\n" +
                        "dcabdabccb");
    }

    void check(String expected, String in) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        process(
                new ByteArrayInputStream(in.getBytes()),
                out
        );
        Assertions.assertEquals(expected, baos.toString());
    }
}
