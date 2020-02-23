package yandex.contest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static hackerrank.TestHelper.assertStdOutAfterStdInput;
import static hackerrank.TestHelper.stubStdInAndStdOut;
import static org.junit.Assert.assertEquals;
import static yandex.contest._6_mergeSortedListsInOne.*;

public class _6_mergeSortedListsInOneTest {
    @Test
    public void readNumberUntilSpaceTest() {
        assertEquals(12, convertByteArrayToIntWithoutString(
                readNumberUntilSpace(new ByteArrayInputStream("12 ".getBytes()))));
        assertEquals(12, convertByteArrayToIntWithoutString(
                readNumberUntilSpace(new ByteArrayInputStream("12".getBytes()))));
        assertEquals(1, convertByteArrayToIntWithoutString(
                readNumberUntilSpace(new ByteArrayInputStream("01".getBytes()))));
        assertEquals(9, convertByteArrayToIntWithoutString(
                readNumberUntilSpace(new ByteArrayInputStream("9\n9 ".getBytes()))));
    }

    @Test
    public void updateFrequenciesTest() {
        ByteArrayInputStream bais = new ByteArrayInputStream("1\n1 2 3\n3 2 3 3 4 5\n5 6\n".getBytes());
        int[] freqs = new int[7];
        updateFrequencies(freqs, bais);
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 1, 1, 1}, freqs);
    }

    @Test
    public void convertByteArrayToIntWithoutStringTest() {
        assertEquals(12, convertByteArrayToIntWithoutString("12".getBytes()));
        assertEquals(0, convertByteArrayToIntWithoutString("000".getBytes()));
        assertEquals(12, convertByteArrayToIntWithoutString("012".getBytes()));
        assertEquals(0, convertByteArrayToIntWithoutString("".getBytes()));
    }

    @Test
    public void test() {
        String input = "4\n" +
                "6 2 26 64 88 96 96\n" +
                "4 8 20 65 86 100\n" +
                "7 1 4 16 42 58 61 69\n" +
                "1 84\n";
        String expected = "1 2 4 8 16 20 26 42 58 61 64 65 69 84 86 88 96 96 100 \n";
        assertStdOutAfterStdInput(input, expected,
                () -> _6_mergeSortedListsInOne.main(null));
    }

    @Test
    @Ignore
    public void tstPerformance() {
        InputStream is = new IStream();
        PrintStream ps = new PrintStream(new _3_find_uniqueTest.OStream(), true);
        stubStdInAndStdOut(is, ps,
                () -> _6_mergeSortedListsInOne.process(is, ps)
        );
    }

    static class IStream extends InputStream {
        private int counter=1;
        private volatile boolean newLineSet;

        @Override
        public int read() {
            int out;
            if (newLineSet && (counter % 2 == 0)) {
                newLineSet = false;
                out = ' ';
            } else {
                newLineSet = true;
                counter+=1;
                out= asciiIndexOf_0+counter % 10;
            }
//            System.err.write(out);
//            System.err.flush();
            return out;
        }
    }
}
