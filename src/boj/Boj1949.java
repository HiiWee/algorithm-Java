package boj;

import java.io.*;
import java.util.*;

class Boj1949 {

    static int[] numbers;
    static List<Integer>[] tree;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        numbers = new int[n + 1];
        tree = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        search(1, new boolean[n + 1]);

        bw.write(Integer.toString(Math.max(dp[1][0], dp[1][1])));
        bw.flush();
        bw.close();
    }

    public static void search(int node, boolean[] visited) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = numbers[node];

        for (int next : tree[node]) {
            if (!visited[next]) {
                search(next, visited);
                dp[node][0] += Math.max(dp[next][1], dp[next][0]);
                dp[node][1] += dp[next][0];
            }
        }
    }
}
