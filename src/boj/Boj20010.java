package boj;

import java.io.*;
import java.util.*;

class Boj20010 {

    static class Node implements Comparable<Node> {
        int number, cost;

        Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return n.cost - cost;
        }
    }

    static class Edge {
        int a, b, cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static int n, k;
    static int[] parent;
    static List<Node>[] graphs;
    static int farNode;
    static int maxCost = 0;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        parent = new int[n];
        graphs = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graphs[i] = new ArrayList<>();
            parent[i] = i;
        }

        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(a, b, c);
            minHeap.offer(edge);
        }

        int minCost = createSet(minHeap);

        dfs(0, 0);
        visited = new boolean[n];
        dfs(farNode, 0);

        bw.write(minCost + "\n" + maxCost);
        bw.flush();
        bw.close();
    }

    public static void dfs(int node, int weight) {
        visited[node] = true;
        if (weight > maxCost) {
            farNode = node;
            maxCost = weight;
        }

        for (Node next : graphs[node]) {
            if (!visited[next.number]) {
                dfs(next.number, weight + next.cost);
            }
        }
    }

    public static int createSet(PriorityQueue<Edge> pq) {
        int count = 1;
        int cost = 0;
        while (count < n) {
            Edge edge = pq.poll();

            if (union(edge.a, edge.b)) {
                count++;
                cost += edge.cost;
                graphs[edge.a].add(new Node(edge.b, edge.cost));
                graphs[edge.b].add(new Node(edge.a, edge.cost));
            }
        }
        return cost;
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return false;
        }

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
        return true;
    }

    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }
}
