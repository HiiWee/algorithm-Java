package boj;

import java.io.*;
import java.util.*;

class Boj1010 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] dp = new int[31][31];

        for (int i = 1; i <= 30; i++) {
            dp[1][i] = i;
        }

        for (int i = 2; i <= 30; i++) {
            for (int j = i; j <= 30; j++) {
                for (int k = 1; k < j; k++) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            sb.append(dp[n][m]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}