package boj;

import java.io.*;
import java.util.*;

class Boj5972 {

    static int n, m;
    static List<int[]>[] graph;

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
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        bw.write(Integer.toString(search()));
        bw.flush();
        bw.close();
    }

    public static int search() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 100_000_000);

        dist[1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            for (int[] next : graph[current[0]]) {

                if (dist[next[0]] > dist[current[0]] + next[1]) {
                    dist[next[0]] = dist[current[0]] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        return dist[n];
    }
}
