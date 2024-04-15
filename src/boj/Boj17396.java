package boj;

import java.io.*;
import java.util.*;

class Boj17396 {

    static class Node implements Comparable<Node> {
        int number;
        long cost;

        public Node(int number, long cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(cost, node.cost);
        }
    }

    static final long INF = 9_999_900_001L;
    static int n, m;
    static List<Node>[] graph;
    static Set<Integer> excludeNode = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            if (i < n - 1 && Integer.parseInt(st.nextToken()) == 1) {
                excludeNode.add(i);
            }
            graph[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (!excludeNode.contains(a) && !excludeNode.contains(b)) {
                graph[a].add(new Node(b, c));
                graph[b].add(new Node(a, c));
            }
        }

        long result = search();
        if (result == INF) {
            bw.write("-1");
        } else {
            bw.write(Long.toString(result));
        }
        bw.flush();
        bw.close();
    }

    public static long search() {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[n - 1] = 0;

        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(n - 1, 0));

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (Node nextNode : graph[curNode.number]) {
                if (dist[nextNode.number] > dist[curNode.number] + nextNode.cost) {
                    dist[nextNode.number] = dist[curNode.number] + nextNode.cost;
                    que.offer(new Node(nextNode.number, dist[nextNode.number]));
                }
            }
        }

        return dist[0];
    }
}
