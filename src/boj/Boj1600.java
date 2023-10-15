package boj;

import java.io.*;
import java.util.*;

class Boj1600 {

    private static final int[] horseRows = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] horseCols = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    static class Node {
        int r, c, horseMoveCount, moveCount;

        Node(int r, int c, int horseMoveCount, int moveCount) {
            this.r = r;
            this.c = c;
            this.horseMoveCount = horseMoveCount;
            this.moveCount = moveCount;
        }
    }
    private static int k, w, h;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(bfs(new Node(0, 0, 0,0)) + "");
        bw.flush();
        bw.close();
    }

    public static int bfs(Node startNode) {
        boolean[][][] visited = new boolean[h][w][k + 1];
        visited[0][0][0] = true;
        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.r == h - 1 && curNode.c == w - 1) {
                return curNode.moveCount;
            }
            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= h || nextC < 0 || nextC >= w) {
                    continue;
                }

                if (map[nextR][nextC] != 1 && !visited[nextR][nextC][curNode.horseMoveCount]) {
                    visited[nextR][nextC][curNode.horseMoveCount] = true;
                    que.offer(new Node(nextR, nextC, curNode.horseMoveCount, curNode.moveCount + 1));
                }
            }
            if (curNode.horseMoveCount < k) {
                for (int i = 0; i < 8; i++) {
                    int nextR = curNode.r + horseRows[i];
                    int nextC = curNode.c + horseCols[i];

                    if (nextR < 0 || nextR >= h || nextC < 0 || nextC >= w) {
                        continue;
                    }

                    if (map[nextR][nextC] != 1 && !visited[nextR][nextC][curNode.horseMoveCount + 1]) {
                        visited[nextR][nextC][curNode.horseMoveCount + 1] = true;
                        que.offer(new Node(nextR, nextC, curNode.horseMoveCount + 1, curNode.moveCount + 1));
                    }
                }
            }
        }
        return -1;
    }
}