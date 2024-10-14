package leetcode;

import java.util.*;

class LeetCode13 {
    public int romanToInt(String s) {
        Map<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);
        romanNumerals.put('D', 500);
        romanNumerals.put('M', 1000);
        /*
            MCMXCIV
            1000 100 1000 10 100 1 5
            1000 900 90 4 -> 1994
        */
        int[] numbers = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            numbers[i] = romanNumerals.get(s.charAt(i));
        }

        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i != s.length() - 1 && numbers[i] < numbers[i + 1]) {
                answer += numbers[i + 1] - numbers[i];
                i++;
            } else {
                answer += numbers[i];
            }
        }

        return answer;
    }
}
