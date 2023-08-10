package boj;

import java.io.*;
import java.util.*;

// Queue 활용한 위상정렬
class Boj1005_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder result = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            List<Integer>[] graphs = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graphs[i] = new ArrayList<>();
            }
            int[] times = new int[n + 1];
            int[] dp = new int[n + 1];
            int[] inDegree = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0 ; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                inDegree[end]++;
                graphs[start].add(end);
            }

            Queue<Integer> que = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) {
                    dp[i] = times[i];
                    que.offer(i);
                }
            }
            while (!que.isEmpty()) {
                int curNode = que.poll();
                for (int nextNode : graphs[curNode]) {
                    inDegree[nextNode]--;
                    dp[nextNode] = Math.max(dp[nextNode], dp[curNode] + times[nextNode]);

                    if (inDegree[nextNode] == 0) {
                        que.offer(nextNode);
                    }
                }
            }
            result.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}

// DFS 활용
class Boj1005_2 {
    private static int[] times;
    private static int[] dp;
    private static List<Integer>[] graphs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder result = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            graphs = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graphs[i] = new ArrayList<>();
            }
            times = new int[n + 1];
            dp = new int[n + 1];
            Arrays.fill(dp, -1);
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0 ; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                graphs[end].add(start);
            }

            result.append(recur(Integer.parseInt(br.readLine()))).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static int recur(int curNode) {
        if (dp[curNode] != -1) {
            return dp[curNode];
        }
        dp[curNode] = times[curNode];
        for (int nextNode : graphs[curNode]) {
            dp[curNode] = Math.max(dp[curNode], recur(nextNode) + times[curNode]);
        }
        return dp[curNode];
    }
}