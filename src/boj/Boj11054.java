package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Bottom-Up
class Boj11054_1 {
    static int n;
    static int[] dp1;
    static int[] dp2;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        dp1 = new int[n + 1];
        dp2 = new int[n + 1];
        arr = new int[n + 1];
        String[] temp = bf.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(temp[i - 1]);
        }

        LIS();
        LDS();

        int max = dp1[1] + dp2[1];
        for (int i = 2; i <= n; i++) {
            max = Math.max(dp1[i] + dp2[i], max);
        }
        System.out.println(max - 1);
    }

    public static void LIS() {
        for (int i = 1; i <= n; i++) {
            dp1[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j] && dp1[i] < dp1[j] + 1) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }
    }

    public static void LDS() {
        for (int i = n; i > 0; i--) {
            dp2[i] = 1;
            for (int j = n; j > i; j--) {
                if (arr[i] > arr[j] && dp2[i] < dp2[j] + 1) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }
    }
}

// Top-Down
class Boj11054_2 {
    static int N;
    static Integer[] l_dp;
    static Integer[] r_dp;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        l_dp = new Integer[N + 1];
        r_dp = new Integer[N + 1];
        arr = new int[N + 1];
        String[] temp = bf.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(temp[i - 1]);
        }

        for (int i = 1; i <= N; i++) {
            LIS(i);
            LDS(i);
        }

        int max = l_dp[1] + r_dp[1];
        for (int i = 2; i <= N; i++) {
            max = Math.max(max, l_dp[i] + r_dp[i]);
        }
        System.out.println(max - 1);
    }

    public static Integer LIS(int n) {
        if (l_dp[n] == null) {
            l_dp[n] = 1;
            for (int i = 1; i < n; i++) {
                if (arr[i] < arr[n]) {
                    l_dp[n] = Math.max(l_dp[n], LIS(i) + 1);
                }
            }
        }
        return l_dp[n];
    }

    public static Integer LDS(int n) {
        if (r_dp[n] == null) {
            r_dp[n] = 1;
            for (int i = n + 1; i <= N; i++) {
                if (arr[i] < arr[n]) {
                    r_dp[n] = Math.max(r_dp[n], LDS(i) + 1);
                }
            }
        }
        return r_dp[n];
    }
}

