package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/simplify-path/
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] a = path.split("/");
        // /a/b/c/../../
        List<String> outPath = new ArrayList<>();
        for (String e : a) {
            if (!e.isEmpty() && !e.equals(".")) {
                if (e.equals("..")) {
                    if (!outPath.isEmpty()) {
                        outPath.remove(outPath.size() - 1);
                    }
                } else {
                    outPath.add(e);
                }
            }
        }
        StringBuilder sb = new StringBuilder(outPath.isEmpty() ? "/" : "");
        for (String s : outPath) {
            sb.append('/').append(s);
        }
        return sb.toString();
    }

    @Test
    public void test() {
        assertEquals("/home", simplifyPath("/home/"));
        assertEquals("/", simplifyPath("/../"));
        assertEquals("/home/foo", simplifyPath("/home//foo/"));
        assertEquals("/home/.../foo", simplifyPath("/home///.../foo/"));
    }

}
