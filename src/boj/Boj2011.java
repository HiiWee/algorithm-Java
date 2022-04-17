package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Bottom-Up
class Boj2011_1 {
    static int MOD = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String nums = bf.readLine();
        int[] dp = new int[nums.length() + 1];

        if (nums.length() == 0 || nums.charAt(0) - '0' == 0) {
            System.out.println(0);
            return;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= nums.length(); i++) {
            int num = nums.charAt(i - 1) - '0';
            if (num > 0 && num < 10) {
                dp[i] += dp[i - 1] % MOD;
            }
            num = (nums.charAt(i - 2) - '0') * 10 + nums.charAt(i - 1) - '0';
            if (num > 9 && num < 27) {
                dp[i] += dp[i - 2] % MOD;
            }
        }
        System.out.println(dp[nums.length()] % MOD);
    }
}

// Top-Down
class Boj2011_2 {
    static int MOD = 1000000;
    static Integer[] dp;
    static String nums;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        nums = bf.readLine();
        dp = new Integer[nums.length() + 1];

        if (nums.length() == 0 || nums.charAt(0) - '0' == 0) {
            System.out.println(0);
            return;
        }
        dp[0] = 1;
        dp[1] = 1;


        System.out.println(recur(nums.length()) % MOD);
    }

    public static Integer recur(int n) {
        if (dp[n] == null) {
            int temp = 0;
            int num = nums.charAt(n - 1) - '0';
            if (num > 0 && num < 10) {
                temp += recur(n - 1) % MOD;
            }
            num += (nums.charAt(n - 2) - '0') * 10;
            if (num > 9 && num < 27) {
                temp += recur(n - 2) % MOD;
            }
            dp[n] = temp;
        }
        return dp[n];
    }
}