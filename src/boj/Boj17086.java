package boj;

import java.io.*;
import java.util.*;

class Boj17086 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static final int[] rows = {-1, 1, 0, 0, 1, 1, -1, -1};
    static final int[] cols = {0, 0, -1, 1, -1, 1, -1 , 1};

    static int n, m;
    static int[][] map;
    static Queue<Node> que = new ArrayDeque<>();
    static int maxDistance = Integer.MIN_VALUE;

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
                if (map[i][j] == 1) {
                    que.offer(new Node(i, j));
                }
            }
        }

        moveSharks();

        bw.write(Integer.toString(maxDistance));
        bw.flush();
        bw.close();
    }

    public static void moveSharks() {
        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 8; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    continue;
                }
                if (map[nextR][nextC] == 0) {
                    map[nextR][nextC] = map[curNode.r][curNode.c] + 1;
                    maxDistance = Math.max(maxDistance, map[nextR][nextC] - 1);
                    que.offer(new Node(nextR, nextC));
                }
            }
        }
    }
}
