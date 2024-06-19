package boj;

import java.io.*;
import java.util.*;

class Boj16398 {

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

    static int n;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        parents = new int[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            parents[i] = i;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    pq.offer(new Edge(i, j, Integer.parseInt(st.nextToken())));
                } else {
                    st.nextToken();
                }
            }
        }

        long cost = 0;
        int count = 0;

        while (count < n - 1) {
            Edge edge = pq.poll();

            if (union(edge.a, edge.b)) {
                cost += edge.cost;
                count++;
            }
        }

        bw.write(Long.toString(cost));
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
