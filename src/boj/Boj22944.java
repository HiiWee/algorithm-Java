package boj;

import java.io.*;
import java.util.*;

class Boj22944 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    private static int n, h, d;
    private static char[][] map;
    private static int[][] visited;

    static class Node {
        int r, c, hp, umbrella, count;

        Node(int r, int c, int hp, int umbrella, int count) {
            this.r = r;
            this.c = c;
            this.hp = hp;
            this.umbrella = umbrella;
            this.count = count;
        }

        public void removeHp() {
            if (umbrella > 0) {
                umbrella--;
            } else {
                hp--;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new char[n][n];
        visited = new int[n][n];

        Queue<Node> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    que.offer(new Node(i, j, h, 0, 0));
                }
            }
        }
        visited[que.peek().r][que.peek().c] = que.peek().hp;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    continue;
                }

                if (map[nextR][nextC] == 'E') {
                    bw.write((curNode.count + 1) + "");
                    bw.flush();
                    bw.close();
                    return;
                }

                int nextUmbrella = curNode.umbrella;
                int nextHp = curNode.hp;
                if (map[nextR][nextC] == 'U') {
                    nextUmbrella = d;
                }
                Node nextNode = new Node(nextR, nextC, nextHp, nextUmbrella, curNode.count + 1);
                nextNode.removeHp();
                if (nextNode.hp == 0) {
                    continue;
                }
                if (visited[nextR][nextC] < nextNode.hp + nextNode.umbrella) {
                    visited[nextR][nextC] = nextNode.hp + nextNode.umbrella;
                    que.offer(nextNode);
                }
            }
        }

        bw.write("-1");
        bw.flush();
        bw.close();
    }
}