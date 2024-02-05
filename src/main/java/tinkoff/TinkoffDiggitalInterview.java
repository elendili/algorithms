package tinkoff;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Дана система счисления, в которой запись чисел выглядит следующим образом:  EDCAB
 * Мы знаем, что следующие сочетания дают нам соответствующие значения из десятичной системы:
 * A: 1
 * B: 5
 * C: 10
 * D: 50
 * E: 100
 * AB: 4
 * AC: 9
 * CD: 40
 * CE: 90
 * Полученные числа из комбинаций просто складываются слева направо: EDCAB = 100 + 50 + 10 + 4 = 164
 * При этом комбинации всегда идут в порядке убывания своих значений. То есть разрешена запись BAA = 7, но не может быть записи AAB, так как A < AB
 * <p>
 * Необходимо написать программу, которая переводит числа из этой системы в десятичную
 */
public class TinkoffDiggitalInterview {
    private static class CharsMapping {
        private final Map<String,Integer> mapping = new HashMap<>();
        CharsMapping(){
            mapping.put("A", 1);
            mapping.put("B", 5);
            mapping.put("C", 10);
            mapping.put("D", 50);
            mapping.put("E", 100);
            mapping.put("AB", 4);
            mapping.put("AC", 9);
            mapping.put("CD", 40);
            mapping.put("CE", 90);
        }
        private Integer getValueOrNull(String s) {
            return mapping.get(s);
        }
    }
    static CharsMapping m = new CharsMapping();

    private static int convert(String num) {
        if(num.isEmpty()){
            return 0;
        }
        int out=0;
        int n = num.length();
        for (int i = 0; i < n; i++) {
            Integer ov = null;
            if(i<n-1){
                ov = m.getValueOrNull(num.substring(i, i + 2));
            }
            if(ov!=null){
                i++;
                out+=ov;
            } else {
                ov = m.getValueOrNull(num.substring(i, i + 1));
                if(ov!=null){
                    out+=ov;
                } else {
                    throw new AssertionError("unknown mapping");
                }
            }
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals(convert(""), 0);
        assertEquals(convert("A"), 1);
        assertEquals(convert("B"), 5);
        assertEquals(convert("C"), 10);
        assertEquals(convert("D"), 50);
        assertEquals(convert("E"), 100);
        assertEquals(convert("AB"), 4);
        assertEquals(convert("AC"), 9);
        assertEquals(convert("CD"), 40);
        assertEquals(convert("CE"), 90);
        assertEquals(convert("EDCAB"), 164);
    }
}
