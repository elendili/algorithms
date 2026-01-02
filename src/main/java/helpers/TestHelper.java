package helpers;

import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
    private static String leftPad(String v, int length){
        int count = length-v.length();
        return " ".repeat(count)+v;
    }
    public static String twoDArrayToString(int[][]a){
        AtomicInteger maxWidth = new AtomicInteger();
        List<List<String>> lists  = Arrays.stream(a)
                .map(aa-> Arrays.stream(aa).mapToObj(String::valueOf)
                        .peek(s->maxWidth.set(Math.max(s.length(),maxWidth.get())))
                        .collect(Collectors.toList()))
                .toList();

        String out = lists.stream()
                .map(
                        l->l.stream().map(s->leftPad(s,maxWidth.get()))
                        .collect(Collectors.joining(",","[","]"))
                )
                .collect(Collectors.joining("\n"));
        return out;
    }
    
    public static String twoDArrayToString(char[][]a){
        return Arrays.stream(a)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }

    public static int[][] extract2dArrayFromBracketedString(String string) {
        string = string.replaceAll("(^\\s*\\[)|(]\\s*$)", "");
        if (string.isEmpty()) {
            return new int[][]{};
        }
        string = string.replaceAll("(^\\s*\\[)|(]\\s*$)", "");
        List<int[]> outList = Arrays.stream(string.split("],\\["))
                .map(TestHelper::extract1dArrayFromBracketedString).toList();
        int[][] out = outList.toArray(new int[][]{});
        return out;
    }

    public static int[] extract1dArrayFromBracketedString(String string) {
        string = string.replaceAll("(^\\s*\\[)|(]\\s*$)", "");
        if (string.isEmpty()) {
            return new int[]{};
        }
        int[] out = Arrays.stream(string.split(",\\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return out;
    }
}
