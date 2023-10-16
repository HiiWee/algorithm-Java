package boj;

import java.io.*;
import java.util.*;

class Boj16954 {

    private static int[] rows = {-1, -1, -1, 0, 1, 1, 1, 0, 0};
    private static int[] cols = {-1, 0, 1, 1, 1, 0, -1, -1, 0};

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map = new char[10][10];
        Arrays.fill(map[0], '.');
        for (int i = 1; i <= 8; i++) {
            String line = br.readLine();
            for (int j = 1; j <= 8; j++) {
                map[i][j] = line.charAt(j - 1);
            }
        }

        int result = bfs();

        bw.write(result + "");
        bw.flush();
        bw.close();

    }

    private static int bfs() {
        Queue<Node> que = new LinkedList<>();
        boolean[][] visited;
        que.offer(new Node(8, 1));

        while (!que.isEmpty()) {

            visited = new boolean[9][9];
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Node curNode = que.poll();

                if (map[curNode.r][curNode.c] == '#') {
                    continue;
                }
                if (curNode.r == 1 && curNode.c == 8) {
                    return 1;
                }
                for (int j = 0; j < 9; j++) {
                    int nextR = curNode.r + rows[j];
                    int nextC = curNode.c + cols[j];

                    if (nextR < 1 || nextR >= 9 || nextC < 1 || nextC >= 9) {
                        continue;
                    }
                    if (map[nextR][nextC] == '.' && !visited[nextR][nextC]) {
                        visited[nextR][nextC] = true;
                        que.offer(new Node(nextR, nextC));
                    }
                }
            }
            moveWall();
        }
        return 0;
    }

    public static void moveWall() {
        for (int i = 9; i >= 1; i--) {
            for (int j = 1; j <= 8; j++) {
                map[i][j] = map[i - 1][j];
            }
        }
    }
}