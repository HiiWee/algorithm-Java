package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Bottom-Up
class Boj1912_1 {
    static int N;
    static int[] dp;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new int[N + 1];
        arr = new int[N + 1];
        String[] temp = bf.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(temp[i - 1]);
        }
        dp[1] = arr[1];
        for (int i = 2; i <= N; i++) {
            dp[i] = arr[i];
            dp[i] = Math.max(Math.max(arr[i], arr[i] + dp[i - 1]), arr[i] + arr[i - 1]);
        }

        int max = dp[1];
        for (int i = 2; i <= N; i++) {
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}

// Top-Down
class Boj1912_2 {
    static int N;
    static Integer[] dp;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new Integer[N + 1];
        arr = new int[N + 1];
        String[] temp = bf.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(temp[i - 1]);
        }
        dp[0] = 0;
        dp[1] = arr[1];

        for (int i = 2; i <= N; i++) {
            recur(i);
        }

        int max = dp[1];
        for (int i = 2; i <= N; i++) {
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }

    public static Integer recur(int n) {
        if (dp[n] == null) {
            dp[n] = arr[n];
            dp[n] = Math.max(Math.max(arr[n], arr[n] + recur(n - 1)), arr[n] + arr[n - 1]);
        }
        return dp[n];
    }
}