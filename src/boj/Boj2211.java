package boj;


import java.io.*;
import java.util.*;

class Boj2211 {

    static int n, m;
    static List<int[]>[] graph;
    static List<int[]> edges = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{a, b, c});
            graph[b].add(new int[]{b, a, c});
        }

        dijkstra();

        StringBuilder answer = new StringBuilder();
        answer.append(edges.size()).append("\n");
        for (int[] edge : edges) {
            answer.append(edge[0]).append(" ").append(edge[1]).append("\n");
        }
        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void dijkstra() {
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, 1_000_000);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        dist[1] = 0;
        visited[1] = true;
        pq.offer(new int[]{1, 1, 0}); // from, to, cost

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            if (!visited[current[1]]) {
                visited[current[1]] = true;
                edges.add(current);
            }

            for (int[] next : graph[current[1]]) {
                if (dist[next[1]] > current[2] + next[2]) {
                    dist[next[1]] = current[2] + next[2];
                    pq.offer(new int[]{current[1], next[1], dist[next[1]]});
                }
            }
        }
    }
}
