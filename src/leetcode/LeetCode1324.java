package leetcode;

import java.util.*;
import java.util.stream.Collectors;

class LeetCode1324 {
    public List<String> printVertically(String s) {
        String[] splitInput = s.split(" ");
        int n = splitInput.length;

        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, splitInput[i].length());
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= splitInput[j].length()) {
                    answer.append(" ");
                } else {
                    answer.append(splitInput[j].charAt(i));
                }
            }
            answer.append(":");
        }

        return Arrays.stream(answer.toString().split(":"))
                .map(String::stripTrailing)
                .collect(Collectors.toList());
    }
}
