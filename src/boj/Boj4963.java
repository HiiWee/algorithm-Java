package boj;

import java.io.*;
import java.util.*;

// BFS 풀이
class Boj4963_1 {
    static int[][] map;
    static boolean[][] visited;
    static int[] row = {1, 0, -1, 0, 1, -1, -1, 1};
    static int[] col = {0, 1, 0, -1, 1, 1, -1, -1};
    static int islandCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                bw.flush();
                bw.close();
                return;
            }
            map = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    BFS(i, j, h, w);
                }
            }
            bw.write(islandCount + "\n");
            islandCount = 0;
        }
    }

    public static void BFS(int r, int c, int h, int w) {
        if (visited[r][c] || map[r][c] == 0) {
            return;
        }
        visited[r][c] = true;
        Queue<Integer> que = new LinkedList<>();
        que.add(r);
        que.add(c);

        while (!que.isEmpty()) {
            int o1 = que.poll();
            int o2 = que.poll();
            for (int i = 0; i < 8; i++) {
                int nextRow = o1 + row[i];
                int nextCol = o2 + col[i];

                if (nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) {
                    continue;
                }
                if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                    que.add(nextRow);
                    que.add(nextCol);
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        islandCount++;
    }
}

// DFS 풀이
class Boj4963_2 {
    static int[][] map;
    static boolean[][] visited;
    static int[] row = {1, 1, 1, 0, -1, -1, -1, 0};
    static int[] col = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int islandCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                bw.flush();
                bw.close();
                return;
            }
            map = new int[h][w];
            visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        DFS(i, j, h, w);
                        islandCount++;
                    }
                }
            }
            bw.write(islandCount + "\n");
            islandCount = 0;

        }
    }

    public static void DFS(int r, int c, int h, int w) {
        visited[r][c] = true;
        for (int i = 0; i < 8; i++) {
            int nextRow = r + row[i];
            int nextCol = c + col[i];

            if (nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) {
                continue;
            }
            if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                DFS(nextRow, nextCol, h, w);
            }
        }
    }
}