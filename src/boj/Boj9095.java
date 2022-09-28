package boj;

/*
    1: 1                                            1개
    2: 1+1, 2                                       2개
    3: 1+1+1, 1+2, 2+1, 3                           4개
    4: 7개
    5: 13
    6: 24
    7: 44
*/

import java.io.*;

class Boj9095 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        int[] dp = new int[12];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
