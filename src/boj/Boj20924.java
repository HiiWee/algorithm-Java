package boj;

/*
    양방향 연결 리스트
    나무 기둥의 길이를 먼저 구한다.(연결된 노드가 1개이거나 2개라면 모두 방문하여 길이를 측정)
    해당 길이는 기둥 변수에 길이를 저장한다.

*/
import java.io.*;
import java.util.*;

class Boj20924 {
    static class Node {
        int number, weight;

        public Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }

    private static List<Node>[] trees;
    private static boolean[] visited;
    private static int root;
    private static int pillarLength;
    private static int branchStartNumber;
    private static int branchLength;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());
        trees = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            trees[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            trees[start].add(new Node(end, weight));
            trees[end].add(new Node(start, weight));
        }
        calcPillarLength(root, 0);
        calcBranchLength(branchStartNumber, 0);

        bw.write(pillarLength + " " + branchLength);
        bw.flush();
        bw.close();
    }

    public static void calcBranchLength(int nodeNumber, int branchSum) {
        if (trees[nodeNumber].size() <= 1) {
            branchLength = Math.max(branchLength, branchSum);
            return;
        }
        for (Node node : trees[nodeNumber]) {
            if (!visited[node.number]) {
                visited[node.number] = true;
                calcBranchLength(node.number, branchSum + node.weight);
            }
        }
    }

    public static void calcPillarLength(int nodeNumber, int pillarSum) {
        if (trees[nodeNumber].size() >= 3
                || nodeNumber != root && trees[nodeNumber].size() == 1
                || nodeNumber == root && trees[nodeNumber].size() > 1) {
            visited[nodeNumber] = true;
            branchStartNumber = nodeNumber;
            pillarLength = pillarSum;
            return;
        }
        for (Node node : trees[nodeNumber]) {
            if (!visited[node.number]) {
                visited[node.number] = true;
                calcPillarLength(node.number, pillarSum + node.weight);
            }
        }
    }
}