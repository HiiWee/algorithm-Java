package boj;

import java.io.*;

class Boj1003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int[][] dp = new int[2][41];
        dp[0][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= 40; i++) {
            dp[0][i] = dp[0][i - 1] + dp[0][i - 2];
            dp[1][i] = dp[1][i - 1] + dp[1][i - 2];
        }
        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int number = Integer.parseInt(br.readLine());
            answer.append(dp[0][number])
                    .append(" ")
                    .append(dp[1][number])
                    .append("\n");
        }
        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}