package boj;

import java.io.*;
import java.util.*;

class Boj16234 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    private static int n, l, r;
    private static int gameLevel;
    private static int[][] map;
    private static int[][] visited;
    private static List<Node> nodes = new ArrayList<>();

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
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean isWorking = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == gameLevel && bfs(new Node(i, j))) {
                        isWorking = true;
                    }
                }
            }
            if (isWorking) {
                gameLevel++;
            } else {
                break;
            }
        }

        bw.write(gameLevel + "");
        bw.flush();
        bw.close();
    }

    public static boolean bfs(Node node) {
        visited[node.r][node.c] = gameLevel + 1;
        nodes.add(node);
        Queue<Node> que = new LinkedList<>();
        que.offer(node);

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    continue;
                }
                int difference = Math.abs(map[curNode.r][curNode.c] - map[nextR][nextC]);
                if (visited[nextR][nextC] != gameLevel + 1 && difference >= l && difference <= r) {
                    visited[nextR][nextC] = gameLevel + 1;
                    Node nextNode = new Node(nextR, nextC);
                    que.offer(nextNode);
                    nodes.add(nextNode);
                }
            }
        }

        if (nodes.size() == 1) {
            nodes.remove(node);
            visited[node.r][node.c] = gameLevel;
            return false;
        }
        updateMap();
        nodes.clear();
        return true;
    }

    public static void updateMap() {
        int total = 0;
        int count = nodes.size();

        for (Node node : nodes) {
            total += map[node.r][node.c];
        }
        int avg = total / count;
        for (Node node : nodes) {
            map[node.r][node.c] = avg;
        }
    }
}