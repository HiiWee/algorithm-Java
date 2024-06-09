package boj;

import java.io.*;
import java.util.*;

class Boj1743 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int n, m, k;
    static int[][] map;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    max = Math.max(dfs(i, j), max);
                }
            }
        }

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }

    public static int dfs(int r, int c) {
        int count = 1;
        map[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            int nextR = rows[i] + r;
            int nextC = cols[i] + c;

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                continue;
            }

            if (map[nextR][nextC] == 1) {
                count += dfs(nextR, nextC);
            }
        }

        return count;
    }
}
