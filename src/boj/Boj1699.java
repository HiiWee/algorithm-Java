package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Bottom-Up
class Boj1699_1 {
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        dp = new int[n + 1];

        // 가장 긴 제곱수들의 합으로 초기화 (1^2)
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }

        for (int i = 2; i <= n; i++) {
            // i보다 작으면서 제곱수인(1^2, 2^2 . . .) 제곱이 i보다 작으면(i = 13일 경우 -> j = 1, 4, 9가 있다.(16은 더 큼))
            // i - j*j번째의 dp의 제곱수들의 합 + 1 과 (13 - 3^2라면 5의 최소 제곱수 2에 3^2이 추가됐으므로 2 + 1 = 3이다.)
            // 현재 제곱수(가장 많은 경우는 모두 1^2으로 이루어진것)를 비교한다
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        System.out.println(dp[n]);
    }
}

// Top-Down
class Main {
    static Integer[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        dp = new Integer[n + 1];

        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            recur(i);
        }

        System.out.println(dp[n]);
    }

    public static Integer recur(int n) {
        if (dp[n] == null) {
            dp[n] = n;
            for (int i = 1; i * i <= n; i++) {
                dp[n] = Math.min(dp[n], recur(n - i * i) + 1);
            }
        }
        return dp[n];
    }
}