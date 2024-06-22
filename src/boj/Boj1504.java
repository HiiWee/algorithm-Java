package boj;

import java.io.*;
import java.util.*;

class Boj1504 {

    static class Node implements Comparable<Node> {
        int number, cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        public int compareTo(Node n) {
            return cost - n.cost;
        }
    }

    static final int INF = 500_000_000;

    static int n, m;
    static List<Node>[] graph;
    static int u, v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken()) - 1;
        v = Integer.parseInt(st.nextToken()) - 1;

        int[] distU = dijkstra(u);
        int[] distV = dijkstra(v);
        int uToV = distU[v];

        int answer = Math.min(
                uToV + distV[0] + distU[n - 1],
                Math.min(uToV + distU[0] + distV[n - 1],
                        Math.min(2 * uToV + distV[0] + distU[n - 1], 2 * uToV + distU[0] + distV[n - 1])));

        if (answer >= INF) {
            bw.write("-1");
        } else {
            bw.write(Integer.toString(answer));
        }
        bw.flush();
        bw.close();
    }

    public static int[] dijkstra(int startNode) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[startNode] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startNode, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.cost > dist[node.number]) {
                continue;
            }

            for (Node next : graph[node.number]) {

                if (dist[next.number] > dist[node.number] + next.cost) {
                    dist[next.number] = dist[node.number] + next.cost;
                    pq.offer(new Node(next.number, dist[next.number]));
                }
            }
        }
        return dist;
    }
}
