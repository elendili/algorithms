package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodecDesign {
    public class Codec {
        private static  final char DELIMITER = '#';
        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for (String s : strs) {
                sb.append(s.length()).append(DELIMITER).append(s);
            }
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < s.length(); ) {
                int delimiterIndex = s.indexOf(DELIMITER, i);
                String lengthString = s.substring(i, delimiterIndex);
                int length = Integer.parseInt(lengthString);
                int startIndex = delimiterIndex + 1;
                int exclusiveLastIndex = startIndex + length;
                String str = s.substring(startIndex, exclusiveLastIndex);
                list.add(str);
                i = exclusiveLastIndex;
            }
            return list;
        }
    }
    @org.junit.jupiter.api.Test
    public void test(){
        Codec codec = new Codec();
        List<String> list = List.of("","1","\n","123","!@#$%^\" ","'\"");
        assertEquals(list.toString(), codec.decode(codec.encode(list)).toString());
    }
}
