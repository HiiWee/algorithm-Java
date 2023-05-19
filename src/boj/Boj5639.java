package boj;

/*
    1. 트리 직접 그리기
    전위 순회 특성상 루트 노드의 좌측부분이 전부 주어지고, 우측 부분이 주어진다.
    따라서 입력받는 순서대로 직접 이진 검색 트리를 만들어서 이후 후위순회를 하면 된다.
*/
import java.io.*;
import java.util.*;

class Boj5639_1 {

    static class Node {
        int number;
        Node left, right;

        public Node(String number) {
            this.number = Integer.parseInt(number);
        }
    }

    private static Node rootNode;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String rootNumber = br.readLine();
        rootNode = new Node(rootNumber);
        String nodeNumber;
        while ((nodeNumber = br.readLine()) != null) {
            Node node = new Node(nodeNumber);
            addToBinarySearchTree(node, rootNode);
        }

        postOrder(rootNode);

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void postOrder(Node currentNode) {
        if (currentNode == null) {
            return;
        }
        postOrder(currentNode.left);
        postOrder(currentNode.right);
        result.append(currentNode.number).append("\n");
    }

    public static void addToBinarySearchTree(Node node, Node addedNode) {
        if (addedNode.number > node.number) {
            if (addedNode.left == null) {
                addedNode.left = node;
            } else {
                addToBinarySearchTree(node, addedNode.left);
            }
        } else {
            if (addedNode.right == null) {
                addedNode.right = node;
            } else {
                addToBinarySearchTree(node, addedNode.right);
            }
        }
    }
}

/*
    2. 배열로 트리 구현하기
    BST를 전위 순회를 하는 값을 하나씩 받을경우, 루트 노드보다 큰 값을 처음 만나면 해당 노드 인덱스 - 1까지가
    루트 노드의 좌측 트리가 된다.
    반대로 해당 노드인덱스 +1 부터 끝 인덱스까지가 루트 노드 기준 우측 자식 트리가 된다.

    루트 노드 기준 좌측 트리 -> 루트 노드 기준 우측 트리 순서로 재귀 탐색을한다.
    이때 현재 start > end가 아닐때까지 탐색을 진행한다.
    재귀가 끝나면 start 인덱스의 값을 result에 append한다.
*/

class Boj5639_2 {
    private static List<Integer> preorderResult = new ArrayList<>();
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String nodeNumber;
        while ((nodeNumber = br.readLine()) != null) {
            preorderResult.add(Integer.parseInt(nodeNumber));
        }

        postorder(0, preorderResult.size() - 1);

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void postorder(int start, int end) {
        if (start > end) {
            return;
        }
        int mid = start + 1;
        while (mid <= end && preorderResult.get(mid) < preorderResult.get(start)) {
            mid++;
        }
        postorder(start + 1, mid - 1);
        postorder(mid, end);
        result.append(preorderResult.get(start)).append("\n");
    }
}