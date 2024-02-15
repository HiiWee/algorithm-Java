package boj;

import java.io.*;
import java.util.*;

class Boj1922 {
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
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(a, b, c));
        }
        //  정렬 O(MlogM) + 집합만들기 O(Nlog*N)
        int count = 0;
        int costSum = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union(edge.a, edge.b)) {
                count++;
                costSum += edge.cost;
            }
            if (count == n - 1) {
                break;
            }
        }

        bw.write(costSum + "");
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
            parents[b] = a;
        } else {
            parents[a] = b;
        }
        return true;
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}