package boj;

import java.io.*;

class Boj1309 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        // 0: 빈칸, 1: 왼쪽, 2: 우측
        int[][] dp = new int[n][3];
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;

        for (int i = 1; i < n; i++) {
            // 현재 빈칸이라면 이전에 빈칸, 좌측, 우측 상관 없음
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;

            // 현재 좌측에 사자를 두면 이전에는 빈칸, 우측이어야 함
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            // 우측은 이전에 빈칸, 좌측
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }

        int result = (dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2]) % 9901;
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}