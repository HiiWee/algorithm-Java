package boj;

import java.io.*;

class Boj9655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        // 홀수 SK, 짝수CY
        int[] dp = new int[1001];
        dp[1] = 1;
        for (int i = 1; i < 1000; i++) {
            // i가 짝수일때는 다음 단계에 홀수 1을 대입
            if (dp[i + 1] == 0) {
                dp[i + 1] = 3 - dp[i];
            }
            // i가 짝수면 i + 3은 홀수이므로 홀수(1)대입 아니라면 짝수
            if (i + 3 <= 1000) {
                dp[i + 3] = 3 - dp[i];
            }
        }

        // 2 또는 1
        if (dp[n] % 2 == 0) {
            bw.write("CY");
        } else {
            bw.write("SK");
        }
        bw.flush();
        bw.close();
    }
}
