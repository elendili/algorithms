package collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class KSmallest {
    /*
        Reuses Priority Queue but requires extra memory
     */
    <T extends Comparable<T>> List<T> findKElementsByPriorityQueue(Comparator<T> comparator, int k, List<T> input) {
        PriorityQueue<T> priorityQueue = new PriorityQueue<>(comparator);
        priorityQueue.addAll(input);
        return IntStream.range(0, k).boxed().map(e -> priorityQueue.poll()).collect(toList());
    }
    /*
      Reuses Linked list with fixed size and
      ListIterator for iterating and inserting
     */
    <T extends Comparable<T>> List<T> findKElementsByList(Comparator<T> comparator, int k, List<T> input) {
        LinkedList<T> list = new LinkedList<>();
        input.forEach(e -> {
            if (list.isEmpty()) {
                list.add(e);
            } else {
                T first = list.peekFirst();
                T last = list.peekLast();
                if (comparator.compare(e, first) < 0) {
                    list.addFirst(e);
                    if (list.size() > k) {
                        list.removeLast();
                    }
                } else if (comparator.compare(e, last) < 0) {
                    ListIterator<T> listIterator = list.listIterator();
                    while(listIterator.hasNext()){
                        T e2=listIterator.next();
                        if(comparator.compare(e, e2) < 0){
                            listIterator.previous();
                            listIterator.add(e);
                            if (list.size() > k) {
                                list.removeLast();
                            }
                            break;
                        }
                    }
                } else if (list.size() < k && comparator.compare(e, last) > 0) {
                    list.addLast(e);
                }
            }
        });
        return list;
    }

    List<Integer> l = IntStream.of(1, 5, 4, 6, 6, 3, 2, 7, 1, 9, 11)
            .boxed().collect(toList());

    @Test
    public void test_findKElementsByPriorityQueue() {
        assertEquals("[1, 1, 2]",
                findKElementsByPriorityQueue(naturalOrder(), 3, l).toString());
        assertEquals("[11, 9, 7, 6, 6]",
                findKElementsByPriorityQueue(reverseOrder(), 5, l).toString());
    }

    @Test
    public void test_findKElementsByList() {
        assertEquals("[1, 1, 2]", findKElementsByList(naturalOrder(), 3, l)
                        .toString());
        assertEquals("[11, 9, 7, 6, 6]",
                findKElementsByList(reverseOrder(),5,l).toString());
    }
}
