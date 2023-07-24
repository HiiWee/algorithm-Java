package boj;

import java.io.*;
import java.util.*;

class Boj2624 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] coins = new int[k + 1];
        int[] count = new int[k + 1];

        for (int i = 1; i <= k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
            count[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[k + 1][t + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= count[i]; j++) {
                for (int l = 0; l <= t; l++) {
                    int value = j * coins[i] + l;
                    if (value > t) {
                        break;
                    }
                    dp[i][value] += dp[i - 1][l];
                }
            }

        }

        bw.write(dp[k][t] + "");
        bw.flush();
        bw.close();
    }
}
