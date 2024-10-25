package leetcode;

import java.util.*;

class LeetCode80_1 {
    public int removeDuplicates(int[] nums) {

        int sameCount = 1;
        int number = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (number == nums[i]) {
                sameCount++;
            } else {
                number = nums[i];
                sameCount = 1;
            }
            if (sameCount > 2) {
                nums[i] = Integer.MAX_VALUE;
            }
        }
        Arrays.sort(nums);

        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                k++;
            } else {
                break;
            }
        }

        return k;
    }
}

class LeetCode80_2 {
    public int removeDuplicates(int[] nums) {

        int k = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
}
