package boj;

import java.io.*;
import java.util.*;

class Boj16973 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    static class Node {
        int r, c, count;

        Node (int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    private static int n, m, h, w, startR, startC, endR, endC;
    private static int answer = -1;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        List<Node> blocks = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    blocks.add(new Node(i, j, 0));
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        startR = Integer.parseInt(st.nextToken());
        startC = Integer.parseInt(st.nextToken());
        endR = Integer.parseInt(st.nextToken());
        endC = Integer.parseInt(st.nextToken());

        n -= h;
        m -= w;

        fillUnreachableArea(blocks);
        bfs();

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

    private static void fillUnreachableArea(final List<Node> blocks) {
        for (Node node : blocks) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    int nextR = node.r - i;
                    int nextC = node.c - j;

                    if (nextR < 1 || nextC < 1) {
                        continue;
                    }
                    map[nextR][nextC] = 1;
                }
            }
        }
    }

    public static void bfs() {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(startR, startC, 0));
        visited[startR][startC] = true;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.r == endR && curNode.c == endC) {
                answer = curNode.count;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 1 || nextR > n + 1 || nextC < 1 || nextC > m + 1 || map[nextR][nextC] == 1) {
                    continue;
                }
                if (!visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    que.offer(new Node(nextR, nextC, curNode.count + 1));
                }
            }
        }
    }
}
