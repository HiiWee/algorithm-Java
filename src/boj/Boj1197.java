package boj;

import java.io.*;
import java.util.*;

class Boj1197 {

    static class Edge implements Comparable<Edge> {
        int vA, vB;
        int weight;

        public Edge(int vA, int vB, int weight) {
            this.vA = vA;
            this.vB = vB;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge n) {
            return weight - n.weight;
        }
    }

    static int v, e;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        parents = new int[v + 1];
        for (int i = 0; i < v + 1; i++) {
            parents[i] = i;
        }

        PriorityQueue<Edge> que = new PriorityQueue<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            que.offer(new Edge(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        int count = v - 1;
        long sum = 0;
        while (count > 0) {
            Edge edge = que.poll();
            if (union(edge)) {
                count--;
                sum += edge.weight;
            }
        }

        bw.write(Long.toString(sum));
        bw.flush();
        bw.close();
    }

    public static boolean union(Edge edge) {
        int a = find(edge.vA);
        int b = find(edge.vB);
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