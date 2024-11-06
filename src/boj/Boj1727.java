package boj;

import java.io.*;
import java.util.*;

class Boj1727 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] man = new int[n];
        int[] woman = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            man[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            woman[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(man);
        Arrays.sort(woman);

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    dp[i + 1][j + 1] = Math.abs(man[i] - woman[j]) + dp[i][j];
                } else if (i < j) {
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j], dp[i][j] + Math.abs(man[i] - woman[j]));
                } else {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + Math.abs(man[i] - woman[j]));
                }
            }
        }

        bw.write(Integer.toString(dp[n][m]));
        bw.flush();
        bw.close();
    }
}
