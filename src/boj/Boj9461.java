package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Bottom-Up
class Boj9461_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(bf.readLine());

        for (int i = 0; i < cnt; i++) {
            int n = Integer.parseInt(bf.readLine());
            long[] dp = new long[n + 1];
            dp[1] = 1;
            if (n > 1) dp[2] = 1;
            if (n > 2) dp[3] = 1;
            if (n > 3) dp[4] = 2;

            for (int j = 5; j <= n; j++) {
                dp[j] = dp[j - 1] + dp[j - 5];
            }

            System.out.println(dp[n]);
        }
    }
}

// Top-Down
class Boj9461_2 {
    static int N;
    static Long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(bf.readLine());
        for (int i = 0; i < cnt; i++) {
            N = Integer.parseInt(bf.readLine());
            dp = new Long[N + 1];
            dp[0] = 0L;
            dp[1] = 1L;
            if (N > 1) dp[2] = 1L;
            if (N > 2) dp[3] = 1L;
            if (N > 3) dp[4] = 2L;

            for (int j = 5; j <= N; j++) {
                recur(j);
            }
            System.out.println(dp[N]);
        }
    }
    public static Long recur(int n) {
        if (dp[n] == null) {
            dp[n] = recur(n - 1) + recur(n - 5);
        }
        return dp[n];
    }
}