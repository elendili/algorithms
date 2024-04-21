package yandex.contest.old;

import org.junit.jupiter.api.Test;

import static helpers.TestHelper.assertStdOutAfterStdInput;

public class _5_anagramsTest {

    @Test
    public void test() {
        assertStdOutAfterStdInput("aqiu\naiuq\n", "1\n",
                () -> _5_anagrams.main(null));
        assertStdOutAfterStdInput("azprl\nazprc\n", "0\n",
                () -> _5_anagrams.main(null));
    }

}
