package boj;

import java.io.*;
import java.util.*;

class Boj1915 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][m + 1];

        int max = 0;
        for (int i = 1; i <= n; i++) {
            String numbers = br.readLine();
            for (int j = 1; j <= m; j++) {
                int number = numbers.charAt(j - 1) - '0';
                if (i == 1 && j == 1) {
                    dp[i][j] = number;
                } else if (number == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        bw.write((max * max) + "");
        bw.flush();
        bw.close();
    }
}