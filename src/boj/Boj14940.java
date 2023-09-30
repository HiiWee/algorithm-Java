package boj;

import java.io.*;
import java.util.*;

class Boj14940 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    static class Node {
        int r, c;
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int[][] distanceMap = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        Queue<Node> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(distanceMap[i], -1);

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    que.offer(new Node(i, j));
                    visited[i][j] = true;
                    distanceMap[i][j] = 0;
                } else if (map[i][j] == 0) {
                    distanceMap[i][j] = 0;
                }
            }
        }

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    continue;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    visited[nextR][nextC] = true;
                    distanceMap[nextR][nextC] = distanceMap[curNode.r][curNode.c] + 1;
                    que.offer(new Node(nextR, nextC));
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result.append(distanceMap[i][j]).append(" ");
            }
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}