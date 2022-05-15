package luxoft.quiz2021July14;

import org.junit.jupiter.api.Assertions;

import java.io.*;

public class SerializationTest implements Serializable {
    public static final Integer DEFAULT = 1;
    private Integer val = DEFAULT;

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(new SerializationTest());
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(baos.toByteArray()));
        SerializationTest test = (SerializationTest) ois.readObject();
        Assertions.assertTrue(test.val == DEFAULT);
    }
}
