package find.am_demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FirstTaskReal {

    private final Comparator<String> boxVersionsComparator = (v1,v2)-> {
        String[] separated1 = v1.split(" ",2);
        String[] separated2 = v2.split(" ",2);
        int compareVersions = separated1[1].compareTo(separated2[1]);
        if (compareVersions==0){
            return separated1[0].compareTo(separated2[0]);
        }
        return compareVersions;
    };

    public List<String> ojb(int nb, List<String> bL) {
        // split by version
        String newVersionIdentifierRegex=".*\\s\\d+$";
        Map<Boolean, List<String>> newOldVersions = bL.stream().collect(Collectors.partitioningBy(l -> l.matches(newVersionIdentifierRegex)));
        // sort old versions using comparator
        List<String> oldSortedVersions = newOldVersions.get(false).stream()
                .sorted(boxVersionsComparator).collect(Collectors.toList());
        oldSortedVersions.addAll(newOldVersions.get(true));
        return oldSortedVersions;
    }

    @Test
    public void test1(){
        List<String> in = new ArrayList<>();
        in.add("ykc 82 01");
        in.add("eo first qpx");
        in.add("09z cat hamster");
        in.add("06f 12 25 6");
        in.add("az0 first qpx");
        in.add("236 cat dog rabbit snake");

        List<String> expected = new ArrayList<>();
        expected.add("236 cat dog rabbit snake");
        expected.add("09z cat hamster");
        expected.add("az0 first qpx");
        expected.add("eo first qpx");
        expected.add("ykc 82 01");
        expected.add("06f 12 25 6");

        Assert.assertEquals(expected, ojb(6,in));
    }
}
