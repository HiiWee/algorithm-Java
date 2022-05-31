package boj;

/*
    1. 어차피 루트는 1로 고정되어 있고 1부터 아래로 탐색하면 됨
    2. 간선이 연결된 노드들이 트리 순서대로 주어지지 않으므로 일단 그래프 형식으로 만듦
    3. 이후 1부터 탐색하며 방문 체크를 하면 결국 트리 구조로 탐색을 하게 된다.
    4. 이를 이용해 2 ~ n - 1번째 노드의 부모를 탐색한다.
    5. BFS, DFS 모두 가능
*/

import java.io.*;
import java.util.*;

class Boj11725_1 {
    static List<Integer>[] tree;
    static boolean[] visited;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        parents = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int firstNum = Integer.parseInt(st.nextToken());
            int secondNum = Integer.parseInt(st.nextToken());
            tree[firstNum].add(secondNum);
            tree[secondNum].add(firstNum);
        }

        DFS(1);

        for (int i = 2; i <= n; i++) {
            bw.write(parents[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int root) {
        visited[root] = true;
        for (int child : tree[root]) {
            if (!visited[child]) {
                parents[child] = root;
                DFS(child);
            }
        }
    }
}

class Boj11725_2 {
    static List<Integer>[] tree;
    static boolean[] visited;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int firstNum = Integer.parseInt(st.nextToken());
            int secondNum = Integer.parseInt(st.nextToken());
            tree[firstNum].add(secondNum);
            tree[secondNum].add(firstNum);
        }

        BFS(1);

        for (int i = 2; i <= n; i++) {
            bw.write(parents[i] + "\n");
        }
        bw.flush();
        bw.close();

    }

    public static void BFS(int root) {
        Queue<Integer> que = new LinkedList<>();
        que.add(root);
        visited[root] = true;

        while (!que.isEmpty()) {
            int node = que.poll();

            for (int childNode : tree[node]) {
                if (!visited[childNode]) {
                    que.add(childNode);
                    visited[childNode] = true;
                    parents[childNode] = node;
                }
            }
        }
    }
}