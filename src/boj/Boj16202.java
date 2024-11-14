package boj;

import java.io.*;
import java.util.*;

class Boj16202 {

    static class Edge {
        int a, b, cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static int n, m, k;
    static Edge[] edges;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, i + 1);
        }

        StringBuilder answer = new StringBuilder();
        for (int edgeCost = 1; edgeCost <= k; edgeCost++) {
            parents = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parents[i] = i;
            }

            int count = 0;
            int sum = 0;

            for (int i = edgeCost; i <= m && count < n - 1; i++) {
                Edge edge = edges[i - 1];

                if (union(edge.a, edge.b)) {
                    count++;
                    sum += edge.cost;
                }
            }

            if (count < n - 1) {
                answer.append("0 ");
            } else {
                answer.append(sum).append(" ");
            }
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

