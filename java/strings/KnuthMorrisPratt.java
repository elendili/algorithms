package strings;

import org.junit.Assert;
import org.junit.Test;

public class KnuthMorrisPratt {
    @Test
    public void test(){
        Assert.assertEquals(3,"aaaaab".indexOf("aab"));
    }
}
