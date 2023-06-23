package woks.woks.utils;

public class ArrayUtils {
    public static int[] removeFirstInstance(int[] array, int target) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            int[] newArray = new int[array.length - 1];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
            return newArray;
        }

        return array;
    }

    public static String[] removeLastElement(String[] array) {
        if (array.length == 0) {
            return array; // If the array is empty, return it as is
        }

        // Create a new array with a length one less than the original array
        String[] result = new String[array.length - 1];

        // Copy elements excluding the last one
        System.arraycopy(array, 0, result, 0, array.length - 1);

        return result;
    }
}