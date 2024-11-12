package leetcode;

// Time Complexity: O(N), Space Complexity: O(1)
class LeetCode189_1 {
    public void rotate(int[] nums, int k) {
        k %= nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }
}

// Time Complexity: O(N), Space Complexity: O(N)
class LeetCode189_2 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int[] copyNums = new int[n];

        int index = 0;
        for (int i = n - k; i < n - k + n; i++) {
            copyNums[index++] = nums[i % n];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = copyNums[i];
        }
    }
}
