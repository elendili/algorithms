package leetcode.medium;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/problems/design-compressed-string-iterator/?envType=study-plan-v2&envId=premium-algo-100
Design and implement a data structure for a compressed string iterator. The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

Implement the StringIterator class:

next() Returns the next character if the original string still has uncompressed characters, otherwise returns a white space.
hasNext() Returns true if there is any letter needs to be uncompressed in the original string, otherwise returns false.
 */
public class StringIteratorDesign {
    class StringIterator {

        private final String compressedString;
        private int nextCharIndexInCompressed;
        private int charCountInCompressed;
        private char currentChar = ' ';

        public StringIterator(String compressedString) {
            this.compressedString = compressedString;
        }

        public char next() {
            if (charCountInCompressed <= 0) {
                if (hasNext()) {
                    // Move to the next character in the compressed string
                    currentChar = compressedString.charAt(nextCharIndexInCompressed);
                    // Parse the count of this character
                    int count = 0, index = nextCharIndexInCompressed + 1;
                    while (index < compressedString.length() && Character.isDigit(compressedString.charAt(index))) {
                        count = count * 10 + (compressedString.charAt(index) - '0');
                        index++;
                    }
                    charCountInCompressed = count;
                    nextCharIndexInCompressed = index;
                } else {
                    currentChar = ' ';
                }
            }
            charCountInCompressed--;
            return currentChar;
        }

        public boolean hasNext() {
            return nextCharIndexInCompressed < compressedString.length() || charCountInCompressed > 0;
        }

    }

    @org.junit.jupiter.api.Test
    public void test() {
        StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
        List<String> calls = List.of(
                "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext");
        Object[] expected = new Object[]{"L", "e", "e", "t", "C", "o", true, "d", true, "e", false, " ", false};
        for (int i = 0; i < calls.size(); i++) {
            String call = calls.get(i);
            if (call.equals("next")) {
                Object actual = stringIterator.next() + "";
                assertEquals(expected[i], actual);
            } else if (call.equals("hasNext")) {
                Object actual = stringIterator.hasNext();
                assertEquals(expected[i], actual);
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        StringIterator stringIterator = new StringIterator("L10");
        List<String> calls = List.of(
                "next", "next", "next", "next", "next", "next", "next", "next", "next", "next", "next");
        Object[] expected = new Object[]{"L", "L", "L", "L", "L", "L", "L", "L", "L", "L", " "};
        for (int i = 0; i < calls.size(); i++) {
            String call = calls.get(i);
            if (call.equals("next")) {
                Object actual = stringIterator.next() + "";
                assertEquals(expected[i], actual, " at index " + i);
            } else if (call.equals("hasNext")) {
                Object actual = stringIterator.hasNext();
                assertEquals(expected[i], actual, " at index " + i);
            }
        }
    }
    @org.junit.jupiter.api.Test
    public void test3() {
        StringIterator stringIterator = new StringIterator("");
        List<String> calls = List.of(
                "hasNext", "next");
        Object[] expected = new Object[]{false, " "};
        for (int i = 0; i < calls.size(); i++) {
            String call = calls.get(i);
            if (call.equals("next")) {
                Object actual = stringIterator.next() + "";
                assertEquals(expected[i], actual, " at index " + i);
            } else if (call.equals("hasNext")) {
                Object actual = stringIterator.hasNext();
                assertEquals(expected[i], actual, " at index " + i);
            }
        }
    }

}
