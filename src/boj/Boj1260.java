package boj;

import java.io.*;
import java.util.*;

class Boj1260 {
    static class Graph {
        static class Node {
            int data;
            List<Node> adjacent;
            boolean visited;

            public Node(int data) {
                this.data = data;
                this.visited = false;
                this.adjacent = new LinkedList<>();
            }
        }
        StringBuilder sb;
        Node[] nodes;
        public Graph(int size) {
            sb = new StringBuilder();
            nodes = new Node[size];
            for (int i = 0; i < size; i++) {
                nodes[i] = new Node(i + 1);
            }
        }

        public void init() {
            for (int i = 0; i < nodes.length; i++) {
                nodes[i].visited = false;
            }
        }

        public void addEdges(int l1, int l2) {
            Node n1 = nodes[l1 - 1];
            Node n2 = nodes[l2 - 1];
            if (!n1.adjacent.contains(n2)) {
                n1.adjacent.add(n2);
            }
            if (!n2.adjacent.contains(n1)) {
                n2.adjacent.add(n1);
            }
        }

        public void sortEdges() {
            for (int i = 0; i < nodes.length; i++) {
                Collections.sort(nodes[i].adjacent, (o1, o2) -> {
                    return o1.data - o2.data;
                });
            }
        }

        public void DFS(int index) {
            Node node = nodes[index - 1];
            if (node == null) {
                return;
            }
            sb.append(node.data).append(" ");
            node.visited = true;
            for (Node n : node.adjacent) {
                if (!n.visited) {
                    DFS(n.data);
                }
            }
        }

        public void BFS(int index) {
            sb.setLength(0);
            Node root = nodes[index - 1];
            if (root == null) {
                return;
            }
            Queue<Node> que = new LinkedList<>();
            que.add(root);
            root.visited = true;
            while (!que.isEmpty()) {
                Node node = que.poll();
                for (Node n : node.adjacent) {
                    if (!n.visited) {
                        que.add(n);
                        n.visited = true;
                    }
                }
                sb.append(node.data).append(" ");
            }
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertices = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int l1 = Integer.parseInt(st.nextToken());
            int l2 = Integer.parseInt(st.nextToken());
            graph.addEdges(l1, l2);
        }
        graph.sortEdges();
        graph.DFS(root);
        bw.write(graph.sb.toString() + "\n");

        graph.init();

        graph.BFS(root);
        bw.write(graph.sb.toString() + "\n");
        bw.flush();
        bw.close();
    }
}