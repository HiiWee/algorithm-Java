package boj;

import java.io.*;
import java.util.*;

class Boj10211 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[n];
            dp[0] = arr[0];
            int max = arr[0];
            for (int i = 1; i < n; i++) {
                dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
                max = Math.max(dp[i], max);
            }
            answer.append(max).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
