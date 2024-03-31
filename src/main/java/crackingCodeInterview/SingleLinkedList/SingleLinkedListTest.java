package crackingCodeInterview.SingleLinkedList;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static java.util.Arrays.asList;

public class SingleLinkedListTest {

    @Test
    public void generateTest(){
        Assertions.assertEquals("1->2->3", LinkedListNode.from(1,2,3).toString());
    }
    @Test
    public void toDequeTest(){
        Assertions.assertEquals(new LinkedList<>(asList(1,2,3)).toString(),
                LinkedListNode.from(1,2,3).toValueDeque().toString());
    }
}
