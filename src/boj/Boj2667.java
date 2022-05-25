package boj;

import java.io.*;
import java.util.*;

/*
 * 좌측 부터 우측, 위 부터 아래 순서로 만나는 단지를 번호를 증가하며 매긴다.
 * 이는 결국 2차원 배열의 index순서와 동일
 * */


// BFS 풀이 방법
class Boj2667_1 {
    static int[][] map;
    static boolean[][] visited;
    static final int[] row = {1, 0, -1, 0};
    static final int[] col = {0, 1, 0, -1};
    // 단지를 카운트
    static int complexCount = 0;
    // 각 단지의 세대 수
    static int houseCount = 0;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bfs(i, j, n);
            }
        }
        Collections.sort(list);

        bw.write(complexCount + "\n");
        for (int count : list) {
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void bfs(int r, int c, int n) {
        if (visited[r][c] || map[r][c] == 0) {
            return;
        }
        visited[r][c] = true;
        Queue<Integer> que = new LinkedList<>();
        que.add(r);
        que.add(c);
        houseCount++;
        while (!que.isEmpty()) {
            int o1 = que.poll();
            int o2 = que.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = o1 + row[i];
                int nextCol = o2 + col[i];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                    continue;
                }
                if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                    houseCount++;
                    visited[nextRow][nextCol] = true;
                    que.add(nextRow);
                    que.add(nextCol);
                }

            }
        }
        list.add(houseCount);
        houseCount = 0;
        complexCount++;
    }
}

// BFS 풀이방법
class Boj2667_2 {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] row = {1, 0, -1, 0};
    static int[] col = {0, 1, 0, -1};
    static int houseCount = 0;
    static int complexCount = 0;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                DFS(i, j, n);
                if (houseCount > 0) {
                    list.add(houseCount);
                    complexCount++;
                    houseCount = 0;
                }
            }
        }

        bw.write(complexCount + "\n");
        for (int count : list) {
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int r, int c, int n) {
        if (visited[r][c] || map[r][c] == 0) {
            return;
        }
        visited[r][c] = true;
        houseCount++;
        for (int i = 0; i < 4; i++) {
            int nextRow = row[i] + r;
            int nextCol = col[i] + c;

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                continue;
            }
            if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                DFS(nextRow, nextCol, n);
            }
        }
    }
}