package yandex.contest;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class _6_mergeSortedListsInOne {
    /*
        Первая строка входного файла содержит единственное число k, k ≤ 1024.
        Каждая из следующих k строк описывает по одному массиву.
        Первое число каждой строки равняется длине соответствующего массива,
        оставшиеся числа этой строки описывают значения элементов этого же массива.
        Элементы массивов являются неотрицательными целыми числами и не превосходят 100.

        Выходной файл должен содержать отсортированный в порядке неубывания массив,
        содержащий все элементы исходных массивов.
    */
    // ascii: {0: 48, 9:57}

    static final byte asciiIndexOf_0 = 48;
    static final byte sizeOfBytesInNumber = 3;
    static final byte maxNumber = 100;
    static final int[] freqs = new int[maxNumber + 1];
    static final byte[] buffer = new byte[sizeOfBytesInNumber];

    // return negative value if input reached EOF
    static byte convertByteArrayToIntWithoutString(final byte[] a) {
        if (a == null) { // no value
            return -1;
        }
        byte cba_out = 0, cba_powerCounter = 1;
        for (byte cba_i = (byte) (a.length - 1); cba_i > -1; cba_i--) {
            byte cba_v = a[cba_i];
            if (cba_v != 0) {
                cba_v -= asciiIndexOf_0;
                cba_out += (cba_v * cba_powerCounter);
                cba_powerCounter *= 10;
            }
        }
        return cba_out;
    }

    // return null if no value
    static byte[] readNumberUntilSpace(final InputStream bis) {
        boolean rnu_hasValue = false;
        try {
            Arrays.fill(buffer, (byte) 0);

            byte rnu_i = 0, rnu_x;
            while ((rnu_x = (byte) bis.read()) != ' ' && rnu_x != -1) {
                if (rnu_x == '\n') { // skip something after new line before space
                    while ((rnu_x = (byte) bis.read()) != ' ' && rnu_x != -1) {
                    }
                    break;
                }
                buffer[rnu_i] = rnu_x;
                rnu_hasValue = true;
                rnu_i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rnu_hasValue ? buffer : null;
    }

    // return count of numbers
    static void updateFrequencies(final int[] freqs, final InputStream bis) {
        byte x;
        int counter = 0;
        while ((x = convertByteArrayToIntWithoutString(readNumberUntilSpace(bis))) > -1) {
            freqs[x] += 1;
            counter++;
            if (counter > 524288) {
                System.gc();
            }
        }
    }

    static void freqsToSortedArrayInOutput(
            final int[] freq,
            final PrintStream out
    ) {
        for (byte i = 0; i < freq.length; i++) {
            int count = freq[i];
            while (count > 0) {
                out.print(i);
                out.write(' ');
                out.flush();
                count--;
            }
        }
    }

    public static void process(final InputStream in,
                               final PrintStream out) {
        readNumberUntilSpace(in);
        updateFrequencies(freqs, in);
        freqsToSortedArrayInOutput(freqs, out);
        out.write('\n');
    }

    public static void main(String[] args) {
        process(System.in, System.out);
    }

}
