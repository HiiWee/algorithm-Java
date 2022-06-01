package boj;

import java.util.*;
import java.io.*;

// DFS를 이용한 트리의 지름 구하기
class Boj1967_1 {
    static int max = Integer.MIN_VALUE;
    static List<Node>[] tree;
    static boolean[] visited;
    static int farNode;

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[root].add(new Node(end, weight));
            tree[end].add(new Node(root, weight));
        }

        DFS(1, 0);
        for (int i = 1; i <= n; i++) {
            visited[i] = false;
        }
        DFS(farNode, 0);
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void DFS(int root, int weight) {
        visited[root] = true;
        if (max < weight) {
            farNode = root;
            max = weight;
        }

        for (Node node : tree[root]) {
            int nextNode = node.end;
            if (!visited[nextNode]) {
                int value = node.weight;
                DFS(node.end, node.weight + weight);
            }
        }
    }
}

// BFS를 이용한 트리의 지름 구하기
class Boj1967_2 {
    static int max = 0;
    static List<Node>[] tree;
    static boolean[] visited;
    static int farNode;
    static int n;

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[root].add(new Node(end, weight));
            tree[end].add(new Node(root, weight));
        }

        BFS(1);
        for (int i = 1; i <= n; i++) {
            visited[i] = false;
        }
        BFS(farNode);
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS(int root) {
        Queue<Integer> que = new LinkedList<>();
        visited[root] = true;
        que.add(root);
        int[] length = new int[n + 1];

        while (!que.isEmpty()) {
            int currentNode = que.poll();
            for (Node node : tree[currentNode]) {
                int nextNode = node.end;
                if (!visited[nextNode]) {
                    length[nextNode] = node.weight + length[currentNode];
                    que.add(nextNode);
                    visited[nextNode] = true;
                    if (max < length[nextNode]) {
                        farNode = nextNode;
                        max = length[nextNode];
                    }
                }
            }
        }
    }
}