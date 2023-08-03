package boj;

import java.io.*;
import java.util.*;

// 재귀 풀이
class Boj10942_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] dp = new int[n + 1][n + 1];
        int[] numbers = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        solve(dp, numbers, n);

        StringBuilder result = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            result.append(dp[s][e]).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void solve(int[][] dp, int[] numbers, int n) {
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
            if (numbers[i - 1] == numbers[i]) {
                dp[i - 1][i] = 1;
            }
        }

        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= n - i; j++) {
                if (numbers[j] == numbers[j + i] && dp[j + 1][j + i - 1] == 1) {
                    dp[j][j + i] = 1;
                }
            }
        }
    }
}

// bottom-up 풀이
class Boj10942_2 {

    private static int[] numbers;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        numbers = new int[n + 1];
        dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder result = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int count = recur(s, e);
            if (count == 0) {
                result.append("1\n");
            } else {
                result.append("0\n");
            }
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static int recur(int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        if (numbers[left] == numbers[right]) {
            dp[left][right] = recur(left + 1, right - 1);
        } else {
            dp[left][right] = Math.min(recur(left + 1, right) + 1, recur(left, right - 1) + 1);
        }

        return dp[left][right];
    }
}
