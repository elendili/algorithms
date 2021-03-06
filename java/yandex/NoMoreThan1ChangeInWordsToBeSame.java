package yandex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoMoreThan1ChangeInWordsToBeSame {

    boolean isNoMore1ChangeInWordsToBeSame(String x, String y) {
        if (x.isEmpty()) {
            return y.length() <= 1;
        }
        if (y.isEmpty()) {
            return x.length() <= 1;
        }
        if (Math.abs(x.length() - y.length()) > 1) {
            return false;
        }
        int diff = 0;
        for (int xi = 0, yi = 0;
             xi < x.length() && yi < y.length();
             xi++, yi++) {
            if (x.charAt(xi) != y.charAt(yi)) {
                if (diff>1) {
                    return false;
                } else {
                    diff++;
                    if (yi + 1 < y.length() && x.charAt(xi) == y.charAt(yi + 1)) {
                        yi++; // means removal from x, or add in y.
                    } else if (xi + 1 < x.length() && x.charAt(xi + 1) == y.charAt(yi)) {
                        xi++; // means removal from y, or add in x.
                    } else if (xi + 1 < x.length() && yi + 1 < y.length() && x.charAt(xi + 1) == y.charAt(yi + 1)) {
                        xi++; // means replacement
                        yi++;
                    }
                }
            }
        }
        return diff<2;
    }

    @Test
    public void test() {
        assertTrue(isNoMore1ChangeInWordsToBeSame("", ""));
        assertTrue(isNoMore1ChangeInWordsToBeSame("", "a"));
        assertTrue(isNoMore1ChangeInWordsToBeSame("a", ""));

        assertTrue(isNoMore1ChangeInWordsToBeSame("a", "b"));
        assertTrue(isNoMore1ChangeInWordsToBeSame("ab", "ab"));
        assertTrue(isNoMore1ChangeInWordsToBeSame("ab", "ac"));
        assertTrue(isNoMore1ChangeInWordsToBeSame("ab", "a"));
        assertTrue(isNoMore1ChangeInWordsToBeSame("a", "ab"));
        assertTrue(isNoMore1ChangeInWordsToBeSame("cat", "cut"));
        assertTrue(isNoMore1ChangeInWordsToBeSame("cat", "ct"));

        assertTrue(isNoMore1ChangeInWordsToBeSame("caat", "caaat"));
        assertTrue(isNoMore1ChangeInWordsToBeSame("caat", "caaaa"));
        assertTrue(isNoMore1ChangeInWordsToBeSame("aaa", "aabb"));


        assertFalse(isNoMore1ChangeInWordsToBeSame("", "ab"));
        assertFalse(isNoMore1ChangeInWordsToBeSame("ab", ""));
        assertFalse(isNoMore1ChangeInWordsToBeSame("ab", "cc"));
        assertFalse(isNoMore1ChangeInWordsToBeSame("abc", "a"));
        assertFalse(isNoMore1ChangeInWordsToBeSame("a", "abc"));
    }

}
