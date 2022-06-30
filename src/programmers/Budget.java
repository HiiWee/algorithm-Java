package programmers;

import java.util.Arrays;

public class Budget {
    static class Solution {
        public int solution(int[] d, int budget) {
            int answer = 0;
            Arrays.sort(d);
            for (int i = 0; i < d.length; i++) {
                budget -= d[i];
                if (budget >= 0) {
                    answer++;
                } else {
                    break;
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        int[][] d = {{1, 3, 2, 5, 4}, {2, 2, 2, 3}};
        int[] budget = {9, 10};
        int[] results = {3, 4};

        Solution solution = new Solution();

        for (int i = 0; i < budget.length; i++) {
            int answer = solution.solution(d[i], budget[i]);
            if (answer == results[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }


    }
}
