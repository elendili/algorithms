package yandex.contest;

import org.junit.Test;

import static hackerrank.TestHelper.assertStdOutAfterStdInput;

public class _5_anagramsTest {

    @Test
    public void test() {
        assertStdOutAfterStdInput("aqiu\naiuq\n", "1\n",
                () -> _5_anagrams.main(null));
        assertStdOutAfterStdInput("azprl\nazprc\n", "0\n",
                () -> _5_anagrams.main(null));
    }

}
