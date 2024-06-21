package boj;

import java.io.*;
import java.util.*;

class Boj1774 {

    static class Edge implements Comparable<Edge> {
        int a, b;
        double cost;

        public Edge(int a, int b, double cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        public int compareTo(Edge edge) {
            return Double.compare(cost, edge.cost);
        }
    }

    static int n, m;
    static int[] parents;
    static int[][] pos;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pos = new int[n][2];
        parents = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pos[i][0] = a;
            pos[i][1] = b;
            parents[i] = i;
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (union(a, b)) {
                count++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = calculateDistance(i, j);
                pq.offer(new Edge(i, j, distance));
            }
        }

        double answer = 0;
        while (count < n - 1) {
            Edge edge = pq.poll();
            if (union(edge.a, edge.b)) {
                count++;
                answer += edge.cost;
            }
        }

        bw.write(String.format("%.2f", answer));
        bw.flush();
        bw.close();
    }

    public static double calculateDistance(int a, int b) {
        return Math.sqrt(Math.pow(pos[a][0] - pos[b][0], 2) + Math.pow(pos[a][1] - pos[b][1], 2));
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

