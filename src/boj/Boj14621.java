package boj;

import java.io.*;
import java.util.*;

class Boj14621 {

    static class Edge implements Comparable<Edge> {
        int a, b, cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }

    static int n, m;
    static int[] genders;
    static int[] parents;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        genders = new int[n];
        parents = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            if (st.nextToken().equals("M")) {
                genders[i] = 1;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(a, b, cost));
        }

        int answer = 0;
        int count = 0;
        while (!pq.isEmpty() && count < n - 1) {
            Edge edge = pq.poll();

            if (genders[edge.a] != genders[edge.b] && union(edge.a, edge.b)) {
                count++;
                answer += edge.cost;
            }
        }

        if (count == n - 1) {
            bw.write(Integer.toString(answer));
        } else {
            bw.write("-1");
        }
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
