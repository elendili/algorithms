package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Stream;

//    https://leetcode.com/problems/lru-cache/
public class LeastRecentlyUsedCache {
    static interface LRUCache {
        int get(int k);

        void put(int k, int v);
    }

    static class LRUCacheOnLinkedHashMap implements LRUCache {
        private final LinkedHashMap<Integer, Integer> lhmap;

        public LRUCacheOnLinkedHashMap(final int capacity) {
            this.lhmap = new LinkedHashMap<>(capacity, 1, true) {
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return this.size() > capacity;
                }
            };
        }

        public int get(int key) {
            return lhmap.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            lhmap.put(key, value);
        }
    }

    static class LRUCacheOnCHM implements LRUCache {
        class Node {
            private final int key;

            private final int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = value;
                this.value = value;
            }

        }

        private final ConcurrentHashMap<Integer, Node> map;
        private final int capacity;
        private final Node tail;   // tail.next leads to head
        private final Node head;   // head.prev leads to tail

        public LRUCacheOnCHM(final int capacity) {
            this.map = new ConcurrentHashMap<>();
            this.capacity = capacity;
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            head.prev = tail;
            tail.next = head;
        }

        public int get(int key) {
            Node out = map.get(key);
            if (out == null) {
                return -1;
            }
            removeFromLinkedList(out);
            insertToHead(out);
            return out.value;
        }

        public void put(int key, int value) {
            map.compute(key, (k, oldNode) -> {
                Node newNode;
                if (oldNode != null) {
                    removeFromLinkedList(oldNode);
                }
                if (oldNode == null) {
                    newNode = new Node(k, value);
                } else if (oldNode.value != value) {
                    newNode = new Node(k, value);
                } else {
                    newNode = oldNode;
                }
                insertToHead(newNode);
                return newNode;
            });

            if (map.size() > capacity) {
                Node toBeRemoved = tail.next;
                removeFromLinkedList(toBeRemoved);
                map.remove(toBeRemoved.key);
            }
        }

        private void insertToHead(Node node) {
            Node beforeHead = this.head.prev;
            beforeHead.next = node;
            node.prev = beforeHead;
            node.next = this.head;
            this.head.prev = node;
        }

        private void removeFromLinkedList(Node node) {
            Node next = node.next;
            Node prev = node.prev;
            prev.next = next;
            next.prev = prev;
        }

    }

    static Stream<Function<Integer, LRUCache>> lruCacheStream() {
        return Stream.of(
                LRUCacheOnLinkedHashMap::new,
                LRUCacheOnCHM::new);
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("lruCacheStream")
    public void test(Function<Integer, LRUCache> lruCacheConstructor) {
        LRUCache lruCache = lruCacheConstructor.apply(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        Assertions.assertEquals(1, lruCache.get(1));
        lruCache.put(3, 3);
        Assertions.assertEquals(-1, lruCache.get(2));     //
        lruCache.put(4, 4);
        Assertions.assertEquals(-1, lruCache.get(1));
        Assertions.assertEquals(3, lruCache.get(3));
        Assertions.assertEquals(4, lruCache.get(4));
    }

}
