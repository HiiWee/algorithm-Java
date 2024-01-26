package boj;

import java.io.*;
import java.util.*;

class Boj1932 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n + 1][n + 1];

        int max = 0;
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                map[i][j] = Math.max(map[i - 1][j], map[i - 1][j - 1]) + Integer.parseInt(st.nextToken());
                if (i == n) {
                    max = Math.max(max, map[i][j]);
                }
            }
        }
        bw.write(max + "");
        bw.flush();
        bw.close();
    }
}
