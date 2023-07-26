package boj;

import java.io.*;
import java.util.*;

class Boj17485 {

    private static final int INF = 100_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n + 1][m + 2];
        int[][][] dp = new int[3][n + 1][m + 2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[0][i], INF);
            Arrays.fill(dp[1][i], INF);
            Arrays.fill(dp[2][i], INF);
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= m; i++) {
            dp[0][1][i] = map[1][i];
            dp[1][1][i] = map[1][i];
            dp[2][1][i] = map[1][i];
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[0][i][j] = Math.min(dp[1][i - 1][j - 1], dp[2][i - 1][j - 1]) + map[i][j];
                dp[1][i][j] = Math.min(dp[0][i - 1][j], dp[2][i - 1][j]) + map[i][j];
                dp[2][i][j] = Math.min(dp[0][i - 1][j + 1], dp[1][i - 1][j + 1]) + map[i][j];
            }
        }

        int result = INF;
        for (int i = 1; i <= m; i++) {
            result = Math.min(result, dp[0][n][i]);
            result = Math.min(result, dp[1][n][i]);
            result = Math.min(result, dp[2][n][i]);
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}