package leetcode;

import java.util.*;

class LeetCode66 {

    public int[] plusOne(int[] digits) {
        digits[digits.length - 1]++;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i > 0 && digits[i] == 10) {
                digits[i] = 0;
                digits[i - 1]++;
            }
        }

        int[] answer;
        if (digits[0] == 10) {
            answer = new int[digits.length + 1];
            answer[0] = 1;
            digits[0] = 0;
            for (int i = 1; i < digits.length; i++) {
                answer[i] = digits[i];
            }
            return answer;
        } else {
            return digits;
        }
    }
}
