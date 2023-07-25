package boj;

import java.io.*;
import java.util.*;

class Boj14567 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[n + 1];
        int[] inDegree = new int[n + 1];
        int[] dp = new int[n + 1];
        Queue<Integer> que = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            inDegree[end]++;
        }

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                dp[i] = 1;
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int current = que.poll();

            for (int next : graph[current]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    que.offer(next);
                    dp[next] = dp[current] + 1;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            result.append(dp[i]).append(" ");
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}
