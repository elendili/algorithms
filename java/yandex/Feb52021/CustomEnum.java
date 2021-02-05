package yandex.Feb52021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static yandex.Feb52021.CustomEnum.*;

/*
Написать аналог Enum (как если бы до Java 1.5)
на примере какого-нибудь перечислимого списка,
например списка валют

0) минимум граблей
1) уникальные, фиксированные значения, задаются на этапе комп
2) безопасное сравнение по ссылке
3) ordinal()
4) getByOrdinal(int)
5) Collection values()

*/
public final class CustomEnum {
    public static final CustomEnum USD = new CustomEnum();
    public static final CustomEnum EUR = new CustomEnum();
    public static final CustomEnum RUB = new CustomEnum();

    // Enum data
    private String name;
    private int ordinal;

    private CustomEnum() {
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name();
    }

    public int ordinal() {
        return ordinal;
    }

    public static CustomEnum getByOrdinal(int i) {
        return enums.get(i);
    }

    public static Collection<CustomEnum> values() {
        return enums;
    }

    // wiring things
    private static final List<CustomEnum> enums = new ArrayList<>();

    static {
        // update fields of instances
        int counter = 0;
        for (Field ceField : CustomEnum.class.getDeclaredFields()) {
            if (ceField.getType() == CustomEnum.class) {
                CustomEnum instance;
                try {
                    instance = (CustomEnum) ceField.get(CustomEnum.class);
                    {// set name
                        Field nameF = CustomEnum.class.getDeclaredField("name");
                        nameF.set(instance, ceField.getName());
                    }
                    {// set ordinal
                        Field ordinalF = CustomEnum.class.getDeclaredField("ordinal");
                        ordinalF.set(instance, counter);
                    }
                    // add in list
                    enums.add(instance);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
                counter++;
            }
        }
    }

}

class CustomEnumTest {
    @Test
    public void test() {
        Assertions.assertTrue(USD == USD);
        Assertions.assertEquals(USD, USD);
        Assertions.assertNotEquals(USD, EUR);
        Assertions.assertEquals(0, USD.ordinal());
        Assertions.assertEquals("USD", USD.name());
        Assertions.assertEquals(1, EUR.ordinal());
        Assertions.assertEquals(2, RUB.ordinal());
        Assertions.assertEquals("RUB", RUB.name());
        Assertions.assertEquals(USD, CustomEnum.getByOrdinal(0));
        Assertions.assertEquals(EUR, CustomEnum.getByOrdinal(1));
        Assertions.assertEquals(Arrays.asList(USD, EUR, RUB), CustomEnum.values());
    }
}