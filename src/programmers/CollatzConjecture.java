package programmers;

public class CollatzConjecture {
    static class Solution {
        public int solution(int num) {
            int answer = 0;
            while (true) {
                if (answer == 500 || num == 1) {
                    break;
                }
                if (num % 2 == 0) {
                    num /= 2;
                }
                else if (num % 2 == 1) {
                    num *= 3;
                    num++;
                }
                answer++;
            }
            if (num == 1) {
                return answer;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] n = {6, 16, 626331};
        int[] results = {8, 4, -1};

        for (int i = 0; i < n.length; i++) {
            int answer = solution.solution(n[i]);
            if (answer == results[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
