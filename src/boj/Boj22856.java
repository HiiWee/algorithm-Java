package boj;

import java.io.*;
import java.util.*;

class Boj22856 {
    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    private static int[][] tree;
    private static int[] parents;
    private static List<Integer> visitedOrder = new ArrayList<>();
    private static int lastNode;
    private static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new int[n + 1][2];
        parents = new int[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int leftNode = Integer.parseInt(st.nextToken());
            tree[node][LEFT] = leftNode;
            if (leftNode != -1) {
                parents[tree[node][LEFT]] = node;
            }

            int rightNode = Integer.parseInt(st.nextToken());
            tree[node][RIGHT] = rightNode;
            if (rightNode != -1) {
                parents[tree[node][RIGHT]] = node;
            }
        }

        normalInorder(1);
        lastNode = visitedOrder.get(n - 1);
        newInorder(1);
    }

    public static void normalInorder(int node) {
        if (node == -1) {
            return;
        }
        normalInorder(tree[node][LEFT]);
        visitedOrder.add(node);
        normalInorder(tree[node][RIGHT]);
    }

    public static void newInorder(int node) {
        if (tree[node][LEFT] != -1) {
            newInorder(tree[node][LEFT]);
            count++;
        }
        if (node == lastNode) {
            System.out.println(count);
            return;
        }
        count++;
        if (tree[node][RIGHT] != -1) {
            newInorder(tree[node][RIGHT]);
            count++;
        }
    }
}