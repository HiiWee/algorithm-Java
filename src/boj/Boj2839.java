package boj;

/*
    DP를 이용해 설탕봉지의 최소 개수를 구해보자
*/

import java.io.*;

class Boj2839 {
    static final int INF = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[5001];

        for (int i = 1; i <= n; i++) {
            dp[i] = INF;
        }
        dp[3] = 1;

        for (int i = 5; i <= n; i++) {
            dp[i] = Math.min(dp[i], Math.min(dp[i - 5], dp[i - 3]) + 1);
        }

        if (dp[n] == INF) {
            bw.write("-1");
        } else {
            bw.write(dp[n] + "\n");
        }
        bw.flush();
        bw.close();
    }
}