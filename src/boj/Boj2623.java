package boj;

import java.io.*;
import java.util.*;

class Boj2623 {

    static int n, m;
    static int[] inDegree;
    static List<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        inDegree = new int[n];
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken()) - 1;
            for (int j = 0; j < count - 1; j++) {
                int next = Integer.parseInt(st.nextToken()) - 1;
                inDegree[next]++;
                graph[prev].add(next);
                prev = next;
            }
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                que.offer(i);
            }
        }

        StringBuilder answer = new StringBuilder();
        while (!que.isEmpty()) {
            int current = que.poll();

            answer.append((current + 1)).append("\n");
            for (int next : graph[current]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    que.offer(next);
                }
            }
        }

        boolean isPossible = true;
        for (int value : inDegree) {
            if (value > 0) {
                isPossible = false;
            }
        }

        if (isPossible) {
            bw.write(answer.toString());
        } else {
            bw.write("0");
        }
        bw.flush();
        bw.close();
    }
}
