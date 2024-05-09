package boj;

import java.io.*;
import java.util.*;

class Boj1058 {

    static final int INF = 100_000;

    static int n;
    static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == 'Y') {
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }

        for (int middle = 0; middle < n; middle++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    graph[from][to] = Math.min(graph[from][to], graph[from][middle] + graph[middle][to]);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int currentCount = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && graph[i][j] <= 2) {
                    currentCount++;
                }
            }
            max = Math.max(currentCount, max);
        }

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }
}
