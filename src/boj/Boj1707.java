package boj;

import java.io.*;
import java.util.*;

/*
* 최대 노드 수가 20000개이므로 인접행렬을 만들면 20000 * 20000 * int(4Byte)는
* 메모리를 초과하게 된다. 따라서 풀 수 없음
* */

// 인접리스트를 이용한 DFS
class Boj1707_1 {
    static List<Integer>[] adjacent;
    static int[] colors;
    static boolean isBipartite;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            isBipartite = true;
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            adjacent = new LinkedList[V + 1];
            colors = new int[V + 1];
            for (int j = 1; j < adjacent.length; j++) {
                adjacent[j] = new LinkedList<>();
            }
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                if (!adjacent[u].contains(v)) {
                    adjacent[u].add(v);
                }
                if (!adjacent[v].contains(u)) {
                    adjacent[v].add(u);
                }
            }
            for (int j = 1; j <= V; j++) {
                if (colors[j] == 0) {
                    DFS(j, 1);
                }
            }

            if (isBipartite) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();

    }

    public static void DFS(int root, int color) {
        colors[root] = color % 2 + 1;
        for (int node : adjacent[root]) {
            // 방문하지 않은 노드
            if (colors[node] == 0) {
                DFS(node, color + 1);
                // 방문하여 이미 색칠된 노드
            } else if (colors[node] > 0) {
                // 현재 노드와 색깔이 동일하다면 이분그래프 아님
                if (colors[node] == colors[root]) {
                    isBipartite = false;
                    return;
                }
            }
        }
    }
}

// 인접리스트를 이용한 BFS
class Boj1707_2 {
    static List<Integer>[] adjacent;
    static int[] colors;
    static boolean isBipartite;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            isBipartite = true;
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            adjacent = new LinkedList[V + 1];
            colors = new int[V + 1];
            for (int j = 1; j < adjacent.length; j++) {
                adjacent[j] = new LinkedList<>();
            }
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adjacent[u].add(v);
                adjacent[v].add(u);
            }
            for (int j = 1; j <= V; j++) {
                if (colors[j] == 0) {
                    BFS(j, 1);
                }
            }

            if (isBipartite) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();

    }

    public static void BFS(int root, int color) {
        Queue<Integer> que = new LinkedList<>();
        que.add(root);
        colors[root] = color % 2 + 1;
        while (!que.isEmpty()) {
            int node = que.poll();
            for (int n : adjacent[node]) {
                // 방문하지 않은노드
                if (colors[n] == 0) {
                    que.add(n);
                    // 방문하지 않은 노드는 인접노드와 다른 색으로 칠함
                    colors[n] = (colors[node] + 1) % 2;
                // 방문한 노드이면서 인접노드와 동일한 색상이면 NO 이므로 바로 종료
                } else if (colors[n] == colors[node]) {
                    isBipartite = false;
                    return;
                }
            }
        }
    }
}