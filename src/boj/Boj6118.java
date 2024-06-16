package boj;

import java.io.*;
import java.util.*;

class Boj6118 {

    static int n;
    static List<Integer>[] graph;
    static int[] distanceCount;
    static int maxDistance = Integer.MIN_VALUE;
    static int minNumber = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        distanceCount = new int[n];
        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);

        Queue<Integer> que = new ArrayDeque<>();
        que.offer(1);
        visited[1] = 0;

        while (!que.isEmpty()) {
            int current = que.poll();

            distanceCount[visited[current]]++;
            if (visited[current] > maxDistance) {
                maxDistance = visited[current];
                minNumber = current;
            } else if (visited[current] == maxDistance) {
                minNumber = Math.min(minNumber, current);
            }

            for (int next : graph[current]) {
                if (visited[next] != -1) {
                    continue;
                }

                visited[next] = visited[current] + 1;
                que.offer(next);
            }
        }

        bw.write(minNumber + " " + maxDistance + " " + distanceCount[maxDistance]);
        bw.flush();
        bw.close();
    }
}
