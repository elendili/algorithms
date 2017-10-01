package find;

import org.junit.Test;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/*
    searches bytes permutation in incoming stream, without necessity to sort and fully compare
 */
public class PermutationsInInputStream {

    class ExpectedAndActualOccurrence {
        int etalon, actual;

        boolean matches() {
            return etalon == actual;
        }
    }

    class PermutationsSearcher {
        LinkedList<Byte> actualQ = new LinkedList<>();
        Map<Byte, ExpectedAndActualOccurrence> permutationMap = new HashMap<>();
        final int expectedSize;

        public PermutationsSearcher(byte[] expected) {
            expectedSize = expected.length;
            for (Byte c : expected) {
                addExpected(c);
            }
        }

        private void addExpected(Byte c) {
            permutationMap.putIfAbsent(c, new ExpectedAndActualOccurrence());
            permutationMap.computeIfPresent(c, (c1, eaf) -> {
                eaf.etalon++;
                return eaf;
            });
        }

        void addActual(Byte c) {
            if (expectedSize > 0 && actualQ.size() == expectedSize) {
                Byte first = actualQ.removeFirst();
                permutationMap.computeIfPresent(first, (c1, eaf) -> {
                    eaf.actual--;
                    return eaf;
                });
            }
            actualQ.add(c);
            permutationMap.computeIfPresent(c, (c1, eaf) -> {
                eaf.actual++;
                return eaf;
            });
        }

        boolean matches() {
            return actualQ.size() == expectedSize &&
                    permutationMap.values().stream().allMatch(ExpectedAndActualOccurrence::matches);
        }

        byte[] getActualSlice() {
            byte[] toReturn = new byte[expectedSize];
            for(int i=0; i<actualQ.size(); i++){
                toReturn[i]=actualQ.get(i);
            }
            return toReturn;
        }
    }

    public List<String> getPermutations(String s1, String permutationSource) throws IOException {
        InputStream is = new ByteArrayInputStream(permutationSource.getBytes("UTF-8"));
        return getPermutations(s1.getBytes(),is)
                .stream().map(e -> {
                    try {
                        return new String(e, "UTF-8");
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                        return null;
                    }
                }).collect(toList());
    }

    public List<byte[]> getPermutations(byte[] s1, InputStream permutationSource) throws IOException {
        List<byte[]> toReturn = new ArrayList<>();
        if (s1 != null && permutationSource != null) {
            PermutationsSearcher permutationsSearcher = new PermutationsSearcher(s1);
            int character;
            while ((character = permutationSource.read()) > -1) {
                permutationsSearcher.addActual((byte)character);
                if (permutationsSearcher.matches()) {
                    toReturn.add(permutationsSearcher.getActualSlice());
                }
            }
        }
        return toReturn;
    }

    @Test
    public void test() throws IOException {
        assertEquals(Collections.singletonList("21"), getPermutations("12", "21"));
        assertEquals(Collections.emptyList(), getPermutations("", "1234"));
        assertEquals(Collections.emptyList(), getPermutations("15", "13321"));
        assertEquals(Collections.emptyList(), getPermutations("1125", "122"));
        assertEquals(Arrays.asList("1", "1", "1", "1"), getPermutations("1", "1111"));
        assertEquals(Arrays.asList("12", "21", "12"), getPermutations("12", "1212"));
        assertEquals(Arrays.asList("lho", "hol"), getPermutations("loh", "lho 1 hol"));
    }

}