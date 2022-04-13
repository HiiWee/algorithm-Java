package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Top-Down
class Boj11055_1 {
    static int n;
    static Integer[] dp;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        dp = new Integer[n + 1];
        arr = new int[n + 1];
        String[] temp = bf.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.parseInt(temp[i]);
        }

        for (int i = 1; i <= n; i++) {
            lis(i);
        }

        int max = dp[1];
        for (int i = 2; i <= n; i++) {
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }

    public static Integer lis(int n) {
        if (dp[n] == null) {
            dp[n] = arr[n];
            for (int i = n - 1; i > 0; i--) {
                if (arr[n] > arr[i]) {
                    dp[n] = Math.max(dp[n], lis(i) + arr[n]);
                }
            }
        }
        return dp[n];
    }
}

// Bottom-Up
class Main {
    static int N;
    static int[] dp;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new int[N + 1];
        arr = new int[N + 1];
        String[] temp = bf.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i + 1] = Integer.parseInt(temp[i]);
        }

        for (int i = 1; i <= N; i++) {
            dp[i] = arr[i];
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + arr[i]) {
                    dp[i] = dp[j] + arr[i];
                }
            }
        }

        int max = dp[1];
        for (int i = 2; i <= N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
