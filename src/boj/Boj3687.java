package boj;

import java.io.*;
import java.util.*;

class Boj3687 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long[] dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;

        String[] minValue = {"1", "7", "4", "2", "0", "8"};

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String num = dp[i - j] + minValue[j - 2];
                dp[i] = Math.min(Long.parseLong(num), dp[i]);
            }
        }
        StringBuilder result = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            result.append(dp[n]).append(" ");
            if (n % 2 == 0) {
                for (int i = 0; i < n / 2; i++) {
                    result.append("1");
                }
            } else {
                result.append("7");
                for (int i = 0; i < n / 2 - 1; i++) {
                    result.append("1");
                }
            }
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}