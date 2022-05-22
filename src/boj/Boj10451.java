package boj;

import java.io.*;
import java.util.*;

// 인접리스트 DFS
class Boj10451_1 {
    static List<Integer>[] adjacent;
    static boolean[] visited;
    static boolean isCycle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            adjacent = new LinkedList[n + 1];
            visited = new boolean[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                adjacent[i] = new LinkedList<>();
                int num = Integer.parseInt(st.nextToken());
                adjacent[i].add(num);
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                isCycle = false;
                if (!visited[i]) {
                    DFS(i, i);
                }
                if (isCycle) {
                    count++;
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int root, int cycle) {
        visited[root] = true;
        for (int node : adjacent[root]) {
            if (!visited[node]) {
                DFS(node, cycle);
            } else if (cycle == node) {
                isCycle = true;
            }
        }
    }
}

// 인접리스트를 배열로 이용한 DFS
class Boj10451_2 {
    static int[] graph;
    static boolean[] visited;
    static boolean isCycle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            graph = new int[n + 1];
            visited = new boolean[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i] = num;
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                isCycle = false;
                if (!visited[i]) {
                    DFS(i, i);
                }
                if (isCycle) {
                    count++;
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int root, int cycle) {
        visited[root] = true;
        int node = graph[root];
        if (!visited[node]) {
            DFS(node, cycle);
        } else if (cycle == node) {
            isCycle = true;
        }
    }
}

// 인접리스트 BFS
class Boj10451_3 {
    static List<Integer>[] adjacent;
    static boolean[] visited;
    static boolean isCycle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            adjacent = new LinkedList[n + 1];
            visited = new boolean[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                adjacent[i] = new LinkedList<>();
                int num = Integer.parseInt(st.nextToken());
                adjacent[i].add(num);
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                isCycle = false;
                if (!visited[i]) {
                    BFS(i, i);
                }
                if (isCycle) {
                    count++;
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void BFS(int root, int cycle) {
        Queue<Integer> que = new LinkedList<>();
        que.add(root);
        visited[root] = true;
        while (!que.isEmpty()) {
            int node = que.poll();
            for (int n : adjacent[node]) {
                if (!visited[n]) {
                    que.add(n);
                    visited[n] = true;
                } else if (cycle == n) {
                    isCycle = true;
                }
            }
        }
    }

}

// 인접 리스트를 배열로(인접행렬) 이용한 BFS
class Boj10451 {
    static int[] graph;
    static boolean[] visited;
    static boolean isCycle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            graph = new int[n + 1];
            visited = new boolean[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i] = num;
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                isCycle = false;
                if (!visited[i]) {
                    BFS(i, i);
                }
                if (isCycle) {
                    count++;
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void BFS(int root, int cycle) {
        visited[root] = true;
        Queue<Integer> que = new LinkedList<>();
        que.add(root);

        while (!que.isEmpty()) {
            int node = que.poll();
            int next = graph[node];
            if (!visited[next]) {
                que.add(next);
                visited[next] = true;
            } else if (cycle == next) {
                isCycle = true;
            }
        }
    }

}



