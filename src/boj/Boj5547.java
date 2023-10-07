package boj;

import java.io.*;
import java.util.*;

class Boj5547 {

    private static final int[] evenRows = {-1, -1, 0, 1, 1, 0};
    private static final int[] evenCols = {-1, 0, 1, 0, -1, -1};
    private static final int[] oddRows = {-1, -1, 0, 1, 1, 0};
    private static final int[] oddCols = {0, 1, 1, 1, 0, -1};

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static boolean[][] visited;
    private static int w, h, totalLength, adjustWallCount;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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

        visited = new boolean[h][w];
        // 1. 일단 맞닿아 있는 건물이 없는 면적 전부 구하기
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(new Node(i, j));
                }
            }
        }

        // 2. 건물에 쌓여있는 빈 공간은 빼주어야 한다.
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    adjustWallCount = 0;
                    if (blockCheckBfs(new Node(i, j))) {
                        totalLength -= adjustWallCount;
                    }
                }
            }
        }

        bw.write(totalLength + "");
        bw.flush();
        bw.close();
    }

    public static boolean blockCheckBfs(Node startNode) {
        boolean isBlock = true;

        visited[startNode.r][startNode.c] = true;
        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 6; i++) {
                int nextR;
                int nextC;
                if ((curNode.r + 1) % 2 == 0) {
                    nextR = evenRows[i] + curNode.r;
                    nextC = evenCols[i] + curNode.c;
                } else {
                    nextR = oddRows[i] + curNode.r;
                    nextC = oddCols[i] + curNode.c;
                }

                if (nextR < 0 || nextR >= h || nextC < 0 || nextC >= w) {
                    isBlock = false;
                    continue;
                }
                if (map[nextR][nextC] == 1) {
                    adjustWallCount++;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC] == 0) {
                    visited[nextR][nextC] = true;
                    que.offer(new Node(nextR, nextC));
                }
            }
        }
        return isBlock;
    }

    public static void bfs(Node startNode) {
        visited[startNode.r][startNode.c] = true;
        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 6; i++) {
                int nextR;
                int nextC;
                if ((curNode.r + 1) % 2 == 0) {
                    nextR = evenRows[i] + curNode.r;
                    nextC = evenCols[i] + curNode.c;
                } else {
                    nextR = oddRows[i] + curNode.r;
                    nextC = oddCols[i] + curNode.c;
                }

                if (nextR < 0 || nextR >= h || nextC < 0 || nextC >= w || map[nextR][nextC] == 0) {
                    totalLength++;
                    continue;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    visited[nextR][nextC] = true;
                    que.offer(new Node(nextR, nextC));
                }
            }
        }
    }
}