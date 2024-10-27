package helpers;

import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestHelper {

    public static void assertStdOutAfterStdInput(String input, String expectedOut, Runnable runnable) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(buffer, true);

        stubStdInAndStdOut(
                new ByteArrayInputStream(input.getBytes()),
                newOut,
                runnable
        );

        String actualOut = buffer.toString();

        System.out.println("------ expected:");
        System.out.println(expectedOut);
        System.out.println("------ actual:");
        System.out.println(actualOut);
        System.out.println("------");
        Assertions.assertEquals(expectedOut, actualOut);
    }

    public static void stubStdInAndStdOut(final InputStream testInput,
                                          final PrintStream newOut,
                                          final Runnable runnable) {
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
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
    }

    public static String twoDArrayToString(int[][]a){
        return Arrays.stream(a)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }
    public static String twoDArrayToString(char[][]a){
        return Arrays.stream(a)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }
}
