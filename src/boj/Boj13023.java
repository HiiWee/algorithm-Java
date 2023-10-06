package boj;

import java.io.*;
import java.util.*;

class Boj13023 {

    private static List<Integer>[] graphs;
    private static boolean[] visited;
    private static boolean hasRelationship;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graphs = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graphs[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graphs[s].add(e);
            graphs[e].add(s);
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            visited[i] = true;
            dfs(i, 0);
            if (hasRelationship) {
                break;
            }
        }

        if (hasRelationship) {
            bw.write("1");
        } else {
            bw.write("0");
        }
        bw.flush();
        bw.close();
    }

    public static void dfs(int node, int count) {
        if (count == 4) {
            hasRelationship = true;
            return;
        }
        for (int next : graphs[node]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, count + 1);
                visited[next] = false;
            }
        }
    }
}
