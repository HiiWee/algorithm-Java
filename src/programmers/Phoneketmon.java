package programmers;

import java.util.HashSet;
import java.util.Set;

public class Phoneketmon {

    static class Solution {
        public int solution(int[] nums) {
            int selectSize = nums.length / 2;

            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                set.add(nums[i]);
            }
            return Math.min(selectSize, set.size());
        }
    }

    public static void main(String[] args) {
        int[][] nums = {{3, 1, 2, 3}, {3, 3, 3, 2, 2, 4}, {3, 3, 3, 2, 2, 2}};
        int[] results = {2, 3, 2};
        Solution solution = new Solution();
        for (int i = 0; i < results.length; i++) {
            int answer = solution.solution(nums[i]);
            if (answer == results[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
