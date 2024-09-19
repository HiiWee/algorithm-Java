package leetcode;

class LeetCode238 {

    int n;

    public int[] productExceptSelf(int[] nums) {
        n = nums.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];

        int value = 1;
        for (int i = 0; i < nums.length; i++) {
            value *= nums[i];
            prefix[i] = value;
        }
        value = 1;
        int index = 0;
        for (int i = n - 1; i >= 0; i--) {
            value *= nums[i];
            suffix[index++] = value;
        }

        int[] answer = new int[n];
        answer[0] = suffix[n - 2];
        answer[n - 1] = prefix[n - 2];
        int currentIndex = 1;
        int prefixIndex = 0;

        for (int i = n - 3; i >= 0; i--) {
            answer[currentIndex++] = prefix[prefixIndex++] * suffix[i];
        }

        return answer;
    }
}
