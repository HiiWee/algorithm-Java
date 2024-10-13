package leetcode;

import java.util.*;

class LeetCode202 {

    Set<Integer> numberCheck = new HashSet<>();

    public boolean isHappy(int n) {
        boolean answer = true;
        int number = n;

        while (number != 1 && !numberCheck.contains(number)) {
            numberCheck.add(number);
            number = calculate(number);
        }

        return number == 1;
    }

    public int calculate(int number) {
        String strNumber = String.valueOf(number);

        int returnNumber = 0;
        for (char c : strNumber.toCharArray()) {
            int currentNumber = c - '0';
            returnNumber += currentNumber * currentNumber;
        }

        return returnNumber;
    }
}
