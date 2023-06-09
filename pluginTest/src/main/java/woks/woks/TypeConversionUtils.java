package woks.woks;

import java.util.ArrayList;
import java.util.List;

public class TypeConversionUtils {
    public static List<Integer> castIntArrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        return list;
    }
}