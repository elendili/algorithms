package yandex.Feb52021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;

/*
Implement iterator which returns only even numbers from provided something
 */
public class CustomIterator implements Iterator<Integer> {
    private final Iterator<Integer> iterator;
    private Integer cache;

    public CustomIterator(Iterable<Integer> iterable) {
        this.iterator = iterable.iterator();
    }

    @Override
    public boolean hasNext() {
        cache = cache != null ? cache : getEvenNumber();
        return cache != null;
    }

    private Integer getEvenNumber() {
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i != null && i % 2 == 0) {
                return i;
            }
        }
        return null;
    }

    @Override
    public Integer next() {
        Integer out = cache != null ? cache : getEvenNumber();
        if (out != null) {
            cache = null;
            return out;
        }
        throw new NoSuchElementException();
    }

}

class CustomIteratorTest {
    @Test
    public void test() {
        CustomIterator ci = new CustomIterator(asList(1, null, 2));
        Assertions.assertTrue(ci.hasNext());
        Assertions.assertEquals(2, ci.next());
        Assertions.assertFalse(ci.hasNext());
    }

    @Test
    public void test2() {
        CustomIterator ci = new CustomIterator(asList(4, -6));
        Assertions.assertTrue(ci.hasNext());
        Assertions.assertEquals(4, ci.next());
        Assertions.assertEquals(-6, ci.next());
        Assertions.assertFalse(ci.hasNext());
    }
}