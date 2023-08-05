package boj;

import java.io.*;
import java.util.*;

class Boj21941 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        int m = Integer.parseInt(br.readLine());
        String[] a = new String[m];
        int[] score = new int[m];

        int[] dp = new int[line.length() + 1];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i] = st.nextToken();
            score[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < line.length(); i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i] + 1);
            for (int j = 0; j < m; j++) {
                if (line.startsWith(a[j], i)) {
                    dp[i + a[j].length()] = Math.max(dp[i + a[j].length()], dp[i] + score[j]);
                }
            }
        }

        bw.write(dp[line.length()] + "");
        bw.flush();
        bw.close();
    }
}