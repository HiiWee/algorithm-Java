package leetcode;

import java.util.*;

class LeetCode17 {

    static final String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> answer = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return answer;
        }
        search(new StringBuilder(), 0, digits);

        return answer;
    }

    public void search(StringBuilder current, int index, String digits) {
        if (current.length() == digits.length()) {
            answer.add(current.toString());
            return;
        }

        String alpha = map[digits.charAt(index) - '0'];
        for (int i = 0; i < alpha.length(); i++) {
            current.append(alpha.charAt(i));
            search(current, index + 1, digits);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
