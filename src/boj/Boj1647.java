package boj;

import java.io.*;
import java.util.*;

class Boj1647 {

    static class Edge implements Comparable<Edge> {
        int a, b, c;

        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge e) {
            return c - e.c;
        }
    }

    static int n, m;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(a, b, c));
        }

        int count = 0;
        int sum = 0;
        int max = 0;
        while (count < n - 1) {
            Edge edge = pq.poll();

            if (union(edge.a, edge.b)) {
                max = Math.max(edge.c, max);
                sum += edge.c;
                count++;
            }
        }

        bw.write(Integer.toString(sum - max));
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
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}
