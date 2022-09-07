package boj;

/*
    A -> B는 A가 B를 신뢰하는 컴퓨터이므로 일방적인 방향이다, 이 떄 B를 해킹하면 A도 해킹할 수 있다.
    즉 특정 방향 그래프에서 한 컴퓨터에서 방문할 수 있는 컴퓨터로 진행할때마다
    방문한 컴퓨터의 카운트를 1씩 증가시키게 되면, 자연스럽게 가장 많은 카운트를 갖게 되는 경우가
    가장 많은 PC를 해킹할 수 있는 컴퓨터가 된다.

    DFS, BFS 모두 가능
*/

import java.util.*;
import java.io.*;

class Main {
    static List<Integer>[] graph;
    static int[] nodes;
    static boolean[] visited;
    static int n, m;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        nodes = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            visited[i] = true;
            nodes[i]++;
            DFS(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            max = Math.max(nodes[i], max);
        }

        for (int i = 1; i <= n; i++) {
            if (max == nodes[i]) {
                sb.append(i).append(" ");
            }
        }
        sb.append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void DFS(int start) {
        for (int node : graph[start]) {
            if (!visited[node]) {
                visited[node] = true;
                nodes[node]++;
                DFS(node);
            }
        }
    }
}