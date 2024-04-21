package yandex.contest.old;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static helpers.TestHelper.assertStdOutAfterStdInput;

public class _2_sequencesTest {

    @Test
    public void tst() {
        assertStdOutAfterStdInput("5\n1\n0\n1\n0\n1\n", "1\n",
                () -> _2_sequences.main(null));
    }

    @Test
    public void tst2() {
        int n = 10_000;
        int expected = 5;
        String s1 = "0\n"+String.join("\n", Collections.nCopies(expected, "1"))+"\n0";
        String s2 = IntStream.rangeClosed(0,n-expected)
                .mapToObj(i->String.valueOf(i%2))
        .collect(Collectors.joining("\n"));
//
        assertStdOutAfterStdInput(n+"\n"+s1+"\n"+s2,
                expected+"\n",
                () -> _2_sequences.main(null));
        String in = n+"\n"+s2+"\n"+s1;
//        System.out.println(in);
        assertStdOutAfterStdInput(in,
                expected+"\n",
                () -> _2_sequences.main(null));

    }

}
