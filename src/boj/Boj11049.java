package boj;

import java.io.*;
import java.util.*;

class Boj11049_1 {
    private static int[][] matrix;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        matrix = new int[n][2];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        bw.write(recur(0, n - 1) + "");
        bw.flush();
        bw.close();
    }

    public static int recur(int pos, int end) {
        if (end == pos) {
            return 0;
        }
        if (dp[pos][end] != Integer.MAX_VALUE) {
            return dp[pos][end];
        }
        for (int i = pos; i < end; i++) {
            dp[pos][end] = Math.min(dp[pos][end], recur(pos, i) + recur(i + 1, end) + (matrix[pos][0] * matrix[i][1] * matrix[end][1]));
        }
        return dp[pos][end];
    }

}

class Boj11049_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = new int[n][2];
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = matrix[i][0] * matrix[i][1] * matrix[i + 1][1];
        }

        for (int gap = 2; gap < n; gap++) {
            for (int i = 0; i + gap < n; i++) {
                int j = i + gap;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1]);
                }
            }
        }

        bw.write(dp[0][n - 1] + "");
        bw.flush();
        bw.close();
    }
}