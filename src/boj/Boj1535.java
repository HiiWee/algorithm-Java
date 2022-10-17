package boj;

/*
    dp[i][j]: i번째 사람까지 인사할 수 있고, j체력까지일때 얻을 수 있는 최대 기쁨
*/

import java.io.*;
import java.util.*;

class Boj1535 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] health = new int[n + 1];
        int[] joy = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            health[i] = Integer.parseInt(st1.nextToken());
            joy[i] = Integer.parseInt(st2.nextToken());
        }

        int[][] dp = new int[n + 1][101];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 99; j++) {
                if (j >= health[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], joy[i] + dp[i - 1][j - health[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        bw.write(dp[n][99] + "\n");
        bw.flush();
        bw.close();
    }
}