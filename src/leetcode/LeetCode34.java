package leetcode;

class LeetCode34 {
    public int[] searchRange(int[] nums, int target) {

        int[] answer = new int[2];
        if (nums.length == 0) {
            answer[0] = -1;
            answer[1] = -1;
            return answer;
        }

        answer[0] = lowerBoundSearch(nums, target);
        answer[1] = upperBoundSearch(nums, target);

        return answer;
    }

    public int lowerBoundSearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;

            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if (nums[l] != target) {
            return -1;
        }
        return l;
    }

    public int upperBoundSearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            int mid = (l + r) / 2;

            if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if (l == 0) {
            return -1;
        }
        if (nums[l - 1] != target) {
            return -1;
        }
        return l - 1;
    }
}
