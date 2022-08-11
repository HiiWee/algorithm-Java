package boj;

/*
    벨만 포드 알고리즘을 이용해 풀어보자
*/
import java.util.*;
import java.io.*;

class Boj11657 {
    static List<Node>[] graph;
    static int n, m;
    static long[] dist;
    static final int INF = Integer.MAX_VALUE;
    static class Node {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        dist = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, c));
        }

        if (bellmanFord(1)) {
            bw.write("-1\n");
        } else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == INF) {
                    dist[i] = -1;
                }
                bw.write(dist[i] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static boolean bellmanFord(int start) {
        dist[start] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Node node : graph[j]) {
                    if (dist[j] != INF && dist[node.idx] > dist[j] + node.cost) {
                        dist[node.idx] = dist[j] + node.cost;

                        // n-1까지 돌면 전부 업데이트 되지만, 음수 사이클이 존재하면 음의 무한대에 수렴하므로 n번째 돌아도 업데이트함
                        if (i == n) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}