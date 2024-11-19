package boj;

import java.io.*;
import java.util.*;

class Boj11562 {

    static int n, m, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], 1_000_000);
            graph[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (b == 1) {
                graph[u][v] = 0;
                graph[v][u] = 0;
            } else {
                graph[u][v] = 0;
                graph[v][u] = 1;
            }
        }

        for (int middle = 1; middle <= n; middle++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    graph[from][to] = Math.min(graph[from][middle] + graph[middle][to], graph[from][to]);
                }
            }
        }

        k = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            answer.append(graph[a][b]).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
