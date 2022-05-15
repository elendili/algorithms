package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

//    https://leetcode.com/problems/lru-cache/
public class LeastRecentlyUsedCache {

    static class LRUCache{
        final LinkedHashMap<Integer,Integer> lhmap;
        public LRUCache(final int capacity) {
            this.lhmap = new LinkedHashMap<Integer,Integer>(capacity,1,true){
                protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
                    return this.size()>capacity;
                }
            };
        }

        public int get(int key) {
            return lhmap.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            lhmap.put(key,value);
        }
    }
    @Test
    public void test(){
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        Assertions.assertEquals(1,lruCache.get(1));
        lruCache.put(3,3);
        Assertions.assertEquals(-1,lruCache.get(2));
        lruCache.put(4,4);
        Assertions.assertEquals(-1,lruCache.get(1));
        Assertions.assertEquals(3,lruCache.get(3));
        Assertions.assertEquals(4,lruCache.get(4));
    }

}
