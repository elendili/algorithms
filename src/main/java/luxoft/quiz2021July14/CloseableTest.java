package luxoft.quiz2021July14;

import java.io.Closeable;
import java.io.IOException;

public class CloseableTest {
    public static void main(String[] args) throws IOException {
        try (C c = new C(new B());) {
            System.out.println("main ");
        }
    }

}

class B implements Closeable {

    @Override
    public void close() throws IOException {
        System.out.println("B closed ");
    }
}

class C implements Closeable {
    private B b;

    public C(B b) {
        this.b = b;
    }

    @Override
    public void close() throws IOException {
        System.out.println("C closed ");
    }
}