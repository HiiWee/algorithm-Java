package boj;

import java.io.*;
import java.util.*;

class Boj16437 {

    // 0양, 1늑대
    static class Node {
        int type, number;
        long count;

        public Node(int type, int number, long count) {
            this.type = type;
            this.number = number;
            this.count = count;
        }
    }

    static int n;
    static Node[] nodes;
    static List<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        nodes = new Node[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        nodes[1] = new Node(0, 1, 0);

        for (int i = 2; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = st.nextToken().equals("S") ? 0 : 1;
            long a = Long.parseLong(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(type, i, a);
            tree[number].add(i);
            tree[i].add(number);
        }

        long count = search(1);

        bw.write(Long.toString(count));
        bw.flush();
        bw.close();
    }

    public static long search(int nodeNumber) {
        long count = 0;

        visited[nodeNumber] = true;
        for (int next : tree[nodeNumber]) {
            if (!visited[next]) {
                count += search(next);
            }
        }

        if (nodes[nodeNumber].type == 0) {
            count += nodes[nodeNumber].count;
        } else {
            count -= nodes[nodeNumber].count;
        }

        return Math.max(count, 0);
    }
}
