package strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KnuthMorrisPratt {
    @Test
    public void test(){
        Assertions.assertEquals(3,"aaaaab".indexOf("aab"));
    }
}
