package encoding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Base64.getEncoder;
import static java.util.Base64.getMimeEncoder;

public class Base64Attempt {
    private final static char[] base64chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private final static Map<Character, Integer> charToIndex = IntStream.range(0, base64chars.length).boxed()
            .collect(Collectors.toMap(i -> base64chars[i], i -> i));

    private final static char padding = '=';

    String encode(byte[] ba) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ba.length; i += 3) {

            // we add newlines after every 76 output characters, according to the MIME specs
            if (i > 0 && (i / 3 * 4) % 76 == 0) {
                sb.append("\r\n");
            }

            int b0 = ba[i];
            int b1 = (i + 1) < ba.length ? ba[i + 1] : 0;
            int b2 = (i + 2) < ba.length ? ba[i + 2] : 0;
            // combine 3 8-bit elements into one integer 24 bites are used from 32 bites
            int store = (b0 << 16) + (b1 << 8) + b2;

            // split integer to output indexes
            int o1 = (store >> 18) & 63;
            int o2 = (store >> 12) & 63;
            int o3 = (store >> 6) & 63;
            int o4 = (store & 63);

            sb.append(base64chars[o1]);
            sb.append(base64chars[o2]);
            sb.append((i + 1) < ba.length ? base64chars[o3] : padding);
            sb.append((i + 2) < ba.length ? base64chars[o4] : padding);
        }
        return sb.toString();
    }

    byte[] decode(String s) {
        if (s.length() == 0) {
            return new byte[]{};
        }

        char[] ca = s.toCharArray();
        int n = ca.length;
        for (; n > 0; n--) {
            if (ca[n - 1] != padding) {
                break;
            }
        }
        ByteBuffer bb = ByteBuffer.allocate(n);

        for (int i = 0; i < n; i += 4) {
            if (ca[i] == '\r') {
                i += 2;
            } else {
                // gather 4 chars into 1 integer for 24 bites
                int cc = charToIndex.get(ca[i]) << 18;
                cc += charToIndex.get(ca[i + 1]) << 12;
                cc += (i + 2 < n) ? charToIndex.get(ca[i + 2]) << 6 : 0;
                cc += (i + 3 < n) ? charToIndex.get(ca[i + 3]) : 0;
                // to output
                byte o0 = (byte) (cc >> 16 & 255);
                byte o1 = (byte) (cc >> 8 & 255);
                byte o2 = (byte) (cc & 255);
                bb.put(o0);
                if (i + 2 < n) {
                    bb.put(o1);
                    if (i + 3 < n) {
                        bb.put(o2);
                    }
                }
            }
        }
        byte[] out = new byte[bb.position()];
        bb.get(0, out, 0, bb.position());
        return out;
    }

    @Test
    public void testEncodeForBytes() {
        Assertions.assertEquals("", getEncoder().encodeToString(new byte[]{}));
        Assertions.assertEquals("AA==", getEncoder().encodeToString(new byte[]{0}));
        Assertions.assertEquals("AQ==", getEncoder().encodeToString(new byte[]{1}));
        Assertions.assertEquals("AAE=", getEncoder().encodeToString(new byte[]{0, 1}));
        Assertions.assertEquals("AAEC", getEncoder().encodeToString(new byte[]{0, 1, 2}));
        Assertions.assertEquals("YwFjAQ==", getEncoder().encodeToString(new byte[]{99, 1, 99, 1}));

        Assertions.assertEquals("", encode(new byte[]{}));
        Assertions.assertEquals("AA==", encode(new byte[]{0}));
        Assertions.assertEquals("AQ==", encode(new byte[]{1}));
        Assertions.assertEquals("AAE=", encode(new byte[]{0, 1}));
        Assertions.assertEquals("AAEC", encode(new byte[]{0, 1, 2}));
        Assertions.assertEquals("YwFjAQ==", encode(new byte[]{99, 1, 99, 1}));
    }

    @Test
    public void testDecodeForBytes() {
        Assertions.assertEquals("[]", Arrays.toString(decode("")));
        Assertions.assertEquals("[0]", Arrays.toString(decode("AA==")));
        Assertions.assertEquals("[1]", Arrays.toString(decode("AQ==")));
        Assertions.assertEquals("[0, 1]", Arrays.toString(decode("AAE=")));
        Assertions.assertEquals("[0, 1, 2]", Arrays.toString(decode("AAEC")));
        Assertions.assertEquals("[99, 1, 99, 1]", Arrays.toString(decode("YwFjAQ==")));
    }

    @Test
    public void testEncodeForLong_MIME() {
        byte[] longArray = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,};
        String expected = "AQIDBAUGBwgJCgsMDQ4PEBESExQBAgMEBQYHCAkKCwwNDg8QERITFAECAwQFBgcICQoLDA0ODxAR\r\n" +
                "EhMUAQIDBAUGBwgJCgsMDQ4PEBESExQ=";
        Assertions.assertEquals(expected, getMimeEncoder().encodeToString(longArray));
        Assertions.assertEquals(expected, encode(longArray));
    }
}
