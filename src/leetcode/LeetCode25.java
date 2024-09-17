package leetcode;

// O(N^2) 풀이
import java.util.*;

class LeetCode25_1 {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 10000000);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < Math.min(i + 1 + nums[i], nums.length); j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }

        return dp[nums.length - 1];
    }
}

// O(N) 풀이
class LeetCode25_2 {
    public int jump(int[] nums) {

        int farthest = 0;
        int answer = 0;
        int end = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, nums[i] + i);

            if (farthest >= nums.length - 1) {
                answer++;
                break;
            }

            if (i == end) {
                answer++;
                end = farthest;
            }
        }

        return answer;
    }
}
