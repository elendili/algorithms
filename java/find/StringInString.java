package find;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringInString {
    public boolean withoutContains(String s1,String s2){
        if (s1==null || s2==null) return false;
        if (s2.isEmpty()) return true;
        if (s2.length()>s1.length()) return false;
        // to arrays
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        // default result is false
        boolean foundFlag = false;
        for (int i=0; i<cs1.length-cs2.length+1 && !foundFlag;i++){
            // search substring in string, assuming strings are equal until unequal chars found
            foundFlag = true;
            for (int j =0; j<cs2.length && foundFlag; j++){
                foundFlag = (cs1[i + j] == cs2[j]);
            }
        }
       return foundFlag;
    }
    @Test
    public void test(){
        assertTrue(withoutContains("",""));
        assertTrue(withoutContains("123",""));
        assertTrue(withoutContains("123","1"));
        assertTrue(withoutContains("123","2"));
        assertTrue(withoutContains("123","3"));
        assertTrue(withoutContains("123","123"));
        assertTrue(withoutContains("12345","34"));
        assertFalse(withoutContains("123","4"));
    }

}