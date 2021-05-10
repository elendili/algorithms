package timus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Checker {

    public static void check(BiConsumer<InputStream, PrintStream> sut, String in, String expected) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        try {
            sut.accept(
                    new ByteArrayInputStream(in.getBytes()),
                    out
            );
        } catch (Throwable e) {
            e.printStackTrace();
        }
        assertEquals(expected.trim(), baos.toString().trim());
    }

}
