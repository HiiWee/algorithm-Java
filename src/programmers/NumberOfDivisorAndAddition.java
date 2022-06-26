package programmers;

public class NumberOfDivisorAndAddition {
    static class Solution {
        public int solution(int left, int right) {
            int answer = 0;
            for (int i = left; i <= right; i++) {
                answer += i * determineNumber(getCountDivisor(i));
            }
            return answer;
        }

        public int getCountDivisor(int num) {
            int count = 0;
            for (int i = 1; i <= num; i++) {
                if (num % i == 0) {
                    count++;
                }
            }
            return count;
        }

        public int determineNumber(int num) {
            if (num % 2 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] left = {13, 24};
        int[] right = {17, 27};
        int[] results = {43, 52};

        for (int i = 0; i < results.length; i++) {
            int answer = solution.solution(left[i], right[i]);
            if (results[i] == answer) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
