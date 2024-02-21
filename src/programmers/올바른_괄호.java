package programmers;

import java.util.*;

class 올바른_괄호 {
    boolean solution(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (count == 0) {
                    return false;
                }
                count--;
            } else {
                count++;
            }
        }
        return count < 1;
    }
}