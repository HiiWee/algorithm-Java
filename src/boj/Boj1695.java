package boj;

import java.io.*;
import java.util.*;

// LCS 이용한 풀이
class Boj1695_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (numbers[i] == numbers[n - (j - 1)]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        bw.write((n - dp[n][n]) + "");
        bw.flush();
        bw.close();
    }
}

// 재귀 이용한 풀이
class Boj1695_2 {

    private static int[] numbers;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        bw.write(recur(0, n - 1) + "");
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
