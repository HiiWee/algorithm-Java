package leetcode;

import java.util.*;

class LeetCode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        // -4, -1, -1, 0, 1, 2 -> loop array until lastIndex - 2
        // i, l = i + 1, r = nums.length - 1 -> we have 3 elements to add
        // sum < 0, sum > 0
        // sum == 0 -> while (originalValue == nums[l]) l++;

        Arrays.sort(nums);
        List<List<Integer>> answers = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {

            if (i != 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum == 0) {
                    answers.add(List.of(nums[i], nums[l], nums[r]));
                    int originalValue = nums[l];
                    while (l < nums.length && nums[l] == originalValue) {
                        l++;
                    }
                    originalValue = nums[r];
                    while (r >= 0 && nums[r] == originalValue) {
                        r--;
                    }
                } else if (sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }

        return answers;
    }
}
