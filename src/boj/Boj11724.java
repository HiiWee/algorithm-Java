package boj;

import java.io.*;
import java.util.*;

// 인접 행렬을 이용한 DFS
class Boj11724_1 {
    static int[][] graph;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;
            graph[y][x] = 1;
        }
        int count = 0;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                DFS(i);
                count++;
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();

    }

    public static void DFS(int root) {
        visited[root] = true;

        for (int i = 1; i < graph.length; i++) {
            if (!visited[i] && graph[root][i] == 1) {
                DFS(i);
            }
        }
    }
}

// 인접리스트를 이용한 DFS
class Boj11724_2 {
    static List<Integer>[] adjacent;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        adjacent = new LinkedList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            adjacent[i] = new LinkedList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (!adjacent[x].contains(y)) {
                adjacent[x].add(y);
            }
            if (!adjacent[y].contains(x)) {
                adjacent[y].add(x);
            }
        }
        int count = 0;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                DFS(i);
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void DFS(int root) {
        visited[root] = true;
        for (int node : adjacent[root]) {
            if (!visited[node]) {
                DFS(node);
            }
        }
    }
}

// 인접행렬을 이용한 BFS
class Boj11724_3 {
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;
            graph[y][x] = 1;
        }
        int count = 0;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                BFS(i);
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS(int root) {
        visited[root] = true;
        Queue<Integer> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int node = que.poll();
            for (int i = 1; i < graph.length; i++) {
                if (!visited[i] && graph[node][i] == 1) {
                    visited[i] = true;
                    que.add(i);
                }
            }
        }
    }
}

// BFS 인접리스트
class Boj11724_4 {
    static List<Integer>[] adjacent;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        adjacent = new LinkedList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            adjacent[i] = new LinkedList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adjacent[x].add(y);
            adjacent[y].add(x);
        }
        int count = 0;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                BFS(i);
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS(int root) {
        visited[root] = true;
        Queue<Integer> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int node = que.poll();

            for (int n : adjacent[node]) {
                if (!visited[n]) {
                    visited[n] = true;
                    que.add(n);
                }
            }
        }
    }
}

