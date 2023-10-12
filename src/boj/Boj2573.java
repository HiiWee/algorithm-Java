package boj;

import java.io.*;
import java.util.*;

class Boj2573 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static int n, m;
    private static int[][] map;
    private static boolean[][] visited;

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

        boolean isAllMelted = false;
        int year = -1;
        int separateCount = 0;
        while (separateCount < 2) {
            separateCount = 0;
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && map[i][j] > 0) {
                        separateCount++;
                        bfs(new Node(i, j));
                    }
                }
            }
            if (separateCount == 0) {
                isAllMelted = true;
                break;
            }
            year++;
        }

        if (isAllMelted) {
            bw.write("0");
        } else {
            bw.write(year + "");
        }
        bw.flush();
        bw.close();
    }

    public static void bfs(Node startNode) {
        Queue<Node> que = new LinkedList<>();
        visited[startNode.r][startNode.c] = true;
        que.offer(startNode);

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            int zeroCount = 0;
            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];


                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    continue;
                }
                if (map[nextR][nextC] == 0 && !visited[nextR][nextC]) {
                    zeroCount++;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC] > 0) {
                    que.offer(new Node(nextR, nextC));
                    visited[nextR][nextC] = true;
                }
            }
            map[curNode.r][curNode.c] = Math.max(0, map[curNode.r][curNode.c] - zeroCount);
        }
    }
}