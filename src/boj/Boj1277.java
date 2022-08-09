package boj;

/*
    초기에 간선을 주어지지 않으므로 다익스트라를 돌면서 거리를 계산하고 최소 거리를 선택하여 최소경로를 만들어 가야함
    1. 노드의 좌표를 리스트에 저장
    2. 연결되어 있는 간선들은 따로 저장 후 후에 dist배열에서 연결된 부분의 가중치는 0으로 초기화 나머지는 INF'
    3. 다익스트라를 실행하며 꺼낸 노드와 가장 가까운 노드를 탐색해야 하므로 visited배열 필요
    4. 다익스트라를 돌며 가장 가까운 노드를 찾기 위해 getDistance 과정 필요
        거리가 m 초과면 패스
*/

import java.util.*;
import java.io.*;

class Boj1277 {
    static int n, w;
    static double m;
    static List<Node> nodes;
    static boolean[][] connected;

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        m = Double.parseDouble(br.readLine());
        nodes = new ArrayList<>();
        connected = new boolean[n + 1][n + 1];
        nodes.add(new Node(-1, -1)); // 더미 노드 추가
        // 발전소(노드) 받아오기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes.add(new Node(x, y));
        }
        // 이미 연결되어 있는 간선 체크 -> 양방향으로 체크하기
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            connected[n1][n2] = true;
            connected[n2][n1] = true;
        }

        bw.write(dijkstra() + "\n");
        bw.flush();
        bw.close();

    }

    public static long dijkstra() {
        boolean[] visited = new boolean[n + 1];
        double[] dist = new double[n + 1];
        // 연결된 간선(전선)이라면 가중치는 0, 아니라면 INF
        for (int i = 2; i <= n; i++) {
            if (connected[1][i]) {
                dist[i] = 0;
            } else {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            int curNode = 0;
            double minDist = Integer.MAX_VALUE;

            for (int j = 1; j <= n; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    curNode = j;
                    minDist = dist[j];
                }
            }
            visited[curNode] = true;

            for (int j = 1; j <= n; j++) {
                if (curNode == j) {
                    continue;
                }
                double distance = getDistance(curNode, j);
                if (distance > m) {
                    continue;
                }
                if (dist[j] > dist[curNode] + distance) {
                    dist[j] = dist[curNode] + distance;
                }
            }
        }
        return (long) (dist[n] * 1000);
    }

    public static double getDistance(int n1, int n2) {
        if (connected[n1][n2]) {
            return 0;
        }
        Node node1 = nodes.get(n1);
        Node node2 = nodes.get(n2);
        int x1 = node1.x;
        int y1 = node1.y;
        int x2 = node2.x;
        int y2 = node2.y;

        double dist = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
        return Math.sqrt(dist);
    }
}
