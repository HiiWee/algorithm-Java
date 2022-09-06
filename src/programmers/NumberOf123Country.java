package programmers;

public class NumberOf123Country {
    static class Solution {
        public String solution(int n) {
            StringBuilder sb = new StringBuilder();

            int rest;
            while (n != 0) {
                rest = n % 3;
                n = n / 3;

                if (rest == 0) {
                    n--;
                    rest = 4;
                }
                sb.append(rest);

            }
            return sb.reverse().toString();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] n = {1, 2, 3, 4};
        String[] result = {"1", "2", "4", "11"};

        for (int i = 0; i < n.length; i++) {
            String answer = solution.solution(n[i]);

            if (answer.equals(result[i])) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
