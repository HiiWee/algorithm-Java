package boj;

import java.io.*;
import java.util.*;

class Boj21937 {

    static int n, m;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a);
        }
        int number = Integer.parseInt(br.readLine());

        if (graph[number].isEmpty()) {
            bw.write("0");
        } else {
            bw.write(Integer.toString(dfs(number) - 1));
        }
        bw.flush();
        bw.close();
    }

    public static int dfs(int node) {
        visited[node] = true;

        if (graph[node].isEmpty()) {
            return 1;
        }

        int count = 1;
        for (int next : graph[node]) {
            if (!visited[next]) {
                count += dfs(next);
            }
        }

        return count;
    }
}
