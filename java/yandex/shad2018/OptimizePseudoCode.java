package yandex.shad2018;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://contest.yandex.ru/contest/12341/problems/D/
public class OptimizePseudoCode {
    // original from pseudo code
    public static int foo(List<Integer> a) {
        System.out.println("===================");
        a = new ArrayList<>(a);
        int out = 0;
        while (a.size() > 2) {
            List<Integer> b = a.stream().sorted().collect(Collectors.toList());
            int n = b.size();
            int x = b.get(0) + b.get(n - 2);
            out += x;
            b.remove(n - 2);
            b.remove(0);
            b.add(x);
            System.out.println(a + " => " + b + " : " + out);
            a = b;
        }
        out = a.get(0) + a.get(1) + out;
        System.out.println(out);
        return out;
    }

    public static int optimized(List<Integer> alist) {
        int[] a = alist.stream().mapToInt(i -> i).toArray();
        int out = 0;
        int n = a.length;
        int maxIndex = -1;
        int preMaxIndex = -1;
        for (int i = 0; i < n; i++) {
            int v = a[i];
            if (maxIndex < 0 || a[maxIndex] < v) {
                preMaxIndex = maxIndex;
                maxIndex = i;
            } else if (preMaxIndex < 0 || a[preMaxIndex] < v) {
                preMaxIndex = i;
            }
        }
        // swap max
        int t = a[maxIndex];
        a[maxIndex] = a[n - 1];
        a[n - 1] = t;
        // swap premax
        t = a[preMaxIndex];
        a[preMaxIndex] = a[n - 2];
        a[n - 2] = t;
        // body
        int startIndex = 0;
        while (startIndex + 2 < n) {
            int x = a[startIndex] + a[n - 2];
            out += x;
            a[n - 2] = Math.min(x, a[n - 1]);
            a[n - 1] = Math.max(x, a[n - 1]);
            startIndex++;
        }
        return a[startIndex] + a[startIndex + 1] + out;
    }
    /*
    -5, -4, 4, 5
    -4, 5, -1  out=-1
    5, -5  out=-1-6
    out=-1-6
    -6
     */

    static Stream<Function<List<Integer>, Integer>> methods() {
        return Stream.of(
                OptimizePseudoCode::foo,
                OptimizePseudoCode::optimized
        );
    }

    @ParameterizedTest
    @MethodSource("methods")
    public void test(Function<List<Integer>, Integer> consumer) {
        assertEquals(116, consumer.apply(asList(100, 2, 3, 1))); //
        assertEquals(7, consumer.apply(asList(1, 1, 2, 0)));
        assertEquals(5, consumer.apply(asList(0, 1, 2, 0)));
        assertEquals(9, consumer.apply(asList(1, 2, 3)));
        assertEquals(20, consumer.apply(asList(1, 2, 3, 4)));
        assertEquals(35, consumer.apply(asList(1, 2, 3, 4, 5)));
        assertEquals(56, consumer.apply(asList(1, 2, 3, 4, 5, 6)));
        assertEquals(35, consumer.apply(asList(5, 4, 3, 2, 1)));
        assertEquals(36, consumer.apply(asList(5, 5, 3, 1, 1)));


        assertEquals(2, consumer.apply(asList(1, 1)));
        assertEquals(5, consumer.apply(asList(1, 1, 1)));
        assertEquals(8, consumer.apply(asList(1, 1, 1, 1)));
        assertEquals(12, consumer.apply(asList(1, 1, 1, 1, 1)));
        assertEquals(16, consumer.apply(asList(1, 1, 1, 1, 1, 1)));
    }

    @ParameterizedTest
    @MethodSource("methods")
    public void test2(Function<List<Integer>, Integer> consumer) {
        assertEquals(3, consumer.apply(asList(1, 2)));
        assertEquals(4, consumer.apply(asList(0, 1, 2)));
        assertEquals(11, consumer.apply(asList(0, 1, 2, 3)));
        assertEquals(14, consumer.apply(asList(1, 2, 3, 1)));
    }

    @ParameterizedTest
    @MethodSource("methods")
    public void test3(Function<List<Integer>, Integer> consumer) {
        assertEquals(116, consumer.apply(asList(1, 2, 3, 100)));
        assertEquals(35, consumer.apply(asList(1, 2, 3, 4, 5)));
        assertEquals(17, consumer.apply(asList(1, 2, 1, 2, 1)));
        assertEquals(19, consumer.apply(asList(2, 1, 2, 1, 2)));
        assertEquals(12, consumer.apply(asList(2, 1, 2, 1)));
    }

    @ParameterizedTest
    @MethodSource("methods")
    public void test4(Function<List<Integer>, Integer> consumer) {
        assertEquals(400, consumer.apply(asList(100, 0, 0, 100)));
    }
}
