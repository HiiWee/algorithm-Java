package boj;

import java.io.*;
import java.util.*;

class Boj20007 {

    static int n, m, x, y;
    static List<int[]>[] graph;

    public static void main(String[] args ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
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

        int[] dist = dijkstra();
        Arrays.sort(dist);

        int count = 0;
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (dist[i] * 2 > x) {
                bw.write("-1");
                bw.flush();
                bw.close();
                return;
            }
            if (sum + dist[i] * 2 <= x) {
                sum += dist[i] * 2;
            } else {
                count++;
                sum = dist[i] * 2;
            }
        }
        if (sum > 0) {
            count++;
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static int[] dijkstra() {
        int[] dist = new int[n];
        Arrays.fill(dist, 100_000_000);
        dist[y] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{y, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            for (int[] next : graph[current[0]]) {

                if (dist[next[0]] > current[1] + next[1]) {
                    dist[next[0]] = current[1] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        return dist;
    }
}
