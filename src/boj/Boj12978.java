package boj;

import java.io.*;
import java.util.*;

class Boj12978 {

    static int n;
    static List<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
        // 1을 루트로 일단 시작 -> 최소 간선을 갖으므로 무조건 사이클이없는 그래프 즉 트리임
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        dfs(1);

        bw.write(Integer.toString(Math.min(dp[1][0], dp[1][1])));
        bw.flush();
        bw.close();
    }

    public static void dfs(int number) {
        visited[number] = true;
        dp[number][0] = 0;
        dp[number][1] = 1;

        for (int next : graph[number]) {
            if (!visited[next]) {
                dfs(next);
                dp[number][0] += dp[next][1];
                dp[number][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}
