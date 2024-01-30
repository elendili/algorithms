package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
* https://leetcode.com/problems/design-authentication-manager
 */
public class AuthenticationManagerWithTest {

    class AuthenticationManager {

        private final int timeToLive;
        private final PriorityQueue<Token> pq;
        private final HashMap<String,Token> unexpiredTokenMap;

        record Token(String tokenId, int expiryTime) {
        }

        public AuthenticationManager(int timeToLive) {
            this.timeToLive = timeToLive;
            this.pq = new PriorityQueue<>(Comparator.comparingInt(Token::expiryTime));
            this.unexpiredTokenMap = new HashMap<>();
        }

        void expire(int currentTime) {
            while (!pq.isEmpty() && pq.peek().expiryTime() <= currentTime) {
                Token t = pq.poll();
                unexpiredTokenMap.remove(t.tokenId);
            }
        }

        public void generate(String tokenId, int currentTime) {
            expire(currentTime);
            Token token = new Token(tokenId, currentTime + timeToLive);
            pq.add(token);
            unexpiredTokenMap.put(tokenId, token);
        }

        public void renew(String tokenId, int currentTime) {
            expire(currentTime);
            if (unexpiredTokenMap.containsKey(tokenId)) {
                Token oldToken = unexpiredTokenMap.remove(tokenId);
                pq.remove(oldToken);
                generate(tokenId, currentTime);
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            expire(currentTime);
            return unexpiredTokenMap.size();
        }
    }

    @Test
    public void test() {
        /*
        ["AuthenticationManager","renew","generate","countUnexpiredTokens","generate","renew","renew","countUnexpiredTokens"]
[[5],["aaa",1],["aaa",2],[6],["bbb",7],["aaa",8],["bbb",10],[15]]
         */
        AuthenticationManager am = new AuthenticationManager(5);
        am.renew("aaa", 1);
        am.generate("aaa", 2);
        assertEquals(1, am.countUnexpiredTokens(6));
        am.generate("bbb", 7);
        am.renew("aaa", 8);
        am.renew("bbb", 10);
        assertEquals(0, am.countUnexpiredTokens(15));
    }

    @Test
    public void test2() {
        /*
       "generate","countUnexpiredTokens","renew"]
["aqdm",28],[29],["puv",30]]

Use Testcase
Output
[null,null,0,0,null,null,null,null,null,4,3,3,null,2,2,1,null,1,null]
Expected
[null,null,0,0,null,null,null,null,null,4,3,3,null,2,2,2,null,2,null]
         */
        AuthenticationManager am = new AuthenticationManager(13);
        am.renew("ajvy",1);
        assertEquals(0, am.countUnexpiredTokens(3));
        assertEquals(0, am.countUnexpiredTokens(4));
        am.generate("fuzxq",5);
        am.generate("izmry",7);
        am.renew("puv",12);
        am.generate("ybiqb",13);
        am.generate("gm",14);
        assertEquals(4, am.countUnexpiredTokens(15));
        assertEquals(3, am.countUnexpiredTokens(18));
        assertEquals(3, am.countUnexpiredTokens(19));
        am.renew("ybiqb",21);
        assertEquals(2, am.countUnexpiredTokens(23));
        assertEquals(2, am.countUnexpiredTokens(25));
        assertEquals(2, am.countUnexpiredTokens(26));   // gm + ybiqb
        am.generate("aqdm",28);
        assertEquals(2, am.countUnexpiredTokens(29));
        am.renew("puv",30);
    }
}
