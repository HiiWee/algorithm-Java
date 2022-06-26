package programmers;

public class ReverseTernaryScale {
    static class Solution {
        public int solution(int n) {
            StringBuilder str = getTernaryScale(n);
            return Integer.parseInt(str.toString(), 3);
        }

        public StringBuilder getTernaryScale(int num) {
            StringBuilder str = new StringBuilder();
            while (num > 0) {
                str.append(num % 3);
                num /= 3;
            }
            return str;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] n = {45, 125};
        int[] results = {7, 229};

        for (int i = 0; i < n.length; i++) {
            int answer = solution.solution(n[i]);
            if (results[i] == answer) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
