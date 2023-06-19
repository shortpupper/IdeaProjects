package woks.woks.utils;

import java.lang.reflect.Array;

public class ArrayUtils {
    public static <T> T[] removeFirstInstance(T[] array, T value) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            T[] result = newArray(array.length - 1, array);
            System.arraycopy(array, 0, result, 0, index);
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
            return result;
        }

        return array;
    }
    @SuppressWarnings("unchecked")
    private static <T> T[] newArray(int length, T[] array) {
        Class<?> type = array.getClass().getComponentType();
        return (T[]) Array.newInstance(type, length);
    }
}
