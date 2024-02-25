package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 */
public class RestoreIPAddresses {

    private static final Set<String> correctOctets = new HashSet<>();

    static {
        for (int i = 0; i < 256; i++) {
            correctOctets.add("" + i);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> out = new ArrayList<>();
        recurse(s, new ArrayList<>(), out);
        return out;
    }

    void recurse(String remainder, List<String> currentOctets, List<String> out) {
        if (currentOctets.size() == 4 && remainder.isEmpty()) {
            String toAdd = String.join(".", currentOctets);
            out.add(toAdd);
        } else {
            int maxLength = Math.min(remainder.length(), 3);
            for (int i = 0; i < maxLength; i++) {
                int subExclusiveEnd = i + 1;
                String sub = remainder.substring(0, subExclusiveEnd);
                if (correctOctets.contains(sub)) {
                    currentOctets.add(sub);
                    recurse(remainder.substring(subExclusiveEnd), currentOctets, out);
                    currentOctets.remove(currentOctets.size() - 1);
                }
            }
        }

    }

    @Test
    public void test() {
        Assertions.assertEquals("[255.255.11.135, 255.255.111.35]", restoreIpAddresses("25525511135").toString());
    }

    @Test
    public void test2() {
        Assertions.assertEquals("[0.0.0.0]", restoreIpAddresses("0000").toString());
    }

    @Test
    public void test3() {
        Assertions.assertEquals("[1.0.10.23, 1.0.102.3, 10.1.0.23, 10.10.2.3, 101.0.2.3]", restoreIpAddresses("101023").toString());
    }
}
