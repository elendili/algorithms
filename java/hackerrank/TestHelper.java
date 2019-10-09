package hackerrank;

import hackerrank.s10basicStatistics.Day0_MeanMedianMode;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class TestHelper {

    public static void assertStdOutAfterStdInput(String input, String expectedOut, Runnable runnable) {
        InputStream testInput = new ByteArrayInputStream(input.getBytes());
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(buffer, true);
        try {
            System.setIn(testInput);
            System.setOut(newOut);
            //
            runnable.run();
            //
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
        String actualOut = buffer.toString();
        System.out.println("------ expected:");
        System.out.println(expectedOut);
        System.out.println("------ actual:");
        System.out.println(actualOut);
        System.out.println("------");
        Assert.assertEquals(expectedOut, buffer.toString());
    }
}
