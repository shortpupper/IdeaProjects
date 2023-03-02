package woks.woks;

import java.util.Arrays;

public class StringMath {
    public static String StringMath(String dog, String cat) {
        int length = Math.max(cat.length(), dog.length());

        dog = new StringBuilder(dog).reverse().toString();
        cat = new StringBuilder(cat).reverse().toString();

        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            int dogDigit = (i < dog.length()) ? Character.getNumericValue(dog.charAt(i)) : 0;
            int catDigit = (i < cat.length()) ? Character.getNumericValue(cat.charAt(i)) : 0;
            numbers[i] = dogDigit + catDigit;
        }

        for (int i = 0; i < length; i++) {
            if (numbers[i] >= 10) {
                numbers[i] -= 10;
                if (i < length - 1) {
                    numbers[i+1]++;
                } else {
                    numbers = Arrays.copyOf(numbers, length+1);
                    numbers[i+1] = 1;
                    length++;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = length-1; i >= 0; i--) {
            result.append(numbers[i]);
            if (i > 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
