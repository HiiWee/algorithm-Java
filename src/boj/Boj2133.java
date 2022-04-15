package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Bottom-Up
class Boj2133_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            dp[i] = 3 * dp[i - 2];
            for (int j = 4; j < i + 2; j += 2) {
                dp[i] += dp[j - 4] * 2;
            }
        }
        System.out.println(dp[n]);
    }
}

// Top-Down
class Boj2133_2 {
    static Integer[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }
        dp = new Integer[n + 1];
        dp[0] = 1;
        dp[2] = 3;
        System.out.println(recur(n));

    }
    public static Integer recur(int n) {
        if (dp[n] == null) {
            dp[n] = recur(n - 2) * 3;
            for (int i = 4; i < n + 2; i += 2) {
                dp[n] += 2 * recur(n - i);
            }
        }
        return dp[n];
    }
}