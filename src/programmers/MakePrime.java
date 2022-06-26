package programmers;

public class MakePrime {
    static class Solution {
        boolean[] visited;
        int answer = 0;

        public int solution(int[] nums) {
            visited = new boolean[nums.length];
            combination(nums, 0, nums.length, 3);

            return answer;
        }

        public void combination(int[] nums, int depth, int n, int r) {
            if (r == 0) {
                // 값 구해서 소수판별
                countPrime(nums);
                return;
            }

            for (int i = depth; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    combination(nums, i + 1, n, r - 1);
                    visited[i] = false;
                }
            }
        }

        public void countPrime(int[] nums) {
            int total = 0;
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    total += nums[i];
                }
            }
            if (isPrime(total)) {
                answer++;
            }
        }

        public boolean isPrime(int num) {
            for (int i = 2; i < num / 2; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }

        public void init() {
            int length = visited.length;
            visited = new boolean[length];
            answer = 0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] nums = {{1, 2, 3, 4}, {1, 2, 7, 6, 4}};
        int[] results = {1, 4};
        for (int i = 0; i < results.length; i++) {
            int answer = solution.solution(nums[i]);
            solution.init();
            if (answer == results[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
