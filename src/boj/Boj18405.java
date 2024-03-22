package boj;

import java.io.*;
import java.util.*;

class Boj18405 {

    static class Node implements Comparable<Node> {
        int r, c, value;

        public Node(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }

        @Override
        public int compareTo(Node node) {
            return value - node.value;
        }
    }

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int n, k, s, x, y;
    static int[][] map;
    static PriorityQueue<Node> que = new PriorityQueue<>();
    static PriorityQueue<Node> nextQue = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    que.offer(new Node(i, j, map[i][j]));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;

        bfs();

        bw.write(Integer.toString(map[x][y]));
        bw.flush();
        bw.close();
    }

    public static void bfs() {
        while (s-- > 0) {
            while (!que.isEmpty()) {
                Node curNode = que.poll();

                for (int i = 0; i < 4; i++) {
                    int nextR = curNode.r + rows[i];
                    int nextC = curNode.c + cols[i];

                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                        continue;
                    }
                    if (map[nextR][nextC] == 0) {
                        map[nextR][nextC] = map[curNode.r][curNode.c];
                        nextQue.offer(new Node(nextR, nextC, map[nextR][nextC]));
                    }
                }
            }
            if (!nextQue.isEmpty()) {
                que.addAll(nextQue);
                nextQue.clear();
            }
        }
    }
}
