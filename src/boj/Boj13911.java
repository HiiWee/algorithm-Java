package boj;

import java.io.*;
import java.util.*;

class Boj13911 {

    static class Node {
        int number;
        long cost;

        public Node(int number, long cost) {
            this.number = number;
            this.cost = cost;
        }
    }

    static int v, e;
    static List<Node>[] graph;
    static int m, x, s, y;
    static Set<Integer> mc = new HashSet<>();
    static Set<Integer> sb = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new ArrayList[v + 3];
        for (int i = 0; i <= v + 2; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            mc.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) {
            sb.add(Integer.parseInt(st.nextToken()));
        }

        for (int mcNode : mc) {
            graph[v + 1].add(new Node(mcNode, 0));
        }
        for (int sbNode : sb) {
            graph[v + 2].add(new Node(sbNode, 0));
        }
        long[] mcDist = dijkstra(v + 1);
        long[] sbDist = dijkstra(v + 2);


        long answer = Long.MAX_VALUE;
        for (int i = 1; i <= v; i++) {
            if (mc.contains(i) || sb.contains(i)) {
                continue;
            }
            if (mcDist[i] > x || sbDist[i] > y) {
                continue;
            }
            answer = Math.min(answer, mcDist[i] + sbDist[i]);
        }

        if (answer == Long.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(Long.toString(answer));
        }
        bw.flush();
        bw.close();
    }

    public static long[] dijkstra(int startNode) {
        long[] dist = new long[v + 3];
        Arrays.fill(dist, 100_000_000 * 10000L + 1);
        dist[startNode] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.cost));
        pq.offer(new Node(startNode, 0));

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > dist[current.number]) {
                continue;
            }

            for (Node next : graph[current.number]) {
                if (dist[next.number] > dist[current.number] + next.cost) {
                    dist[next.number] = dist[current.number] + next.cost;
                    pq.offer(new Node(next.number, dist[next.number]));
                }
            }
        }

        return dist;
    }
}
