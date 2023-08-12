package boj;

import java.io.*;

// bottomUp 풀이
class Boj1823_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            dp[i][i] = numbers[i] * n;
        }

        for (int gap = 1; gap <= n; gap++) {
            for (int i = 1; gap + i <= n; i++) {
                int j = i + gap;
                dp[i][j] = Math.max(dp[i][j - 1] + (n - gap) * numbers[j] , dp[i + 1][j] + (n - gap) * numbers[i]);
            }
        }

        bw.write(dp[1][n] + "");
        bw.flush();
        bw.close();
    }
}

// topDown 풀이
class Boj1823_2 {
    private static int[] numbers;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        numbers = new int[n + 1];
        dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            dp[i][i] = numbers[i] * n;
        }

        int result = recur(1, n, 1);

        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    public static int recur(int start, int end, int num) {
        if (start > end) {
            return 0;
        }
        if (dp[start][end] != 0) {
            return dp[start][end];
        }
        dp[start][end] = Math.max(recur(start + 1, end, num + 1) + numbers[start] * num, recur(start, end - 1, num + 1) + numbers[end] * num);
        return dp[start][end];
    }
}