package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Bottom-Up
class Boj11722_1 {
    static int n;
    static int[] dp;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        dp = new int[n + 1];
        arr = new int[n + 1];
        String[] temp = bf.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.parseInt(temp[i]);
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[i] < arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int max = dp[1];
        for (int i = 2; i <= n; i++) {
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}

// Top-Down
class Boj11722_2 {
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
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    public static Integer lis(int n) {
        if (dp[n] == null) {
            dp[n] = 1;
            for (int i = n - 1; i > 0; i--) {
                if (arr[i] > arr[n]) {
                    dp[n] = Math.max(dp[n], lis(i) + 1);
                }
            }
        }
        return dp[n];
    }
}
