package leetcode;

class LeetCode134 {
    int n;
    public int canCompleteCircuit(int[] gas, int[] cost) {
        n = gas.length;

        int totalSum = 0;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            totalSum += gas[i] - cost[i];
            sum += gas[i] - cost[i];

            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
        }

        if (totalSum < 0) {
            return -1;
        }

        return start;
    }
}
