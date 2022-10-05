package boj;

import java.util.*;
import java.io.*;

class Boj21317_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n + 1][2];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine());

        if (n > 1) {
            dp[2] = arr[1][0];
        }
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.min(arr[i - 1][0] + dp[i - 1], arr[i - 2][1] + dp[i - 2]);
        }
        int[] dp2 = new int[n + 1];
        int min = dp[n];
        for (int i = 1; i <= n - 3; i++) {
            if (dp[i + 3] <= dp[i] + k) {
                continue;
            }
            for (int j = 1; j <= n; j++) {
                dp2[j] = dp[j];
            }
            dp2[i + 3] = dp[i] + k;
            for (int j = i + 4; j <= n; j++) {
                dp2[j] = Math.min(Math.min(dp[j], dp2[j - 1] + arr[j - 1][0]), dp2[j - 2] + arr[j - 2][1]);
            }
            min = Math.min(dp2[n], min);
        }


        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
}

// TODO: DFS로 풀어보기!!
