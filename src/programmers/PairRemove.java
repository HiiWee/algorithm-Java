package programmers;

import java.util.Stack;

public class PairRemove {
    static class Solution {
        public int solution(String s) {
            int answer = -1;
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
            if (stack.isEmpty()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] s = {"baabaa", "cdcd"};
        int[] results = {1, 0};
        for (int i = 0; i < s.length; i++) {
            int answer = solution.solution(s[i]);
            if (answer == results[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
