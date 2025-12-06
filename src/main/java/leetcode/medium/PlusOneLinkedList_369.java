package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/plus-one-linked-list/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class PlusOneLinkedList_369 {
    public ListNode plusOne(ListNode head) {
        List<ListNode> asList = new ArrayList<>();
        ListNode n = head;
        while (n != null) {
            asList.add(n);
            n = n.next;
        }
        boolean borrowed = true;
        for (int i = asList.size() - 1; i > -1; i--) {
            n = asList.get(i);
            int cv = n.val;
            if (cv == 9) {
                n.val = 0;
            } else {
                n.val += 1;
                borrowed = false;
                break;
            }
        }
        if (borrowed){
            head = new ListNode(1, head);
        }
        return head;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    nums          | expected
                    0             | 1
                    9             | 1, 0
                    9,9           | 1, 0, 0
                    1,9           | 2, 0
                    """
    )
    public void test(String nums, String expected) {
        List<Integer> input = Arrays.stream(nums.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt).boxed().toList();
        ListNode head = ListNode.createFromNumbers(input);
        head = plusOne(head);
        assertEquals(expected, head.toString());
    }
}
