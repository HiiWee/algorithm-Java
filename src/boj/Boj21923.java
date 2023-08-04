package boj;

import java.io.*;
import java.util.*;

class Boj21923 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] numbers = new int[n + 1][m + 1];
        int[][] upDp = new int[n + 1][m + 1];
        int[][] downDp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                numbers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        upDp[n][1] = numbers[n][1];
        for (int i = n; i > 0; i--) {
            if (i != n) {
                upDp[i][1] = numbers[i][1] + upDp[i + 1][1];
            }
            for (int j = 2; j <= m; j++) {
                if (i == n) {
                    upDp[i][j] = numbers[i][j] + upDp[i][j - 1];
                } else {
                    upDp[i][j] = numbers[i][j] + Math.max(upDp[i + 1][j], upDp[i][j - 1]);
                }
            }
        }

        downDp[n][m] = numbers[n][m];
        for (int i = n; i > 0; i--) {
            if (i != n) {
                downDp[i][m] = numbers[i][m] + downDp[i + 1][m];
            }
            for (int j = m - 1; j > 0; j--) {
                if (i == n) {
                    downDp[i][j] = numbers[i][j] + downDp[i][j + 1];
                } else {
                    downDp[i][j] = numbers[i][j] + Math.max(downDp[i + 1][j], downDp[i][j + 1]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                max = Math.max(upDp[i][j] + downDp[i][j], max);
            }
        }

        bw.write(max + "");
        bw.flush();
        bw.close();
    }
}