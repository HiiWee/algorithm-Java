package boj;

/**
 * 트리의 지름 구하기
 * 1. 임의의 정점 x를 구한다.
 * 2. x와 가장 먼 정점 y를 구한다.
 * 3. y와 가장 먼 정점 z를 구한다.
 * 트리의 지름은 정점 y와 z를 연결하는 경로이다.
 */

import java.util.*;
import java.io.*;

class Boj1167 {
    static List<Node>[] tree;
    static boolean[] visited;
    static int farNode;
    static int max = Integer.MIN_VALUE;
    static StringTokenizer st;

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
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken());
            while (true) {
                int node = Integer.parseInt(st.nextToken());
                if (node == -1) {
                    break;
                }
                int value = Integer.parseInt(st.nextToken());
                tree[root].add(new Node(node, value));
            }
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

    /*
        1. DFS를 돌면서 각 경로의 가중치를 더하고 만약 끝까지 탐방하면서
        2. 가중치의 값이 더 커지는 경우를 발견하면 노드 값을 교체하고, 가중치를 업데이트 해준다.
    */
    public static void DFS(int root, int length) {
        visited[root] = true;
        if (max < length) {
            max = length;
            farNode = root;
        }
        for (Node node : tree[root]) {
            if (!visited[node.end]) {
                int value = node.weight;
                DFS(node.end, length + value);
            }
        }
    }
}