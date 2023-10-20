package boj;

import java.io.*;
import java.util.*;

class Boj22868 {

    private static int n, m, s, e;
    private static List<Integer>[] graphs;
    private static boolean[] visited;

    static class Node {
        int number, count;
        Node before;

        Node (int number, Node before, int count) {
            this.number = number;
            this.count = count;
            this.before = before;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graphs = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graphs[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graphs[start].add(end);
            graphs[end].add(start);
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            graphs[i].sort(Comparator.naturalOrder());
        }

        visited = new boolean[n + 1];
        Node endNode = bfs(s, e);

        visited = new boolean[n + 1];
        for (Node node = endNode; node != null; node = node.before) {
            visited[node.number] = true;
        }
        visited[s] = false;

        Node startNode = bfs(e, s);

        bw.write((endNode.count + startNode.count) + "");
        bw.flush();
        bw.close();
    }

    public static Node bfs(int start, int end) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(start, null, 0));

        visited[start] = true;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.number == end) {
                return curNode;
            }
            for (int next : graphs[curNode.number]) {
                if (!visited[next]) {
                    que.offer(new Node(next, curNode, curNode.count + 1));
                    visited[next] = true;
                }
            }
        }
        return null;
    }
}