package boj;

import java.io.*;
import java.util.*;

class Boj2406 {

    static class Edge implements Comparable<Edge> {
        int a, b, cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }

    static int n, m;
    static Edge[] edges;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new Edge[(n - 1) * n / 2];
        parents = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        int uniqueCount = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (union(a, b)) {
                uniqueCount++;
            }
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i <= j) {
                    continue;
                }
                edges[index] = new Edge(i + 1, j + 1, cost);
                pq.offer(edges[index++]);
            }
        }

        index = 0;
        Edge[] usedEdges = new Edge[n - 2 - uniqueCount];
        int count = uniqueCount;
        int sum = 0;

        while (!pq.isEmpty() && count < n - 2) {
            Edge edge = pq.poll();

            if (edge.a == 1 || edge.b == 1) {
                continue;
            }
            if (union(edge.a, edge.b)) {
                count++;
                sum += edge.cost;
                usedEdges[index++] = edge;
            }
        }
        StringBuilder answer = new StringBuilder();
        answer.append(sum).append(" ").append(n - 2 - uniqueCount).append("\n");

        for (Edge edge : usedEdges) {
            answer.append(edge.a).append(" ").append(edge.b).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return false;
        }
        if (a > b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
        return true;
    }

    public static int find(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }
}
