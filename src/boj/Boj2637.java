package boj;

import java.io.*;
import java.util.*;

class Boj2637 {

    static int n, m;
    static List<int[]>[] graph;
    static int[] inDegree;
    static int[] cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        inDegree = new int[n + 1];
        cost = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            graph[x].add(new int[]{y, k});
            inDegree[y]++;
        }


        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                que.offer(i);
                cost[i] = 1;
            }
        }

        while (!que.isEmpty()) {
            int current = que.poll();

            for (int[] next : graph[current]) {
                cost[next[0]] += cost[current] * next[1];
                inDegree[next[0]]--;
                if (inDegree[next[0]] == 0) {
                    que.offer(next[0]);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (graph[i].isEmpty()) {
                answer.append(i).append(" ").append(cost[i]).append("\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
