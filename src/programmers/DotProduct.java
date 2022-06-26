package programmers;

public class DotProduct {
    static class Solution {
        public int solution(int[] a, int[] b) {
            int answer = 0;
            for (int i = 0; i < a.length; i++) {
                answer += a[i] * b[i];
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] a = {{1, 2, 3, 4}, {-1, 0, 1}};
        int[][] b = {{-3, -1, 0, 2}, {1, 0, -1}};
        int[] results = {3, -2};

        for (int i = 0; i < results.length; i++) {
            int answer = solution.solution(a[i], b[i]);
            if (results[i] == answer) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
