package boj;

import java.io.*;
import java.util.*;

class Boj18290 {

    static int n, m, k;
    static int[][] map;
    static boolean[][] visited;
    static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        search(1, 0, 0, 0);

        bw.write(Integer.toString(maxSum));
        bw.flush();
        bw.close();
    }

    public static void search(int r, int c, int count, int sum) {
        if (count == k) {
            maxSum = Math.max(sum, maxSum);
            return;
        }

        c++;
        if (c == m + 1) {
            c = 1;
            r++;
        }

        for (int i = r; i <= n; i++) {
            for (int j = c; j <= m; j++) {
                if (visited[i][j] || isNearVisit(i, j)) {
                    continue;
                }
                visited[i][j] = true;
                search(i, j, count + 1, sum + map[i][j]);
                visited[i][j] = false;
            }
            c = 1;
        }
    }

    public static boolean isNearVisit(int r, int c) {
        return visited[r][c - 1] || visited[r - 1][c];
    }
}
