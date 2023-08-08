package boj;

import java.io.*;
import java.util.*;

class Boj1520 {
    private static int[] row = {-1, 0, 1, 0};
    private static int[] col = {0, 1, 0, -1};
    private static int n, m;
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(dfs(1, 1) + "");
        bw.flush();
        bw.close();
    }

    public static int dfs(int r, int c) {
        if (r == n && c == m) {
            return 1;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nextR = row[i] + r;
            int nextC = col[i] + c;

            if (nextR < 1 || nextR > n || nextC < 1 || nextC > m) {
                continue;
            }

            if (map[nextR][nextC] < map[r][c]) {
                dp[r][c] += dfs(nextR, nextC);
            }
        }
        return dp[r][c];
    }
}