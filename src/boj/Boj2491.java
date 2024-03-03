package boj;

import java.io.*;
import java.util.*;

class Boj2491 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[][] dp = new int[2][n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int maxLength = 1;
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] <= arr[i]) {
                dp[0][i] = dp[0][i - 1] + 1;
            } else {
                dp[0][i] = 1;
            }
            if (arr[i - 1] >= arr[i]) {
                dp[1][i] = dp[1][i - 1] + 1;
            } else {
                dp[1][i] = 1;
            }

            maxLength = Math.max(dp[1][i], Math.max(dp[0][i], maxLength));
        }

        bw.write(Integer.toString(maxLength));
        bw.flush();
        bw.close();
    }
}