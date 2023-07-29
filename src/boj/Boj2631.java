package boj;

import java.io.*;
import java.util.*;

class Boj2631 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] childs = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            childs[i] = Integer.parseInt(br.readLine());
        }
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (childs[j] < childs[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }

        bw.write((n - max) + "");
        bw.flush();
        bw.close();
    }
}