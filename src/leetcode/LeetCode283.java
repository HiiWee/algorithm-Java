package leetcode;

class LeetCode283 {
    public void moveZeroes(int[] nums) {
        int fillIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i == fillIndex) {
                    fillIndex++;
                    continue;
                }
                nums[fillIndex++] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
