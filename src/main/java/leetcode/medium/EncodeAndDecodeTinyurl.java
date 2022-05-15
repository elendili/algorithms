package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

// https://leetcode.com/problems/encode-and-decode-tinyurl/
public class EncodeAndDecodeTinyurl {
    public static class Codec {
        private static final ThreadLocalRandom tlr = ThreadLocalRandom.current();
        private static final String base = "http://tinyurl.com/";
        private static final char[] alphabet =
                ("abcdefghijklmnopqrstuvwxyz" +
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                        "0123456789_.-~").toCharArray();
        private final Map<String, String> code2Url = new HashMap<>();
        private final Map<String, String> url2Code = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            return base + Optional.ofNullable(url2Code.get(longUrl))
                    .orElseGet(() -> {
                        final String code = genCode();
                        int i = 0;
                        while (code2Url.containsKey(code + intToCode(i))) {
                            i++;
                        }
                        String readyCode = code + intToCode(i);
                        code2Url.put(readyCode, longUrl);
                        url2Code.put(longUrl, readyCode);
                        return readyCode;
                    });
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return code2Url.get(shortUrl.substring(base.length()));
        }

        private String intToCode(int n) {
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                int rem = n % alphabet.length;
                sb.append(alphabet[rem]);
                n = n / alphabet.length;
            }
            return sb.toString();
        }

        private String genCode() {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < 6) {
                sb.append(tlr.nextInt(alphabet.length));
            }
            return sb.toString();
        }
    }

    @Test
    public void test() {
        Codec codec = new Codec();
        String url = "https://leetcode.com/problems/encode-and-decode-tinyurl/";
        Assertions.assertEquals(url, codec.decode(codec.encode(url)));
        Assertions.assertTrue(codec.encode(url).startsWith("http://tinyurl.com/"));
    }
}
