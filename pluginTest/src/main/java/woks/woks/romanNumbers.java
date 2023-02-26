package woks.woks;

import java.util.HashMap;

public class romanNumbers {
    public static String integerToRoman(int number) {
        final int[] values = {32767, 10000, 9000, 1000,900,500,400,100,90,50,40,10,9,5,4,1};
        final String[] romanLetters = {"Z", "R", "RM", "M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        if (number >= 1) {
            for (int i = 0; i < values.length; i++) {
                while (number >= values[i]) {
                    number = number - values[i];
                    roman.append(romanLetters[i]);
                }
            }
            return roman.toString();
        } else {
            return "";
        }
    }

    public static int romanToInteger(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        map.put('R', 10000);
        map.put('Z', 32767);


        int result = 0;
        int prev = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = map.get(s.charAt(i));
            if (curr < prev) {
                result -= curr;
            } else {
                result += curr;
            }
            prev = curr;
        }

        return result;
    }
}
