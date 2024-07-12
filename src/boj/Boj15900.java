package boj;

import java.io.*;
import java.util.*;

class Boj15900 {

    static int n;
    static List<Integer>[] trees;
    static boolean[] visited;
    static int pathEdgeCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];

        trees = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            trees[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            trees[a].add(b);
            trees[b].add(a);
        }
        search(0, 0, 0);

        if (pathEdgeCount % 2 == 0) {
            bw.write("No");
        } else {
            bw.write("Yes");
        }
        bw.flush();
        bw.close();
    }

    public static void search(int startNode, int node, int count) {
        visited[node] = true;
        if (startNode != node && trees[node].size() == 1) {
            pathEdgeCount += count;
        }
        for (int next : trees[node]) {
            if (!visited[next]) {
                search(startNode, next, count + 1);
            }
        }
    }
}
