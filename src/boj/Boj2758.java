package boj;

import java.io.*;
import java.util.*;

class Boj2758 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long[][] dp = new long[n + 1][m + 1];
            Arrays.fill(dp[0], 1L);

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j / 2];
                }
            }

            result.append(dp[n][m]).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}