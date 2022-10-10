package boj;

/*
    0-1배낭문제 DP 풀어보자!!
    dp의 열은 무게가 되고, 행은 선택할 수 있는 물건의 최대값이된다.
    특정 번호까지의 물건만 선택할 수 있고, 들 수 있는 무게를 증가시키면서 해당 조건에서의 들 수 있는 최대 물건을 구한다.
*/

import java.io.*;
import java.util.*;

class Boj12865 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] w = new int[n + 1];
        int[] v = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        // 행: 선택가능한 최대 물품 번호, 열: 채울수 있는 한계 무게
        int[][] dp = new int[n + 1][k + 1];
        int max = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], v[i] + dp[i - 1][j - w[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }
}