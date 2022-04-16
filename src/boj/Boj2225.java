package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Bottom-Up
class Boj2225_1 {
    static final long MOD = 1000000000L;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bf.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int k = Integer.parseInt(temp[1]);
        long[][] dp = new long[k + 1][n + 1];

        // 기본값 초기화 작업
        for (int i = 1; i <= k; i++) {
            dp[i][0] = 1L;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        System.out.println(dp[k][n] % MOD);
    }
}

// Top-Down
class Boj2225_2 {
    static long MOD = 1000000000L;
    static Long[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        dp = new Long[k + 1][n + 1];

        System.out.println(recur(k, n) % MOD);
    }

    public static Long recur(int k, int n) {
        if (k == 0) {
            dp[k][n] = 0L;
            return dp[k][n];
        }
        else if (n == 0) {
            dp[k][n] = 1L;
            return dp[k][n];
        }
        if (dp[k][n] == null) {
            dp[k][n] = recur(k, n - 1) + recur(k - 1, n);
        }
        return dp[k][n] % MOD;
    }
}


