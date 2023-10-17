package boj;

import java.io.*;
import java.util.*;

class Boj2206 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    static class Node {
        int r, c, count;
        int isBroken;

        Node(int r, int c, int count, int isBroken) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.isBroken = isBroken;
        }
    }

    private static int n, m;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }


        int result = bfs();
        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    public static int bfs() {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(0, 0, 1, 0));
        boolean[][][] visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.r == n - 1 && curNode.c == m - 1) {
                return curNode.count;
            }
            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    continue;
                }
                // 벽이 아닌 경우, 현재 방문 노드가 벽을 부수고 온건지, 안부수고 온건지를 파악하고
                // 해당 값에 맞게 방문처리 및 큐에 다음 노드 삽입
                if (map[nextR][nextC] == 0) {
                    if (!visited[nextR][nextC][curNode.isBroken]) {
                        visited[nextR][nextC][curNode.isBroken] = true;
                        que.offer(new Node(nextR, nextC, curNode.count + 1, curNode.isBroken));
                    }
                } else {
                    // 벽이라면 현재 노드가 벽을 부수고 온 노드인지 체크하고, 방문할 벽이 이미 부숴진 체크가 되어있는지 확인
                    if (curNode.isBroken == 0 && !visited[nextR][nextC][1]) {
                        visited[nextR][nextC][1] = true;
                        que.offer(new Node(nextR, nextC, curNode.count + 1, 1));
                    }

                }
            }
        }
        return -1;
    }
}