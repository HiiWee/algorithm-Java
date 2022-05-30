package boj;

import java.io.*;
import java.util.*;

class Main {
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