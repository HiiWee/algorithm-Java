package boj;

import java.io.*;
import java.util.*;

class Boj1991_1 {
    static Map<String, List<String>> map;
    static String topRoot;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map = new HashMap<>();
        sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            List<String> list = new ArrayList<>();
            String root = st.nextToken();
            if (i == 0) {
                topRoot = root;
            }
            for (int j = 0; j < 2; j++) {
                list.add(st.nextToken());
            }
            map.put(root, list);
        }
        preorder(topRoot);
        sb.append("\n");
        inorder(topRoot);
        sb.append("\n");
        postorder(topRoot);
        sb.append("\n");
        bw.write(sb + "");
        bw.flush();
        bw.close();
    }

    public static void print(String node) {
        sb.append(node);
    }

    public static void preorder(String root) {
        if (root.equals(".")) {
            return;
        }
        List<String> list = map.get(root);
        print(root);
        preorder(list.get(0));
        preorder(list.get(1));
    }

    public static void inorder(String root) {
        if (root.equals(".")) {
            return;
        }
        List<String> list = map.get(root);
        inorder(list.get(0));
        print(root);
        inorder(list.get(1));
    }

    public static void postorder(String root) {
        if (root.equals(".")) {
            return;
        }
        List<String> list = map.get(root);
        postorder(list.get(0));
        postorder(list.get(1));
        print(root);
    }

}

class Boj1991_2 {
    static class Node {
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static List<Node>[] list;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String root = st.nextToken();
            int left = st.nextToken().charAt(0) - 'A';
            int right = st.nextToken().charAt(0) - 'A';
            list[root.charAt(0) - 'A'].add(new Node(left, right));
        }
        preorder(0);
        sb.append("\n");
        inorder(0);
        sb.append("\n");
        postorder(0);
        sb.append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.flush();
    }

    public static void print(int root) {
        sb.append((char) (root + 'A'));
    }

    public static void preorder(int root) {
        if (root + 'A' == '.') {
            return;
        }
        Node node = list[root].get(0);
        print(root);
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(int root) {
        if (root + 'A' == '.') {
            return;
        }
        Node node = list[root].get(0);
        inorder(node.left);
        print(root);
        inorder(node.right);
    }

    public static void postorder(int root) {
        if (root + 'A' == '.') {
            return;
        }
        Node node = list[root].get(0);
        postorder(node.left);
        postorder(node.right);
        print(root);
    }
}