package boj;

import java.io.*;
import java.util.*;

class Boj2636 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int row, col, cheeseCount;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheeseCount++;
                }
            }
        }
        int caseCount = 0;
        while (true) {
            int removeCount = bfs(new Node(0, 0));
            caseCount++;
            if (cheeseCount - removeCount == 0) {
                break;
            }
            cheeseCount -= removeCount;
        }
        bw.write(caseCount + "\n" + cheeseCount);
        bw.flush();
        bw.close();
    }

    public static int bfs(Node startNode) {
        int removeCount = 0;
        Queue<Node> que = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        visited[0][0] = true;
        que.offer(startNode);

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col) {
                    continue;
                }
                if (map[nextR][nextC] == 1 && map[curNode.r][curNode.c] == 0) {
                    map[nextR][nextC] = 0;
                    visited[nextR][nextC] = true;
                    removeCount++;
                }
                if (map[nextR][nextC] == 0 && !visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    que.offer(new Node(nextR, nextC));
                }
            }
        }

        return removeCount;
    }
}