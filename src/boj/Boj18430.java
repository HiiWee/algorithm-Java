package boj;

import java.io.*;
import java.util.*;

class Boj18430 {

    static final int[][] rows = {{0, 0, 1}, {0, 0, -1}, {0, 1, 1}, {0, 0, 1}};
    static final int[][] cols = {{0, 1, 1}, {0, 1, 1}, {0, 0, 1}, {1, 0, 0}};
    static int n, m;
    static int[][] map;
    static int maxSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        search(0, 0, 0, new boolean[n][m]);

        bw.write(maxSum + "");
        bw.flush();
        bw.close();
    }

    public static void search(int r, int c, int sum, boolean[][] visited) {
        if (c == m) {
            c = 0;
            r++;
        }
        if (r == n) {
            // 최대 합 갱신
            maxSum = Math.max(maxSum, sum);
            return;
        }

        // 이미 방문했다면 다음 칸으로 넘어가도록 함
        if (visited[r][c]) {
            search(r, c + 1, sum, visited);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int[] row = rows[i];
            int[] col = cols[i];
            int nr1 = row[0] + r;
            int nc1 = col[0] + c;
            int nr2 = row[1] + r;
            int nc2 = col[1] + c;
            int nr3 = row[2] + r;
            int nc3 = col[2] + c;

            if (!check(nr1, nc1, visited) || !check(nr2, nc2, visited) || !check(nr3, nc3, visited)) {
                continue;
            }
            visited[nr1][nc1] = true;
            visited[nr2][nc2] = true;
            visited[nr3][nc3] = true;
            search(r, c + 1, sum + map[nr1][nc1] + map[nr2][nc2] * 2 + map[nr3][nc3], visited);
            visited[nr1][nc1] = false;
            visited[nr2][nc2] = false;
            visited[nr3][nc3] = false;
        }
        // 현재 칸에서 선택하지 않고 다음칸부터 고르는 경우도 고려
        search(r, c + 1, sum, visited);
    }

    public static boolean check(int r, int c, boolean[][] visited) {
        return r >= 0 && r < n && c >= 0 && c < m && !visited[r][c];
    }
}