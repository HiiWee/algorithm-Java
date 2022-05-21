package structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    static class Node {
        int data;
        LinkedList<Node> adjacent;
        boolean visited;

        public Node(int data) {
            this.data = data;
            this.visited = false;
            adjacent = new LinkedList<>();
        }
    }

    Node[] nodes;

    public Graph(int size) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i);
        }
    }

    public void addEdge(int l1, int l2) {
        Node n1 = nodes[l1];
        Node n2 = nodes[l2];
        if (!n1.adjacent.contains(n2)) {
            n1.adjacent.add(n2);
        }
        if (!n2.adjacent.contains(n1)) {
            n2.adjacent.add(n1);
        }
    }

    public void DFS() {
        DFS(1);
    }

    // 기본적인 DFS
    public void DFS(int index) {
        Node root = nodes[index];
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        root.visited = true;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            for (Node n : node.adjacent) {
                if (!n.visited) {
                    n.visited = true;
                    stack.push(n);
                }
            }
            print(node);
        }
    }

    public void recurDFS(Node root) {
        if (root == null) {
            return;
        }
        print(root);
        root.visited = true;
        for (Node n : root.adjacent) {
            if (!n.visited) {
                recurDFS(n);
            }
        }
    }

    public void recurDFS(int index) {
        Node root = nodes[index];
        recurDFS(root);
    }

    public void BFS(int index) {
        Node root = nodes[index];
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        root.visited = true;
        while (!que.isEmpty()) {
            Node node = que.poll();
            for (Node n : node.adjacent) {
                if (!n.visited) {
                    n.visited = true;
                    que.add(n);
                }
            }
            print(node);
        }

    }

    public void print(Node node) {
        System.out.print(node.data + " ");
    }

    public void init() {
        for (Node node : nodes) {
            node.visited = false;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 8);

        graph.DFS(0);
        System.out.println();

        graph.init();

        graph.recurDFS(0);
        System.out.println();

        graph.init();

        graph.BFS(0);
    }
}
