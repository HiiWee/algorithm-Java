package boj;

import java.io.*;
import java.util.*;

class Boj5557 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n][21];

        dp[0][numbers[0]] = 1L;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] == 0) {
                    continue;
                }
                if (j + numbers[i] <= 20) {
                    dp[i][j + numbers[i]] += dp[i - 1][j];
                }
                if (j - numbers[i] >= 0) {
                    dp[i][ j - numbers[i]] += dp[i - 1][j];
                }
            }
        }

        bw.write(dp[n - 2][numbers[n - 1]] + "");
        bw.flush();
        bw.close();
    }
}