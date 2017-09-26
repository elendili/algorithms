package collections;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseStackAndQueue {
    // idiots call it linked list, but LinkedList is a Deque
    static class LinkedEntities<T> {
        private Node<T> head;

        static class Node<T> {
            Node<T> next;
            T value;
        }

        private LinkedEntities() {
        }

        public static <T> LinkedEntities<T> createAsStack(T... values) {
            LinkedEntities<T> toReturn = new LinkedEntities<>();
            for (T value : values) {
                Node<T> next = new Node<>();
                next.value = value;
                next.next = toReturn.head;
                toReturn.head = next;
            }
            return toReturn;
        }

        public static <T> LinkedEntities<T> createAsQueue(T... values) {
            LinkedEntities<T> toReturn = new LinkedEntities<>();
            Node<T> current = toReturn.head = new Node<>();
            for (int i = 0; i < values.length; i++) {
                T value = values[i];
                current.value = value;
                if (i < values.length - 1) {
                    current.next = new Node<>();
                    current = current.next;
                }
            }
            return toReturn;
        }

        @Override
        public String toString() {
            String out = "";
            for (Node<T> current = head; current != null; current = current.next) {
                out += current.value + " ";
            }
            return out;
        }

        public void reverseInLoop() {
            Node<T> previous = null;
            Node<T> current = head;
            Node<T> next;
            do { //switch 'next' reference
                next = current.next;
                current.next = previous;
                //move forward
                previous = current;
                current = next;
            } while (current != null); //till the end
            head = previous; //reset head to tail
        }

        public void reverseInRecursion() {
            head = switchRefs(null, head);
        }

        public Node<T> switchRefs(Node<T> previous, Node<T> current) {
            Node<T> next = current.next;
            current.next = previous;
            if (next != null) {
                return switchRefs(current, next);
            }
            return current;
        }

    }

    @Test
    public void stackTest() {
        LinkedEntities<String> entities = LinkedEntities.createAsStack("1", "2", "3", "4", "5");
        assertEquals("5 4 3 2 1 ", entities.toString());
        entities.reverseInLoop();
        assertEquals("1 2 3 4 5 ", entities.toString());
        entities.reverseInRecursion();
        assertEquals("5 4 3 2 1 ", entities.toString());
    }

    @Test
    public void queueTest() {
        LinkedEntities<String> entities = LinkedEntities.createAsQueue("1", "2", "3", "4", "5");
        assertEquals("1 2 3 4 5 ", entities.toString());
        entities.reverseInLoop();
        assertEquals("5 4 3 2 1 ", entities.toString());
        entities.reverseInRecursion();
        assertEquals("1 2 3 4 5 ", entities.toString());
    }

}
