package boj;

import java.io.*;
import java.util.*;

class Boj1149 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] rgb = new int[3][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rgb[0][i] = Integer.parseInt(st.nextToken());
            rgb[1][i] = Integer.parseInt(st.nextToken());
            rgb[2][i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[3][n];
        dp[0][0] = rgb[0][0];
        dp[1][0] = rgb[1][0];
        dp[2][0] = rgb[2][0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = rgb[0][i] + Math.min(dp[1][i - 1], dp[2][i - 1]);
            dp[1][i] = rgb[1][i] + Math.min(dp[0][i - 1], dp[2][i - 1]);
            dp[2][i] = rgb[2][i] + Math.min(dp[0][i - 1], dp[1][i - 1]);
        }

        int result = Math.min(Math.min(dp[0][n - 1], dp[1][n - 1]), dp[2][n - 1]);
        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}