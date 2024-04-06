package boj;

import java.io.*;
import java.util.*;

class Boj13565 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int n, m;
    static int[][] map;
    static Queue<int[]> que = new ArrayDeque<>();
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = line[j] - '0';
                if (i == 0 && map[i][j] == 0) {
                    que.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        percolate();

        boolean isPossible = false;
        for (int i = 0; i < m; i++) {
            if (visited[n - 1][i]) {
                isPossible = true;
                break;
            }
        }

        if (isPossible) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        bw.flush();
        bw.close();
    }

    public static void percolate() {
        while (!que.isEmpty()) {
            int[] current = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = current[0] + rows[i];
                int nextC = current[1] + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    continue;
                }

                if (!visited[nextR][nextC] && map[nextR][nextC] == 0) {
                    visited[nextR][nextC] = true;
                    que.offer(new int[]{nextR, nextC});
                }
            }
        }
    }
}
