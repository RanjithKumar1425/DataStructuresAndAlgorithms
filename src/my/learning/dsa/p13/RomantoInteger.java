package my.learning.dsa.p13;

import java.util.HashMap;
import java.util.Map;

public class RomantoInteger {
    public static void main(String[] args) {
        RomantoInteger rit = new RomantoInteger();
        System.out.println(rit.romanToInt("MCMXCIV")); // 1994
        System.out.println(rit.romanToInt("LVIII")); // 58
        System.out.println(rit.romanToInt("III")); // 3
    }

    public int romanToInt(String s) {
        Map<Character, Integer> rm = new HashMap<>();
        rm.put('M', 1000);
        rm.put('D', 500);
        rm.put('C', 100);
        rm.put('L', 50);
        rm.put('X', 10);
        rm.put('V', 5);
        rm.put('I', 1);
        int n = s.length();
        int number = 0;
        int previous = rm.get(s.charAt(0));
        number = 0;
        for (int i = 1; i < n; i++) {
            int current = rm.get(s.charAt(i));
            if (previous < current) {
                number -= previous;
            } else {
                number += previous;
            }
            previous = current;
        }
        return number + rm.get(s.charAt(n - 1));
    }
}
