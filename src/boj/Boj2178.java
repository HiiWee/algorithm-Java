package boj;

import java.io.*;
import java.util.*;

class Boj2178 {
    static int[][] map;
    static boolean[][] visited;
    static int[] row = {1, 0, -1, 0};
    static int[] col = {0, 1, 0, -1};
    static StringTokenizer st;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        BFS();

        bw.write(map[n - 1][m - 1] + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS() {
        Queue<String> que = new LinkedList<>();
        visited[0][0] = true;
        que.add(0 + " " + 0);

        while (!que.isEmpty()) {
            st = new StringTokenizer(que.poll());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for (int i = 0; i < 4; i++) {
                int nextRow = r + row[i];
                int nextCol = c + col[i];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                    continue;
                }
                if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                    map[nextRow][nextCol] = map[r][c] + 1;
                    visited[nextRow][nextCol] = true;
                    que.add(nextRow + " " + nextCol);
                }
            }
        }
    }
}