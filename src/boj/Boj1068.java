package boj;

/*
    트리의 방향이 부모 -> 자식으로 흐름이 흐르도록해야하므로 단방향 연결리스트로 만든다.

    이후 주어진 노드 번호에 해당하는 리스트를 전부 삭제하고 BFS를 돈다.
    돌면서 만약 해당 노드에 해당되는 연결리스트가 비어있다면 리프노드이므로 카운팅을한다.
*/
import java.io.*;
import java.util.*;

class Boj1068 {
    private static List<Integer>[] trees;
    private static int leafCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        trees = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            trees[i] = new ArrayList<>();
        }

        int rootNode = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (number != -1) {
                trees[number].add(i);
            } else {
                rootNode = i;
            }
        }
        int removeNumber = Integer.parseInt(br.readLine());
        removeTree(removeNumber);

        searchLeafNode(rootNode);

        bw.write(leafCount + "");
        bw.flush();
        bw.close();
    }

    public static void searchLeafNode(int node) {
        if (trees[node] == null) {
            return;
        }
        if (trees[node].isEmpty() || trees[node].size() == 1 && trees[trees[node].get(0)] == null) {
            leafCount++;
            return;
        }

        for (int nextNode : trees[node]) {
            searchLeafNode(nextNode);
        }
    }

    public static void removeTree(int removeNumber) {
        if (trees[removeNumber].size() == 0) {
            trees[removeNumber] = null;
            return;
        }
        for (int number : trees[removeNumber]) {
            removeTree(number);
        }
        trees[removeNumber] = null;
    }
}
