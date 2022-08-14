package boj;

/*
    먼저 각 지점을 잇는 도로 및 웜홀을 입력으로 받고 벨만-포드 알고리즘을 통해 N-1번 반복하여 최단경로를 업데이트 한다.
    다시 벨만-포드 알고리즘을 1번 돌게될때 최단경로가 새로이 업데이트 되는경우가 발생하면 음의 사이클이 존재한다는 의미이므로
    시간이 줄어들면서 출발 위치로 돌아오는 것이 가능, YES
    만약 업데이트 되지 않고 종료된다면 NO가 된다.

    여기서 주의해야 할 것은 단지 음의 사이클이 존재하는지 아닌지 파악하는 문제이므로
    모든 점을 출발점으로 두고 시작하는것이 아닌, 임의의 출발점을 두고 시작하면 dist[from] == INF일때는 검사하지 않는
    조건을 제외시켜주어야 한다. 왜냐하면 그래프가 서로 단절되어 있는 경우 출발점이 속하지 않은 부분에서 사이클이 존재하고,
    위의 조건이 포함되어 있는경우는 해당 부분을 애초에 돌지 않기 때문이다.

    dist[from] != INF일때만 검사하고 싶다면, 모든 점을 출발점으로 두고 풀면 된다. (시간초과 발생확률 있음)
*/

import java.util.*;
import java.io.*;

class Boj1865 {
    static final int INF = Integer.MAX_VALUE;
    static int tc, n, m, w;
    static long[] dist;
    static List<Node>[] graph;
    static StringBuilder sb = new StringBuilder();

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
        tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            initGraph();
            for (int i = 0; i < m + w; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                if (i < m) {
                    graph[s].add(new Node(e, t));
                    graph[e].add(new Node(s, t));
                } else {
                    graph[s].add(new Node(e, -t));
                }
            }
            if (bellmanFord()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void initGraph() {
        graph = new ArrayList[n + 1];
        dist = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }
    }

    public static boolean bellmanFord() {
        dist[1] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Node nextNode : graph[j]) {
                    if (dist[nextNode.idx] > dist[j] + nextNode.cost) {
                        dist[nextNode.idx] = dist[j] + nextNode.cost;
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