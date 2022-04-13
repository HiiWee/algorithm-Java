package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Top-Down
class Boj11053_1 {
    static int N;
    static Integer[] dp;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N + 1];
        dp = new Integer[N + 1];
        String[] temp = bf.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i + 1] = Integer.parseInt(temp[i]);
        }

        for (int i = 1; i <= N; i++) {
            recur(i);
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        System.out.println(max);
    }
    public static Integer recur(int n) {
        if (dp[n] == null) {
            dp[n] = 1;
            for (int i = n; i > 0; i--) {
                if (arr[n] > arr[i]) {
                    dp[n] = Math.max(dp[n], recur(i) + 1);
                }
            }
        }
        return dp[n];
    }
}

// Bottom-Up
class Boj11053_2 {
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
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        System.out.println(max);
    }
}
