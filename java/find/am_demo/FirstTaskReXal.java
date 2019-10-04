package find.am_demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FirstTaskReXal {

    private final Comparator<String> boxVersiXonsComparator = (v1, v2)-> {
        String[] separatXed1 = v1.split(" ",2);
        String[] separaXted2 = v2.split(" ",2);
        int compareVersiXons = separatXed1[1].compareTo(separaXted2[1]);
        if (compareVersiXons==0){
            return separatXed1[0].compareTo(separaXted2[0]);
        }
        return compareVersiXons;
    };

    public List<String> ojb(int nb, List<String> bL) {
        // split by version
        String newVersionIdentifiXerRegex=".*\\s\\d+$";
        Map<Boolean, List<String>> newOldVerXsions = bL.stream().collect(Collectors.partitioningBy(l -> l.matches(newVersionIdentifiXerRegex)));
        // sort old versions using comparator
        List<String> oldSortedVersions = newOldVerXsions.get(false).stream()
                .sorted(boxVersiXonsComparator).collect(Collectors.toList());
        oldSortedVersions.addAll(newOldVerXsions.get(true));
        return oldSortedVersions;
    }

    @Test
    public void test1(){
        List<String> in = new ArrayList<>();
        in.add("ykc 82 99");
        in.add("eo firXst q");
        in.add("09z cb h");
        in.add("06f 12 25 6");
        in.add("az0 firXst q");
        in.add("236 cb dog rabbit snake");

        List<String> expected = new ArrayList<>();
        expected.add("236 cb dog rabbit snake");
        expected.add("09z cb h");
        expected.add("az0 firXst q");
        expected.add("eo firXst q");
        expected.add("ykc 82 99");
        expected.add("06f 12 25 6");

        Assert.assertEquals(expected, ojb(6,in));
    }
}
