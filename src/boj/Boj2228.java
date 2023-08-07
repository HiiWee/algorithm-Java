package boj;

import java.io.*;
import java.util.*;

class Boj2228 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        int[] sum = new int[n + 1];
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum[i] = sum[i - 1] + arr[i];
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Integer.MIN_VALUE / 2;
            }
        }

        dp[1][1] = sum[1];

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];

                int min = 0;
                if (j == 1) {
                    min = -1;
                }

                for (int k = i - 2; k >= min; k--) {
                    if (k < 0) {
                        dp[i][j] = Math.max(dp[i][j], sum[i]);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + sum[i] - sum[k + 1]);
                    }
                }
            }
        }

        bw.write(dp[n][m] + "");
        bw.flush();
        bw.close();
    }
}
