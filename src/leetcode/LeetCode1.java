package leetcode;

import java.util.*;

class LeetCode1 {

    Map<Integer, Integer> numberToIndex = new HashMap<>();

    public int[] twoSum(int[] nums, int target) {

        int[] answer = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (numberToIndex.containsKey(target - nums[i])) {
                answer[0] = numberToIndex.get(target - nums[i]);
                answer[1] = i;
                break;
            }
            numberToIndex.put(nums[i], i);
        }

        return answer;
    }
}
