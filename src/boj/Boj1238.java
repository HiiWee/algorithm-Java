package boj;

/*
    먼저 각 번호에서 파티하는 마을로 가는 최단 경로들을 각각 구해줘야 한다.
    만약 마을이 1~4이고 파티를 2에서 연다면
    start -> end = (1, 3, 4) -> 2로 가는 최단 경로들을 각각 구해줘야 한다.

    이후 다시 end -> start로 가는데 이때는 2가 시작점이고 각 모든 경로에 대해 최단경로를 구한다.
    이후 위에서 구한 파티장으로 가는 경로값과 집으로 돌아가는 값을 더하여 최대값을 구하면 된다.
*/

import java.util.*;
import java.io.*;

class Boj1238 {
    static int n, m, x;
    static List<Node>[] backGraph;
    static List<Node>[] goGraph;
    static int[] result;

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
        x = Integer.parseInt(st.nextToken());
        backGraph = new ArrayList[n + 1];
        goGraph = new ArrayList[n + 1];
        int[] back = new int[n + 1];
        int[] go = new int[n + 1];
        Arrays.fill(back, Integer.MAX_VALUE);
        Arrays.fill(go, Integer.MAX_VALUE);
        result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            backGraph[i] = new ArrayList<>();
            goGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            backGraph[s].add(new Node(e, c));
            goGraph[e].add(new Node(s, c));
        }

        dijkstra(back, backGraph);
        dijkstra(go, goGraph);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(result[i], max);
        }
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void dijkstra(int[] dist, List<Node>[] graph) {
        dist[x] = 0;
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        que.offer(new Node(x, 0));

        while (!que.isEmpty()) {
            Node currentNode = que.poll();

            if (currentNode.cost > dist[currentNode.idx]) {
                continue;
            }

            for (Node nextNode : graph[currentNode.idx]) {
                if (dist[nextNode.idx] > currentNode.cost + nextNode.cost) {
                    dist[nextNode.idx] = currentNode.cost + nextNode.cost;
                    que.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            result[i] += dist[i];
        }
    }
}