package boj;

import java.io.*;
import java.util.*;

class Boj18427 {
    private static final int OFFSET = 10_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][h + 1];
        int[][] coins = new int[n + 1][m];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            while (st.hasMoreTokens()) {
                coins[i][j++] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= h; j++) {
                for (int k = 0; k < m; k++) {
                    if (coins[i][k] == 0) {
                        break;
                    }
                    if (coins[i][k] <= j) {
                        dp[i][j] += dp[i - 1][j - coins[i][k]] % OFFSET;
                    }
                }
                dp[i][j] += dp[i - 1][j] % OFFSET;
            }
        }

        bw.write((dp[n][h] % OFFSET) + "");
        bw.flush();
        bw.close();
    }
}