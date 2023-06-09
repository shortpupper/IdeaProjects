package woks.woks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeConversionUtils {
    public static List<Integer> castIntArrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        return list;
    }
    public static int[] castIntegerArrayToIntArray(Integer[] array) {
        return Arrays.stream(array).mapToInt(Integer::intValue).toArray();
    }
}