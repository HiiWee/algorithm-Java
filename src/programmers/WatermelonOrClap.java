package programmers;

public class WatermelonOrClap {
    static class Solution {
        public String solution(int n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    sb.append("수");
                } else {
                    sb.append("박");
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] n = {3, 4};
        String[] results = {"수박수", "수박수박"};
        for (int i = 0; i < n.length; i++) {
            String answer = solution.solution(n[i]);
            if (answer.equals(results[i])) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
