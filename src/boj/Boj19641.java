package boj;

import java.io.*;
import java.util.*;

class Boj19641 {

    static int root;
    static List<Integer>[] trees;
    static int[][] nodeInfo;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        nodeInfo = new int[n + 1][3];
        visited = new boolean[n + 1];
        trees = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            trees[i] = new ArrayList<>();
            nodeInfo[i][0] = i;
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int startNumber = Integer.parseInt(st.nextToken());
            int number;
            while ((number = Integer.parseInt(st.nextToken())) != -1) {
                if (!trees[startNumber].contains(number)) {
                    trees[startNumber].add(number);
                }
            }
            trees[startNumber].sort(Comparator.naturalOrder());
        }
        root = Integer.parseInt(br.readLine());

        search(1, root);

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                answer.append(nodeInfo[i][j]).append(" ");
            }
            answer.append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static int search(int left, int node) {
        nodeInfo[node][1] = left;

        visited[node] = true;
        int right = left;
        for (int next : trees[node]) {
            if (!visited[next]) {
                right = Math.max(right, search(right + 1, next));
            }
        }
        nodeInfo[node][2] = right + 1;

        return right + 1;
    }
}
