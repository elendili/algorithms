package collections;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class HeapArrayTest{

    @Test
    public void testNaturalOrder() {
        HeapArray<Integer> ha = new HeapArray<>();
        ha.add(1,2,3,7,1);
        assertEquals("[7, 3, 2, 1, 1]",ha.items().toString());
        assertEquals(7,(int)ha.poll());
        assertEquals("[3, 1, 2, 1]",ha.items().toString());
        assertEquals(3,(int)ha.poll());
        assertEquals("[2, 1, 1]",ha.items().toString());
    }

    @Test
    public void testReversedOrder() {
        HeapArray<Integer> ha = new HeapArray<>(Comparator.<Integer>reverseOrder(),10);
        ha.add(1,2,3,7,1);
        assertEquals("[1, 1, 3, 7, 2]",ha.items().toString());
        assertEquals(1,(int)ha.poll());
        assertEquals(1,(int)ha.poll());
        assertEquals("[2, 7, 3]",ha.items().toString());
    }

    @Test
    public void testReversedOrderSmall() {
        HeapArray<Integer> ha = new HeapArray<>(Comparator.<Integer>reverseOrder(),5);
        ha.add(1,2,3,7,1,4,10);
        assertEquals("[1, 1, 3, 7, 2, 4, 10]",ha.items().toString());
        assertEquals(1,(int)ha.poll());
        assertEquals(1,(int)ha.poll());
        assertEquals(2,(int)ha.poll());
        assertEquals(3,(int)ha.poll());
        assertEquals(4,(int)ha.poll());
        assertEquals(7,(int)ha.poll());
    }

}
