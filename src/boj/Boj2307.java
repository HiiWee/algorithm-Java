package boj;

import java.io.*;
import java.util.*;

class Boj2307 {

    static class Node {
        int number, cost, edge;
        List<Integer> paths = new ArrayList<>();

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        public Node(int number, int cost, int edge) {
            this.number = number;
            this.cost = cost;
            this.edge = edge;
        }
    }

    static int n, m;
    static List<Node>[] graph;
    static int[] edges;
    static int minDist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        edges = new int[m];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[i] = c;
            graph[a].add(new Node(b, c, i));
            graph[b].add(new Node(a, c, i));
        }

        int maxLazyTime = Integer.MIN_VALUE;
        List<Integer> paths = dijkstra();
        for (int path : paths) {
            int[] dist = dijkstra(path);
            if (dist[n] == 110_000_000) {
                maxLazyTime = -1;
                break;
            } else {
                maxLazyTime = Math.max(Math.abs(minDist - dist[n]), maxLazyTime);
            }
        }

        bw.write(Integer.toString(maxLazyTime));
        bw.flush();
        bw.close();
    }


    public static List<Integer> dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 110_000_000);
        dist[1] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.number == n) {
                minDist = dist[n];
                return current.paths;
            }

            for (Node next : graph[current.number]) {
                if (dist[next.number] > dist[current.number] + next.cost) {
                    dist[next.number] = dist[current.number] + next.cost;
                    Node select = new Node(next.number, dist[next.number]);
                    if (current.number != 1) {
                        select.paths.addAll(current.paths);
                    }
                    select.paths.add(next.edge);
                    pq.offer(select);
                }
            }
        }


        return null;
    }

    public static int[] dijkstra(int edge) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 110_000_000);
        dist[1] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node next : graph[current.number]) {
                if (next.edge == edge) {
                    continue;
                }
                if (dist[next.number] > dist[current.number] + next.cost) {
                    dist[next.number] = dist[current.number] + next.cost;
                    pq.offer(new Node(next.number, dist[next.number]));
                }
            }
        }

        return dist;
    }
}
