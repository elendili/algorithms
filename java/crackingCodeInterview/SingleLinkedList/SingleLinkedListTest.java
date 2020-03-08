package crackingCodeInterview.SingleLinkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

import static crackingCodeInterview.SingleLinkedList.SingleLinkedList.from;
import static java.util.Arrays.asList;

public class SingleLinkedListTest {

    @Test
    public void generateTest(){
        Assert.assertEquals("1->2->3",from(1,2,3).toString());
    }
    @Test
    public void toDequeTest(){
        Assert.assertEquals(new LinkedList<>(asList(1,2,3)).toString(),
                from(1,2,3).toValueDeque().toString());
    }
}
