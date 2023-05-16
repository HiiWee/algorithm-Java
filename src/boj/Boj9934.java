package boj;


/*
    주어진 깊이 만큼의 완전 이진 트리를 먼저 만든다.

    이후 비어 있는 트리를 중위 순회 한다. 이때 전역적으로 공유하는 인덱스를 둔다.
    중위 순위를 돌때 현재 빌딩에 방문하는 경우, 현재 인덱스에 해당되는 방문 빌딩 번호를 노드에 기록한다.

    순회가 끝나게 되면 트리를 루트 부터 출력한다. 이때 출력 방식은 재귀를 이용한다.
*/
import java.io.*;
import java.util.*;

class Boj9934_1 {

    static class Node {
        int number, depth;
        Node left, right;

        public Node(Node left, Node right, int depth) {
            this.left = left;
            this.right = right;
            this.depth = depth;
        }

        public Node(int depth) {
            this.depth = depth;
        }
    }
    private static Map<Integer, List<Integer>> nodeNumbers = new HashMap<>();
    private static int[] buildingNumber;
    private static int currentIndex;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = (int) Math.pow(2, n) - 1;
        buildingNumber = new int[count];
        for (int i = 0; i < count; i++) {
            buildingNumber[i] = Integer.parseInt(st.nextToken());
        }
        Node root = new Node(0);
        createTree(root, n, 1);
        inOrder(root);

        List<Integer> keys = new ArrayList<>(nodeNumbers.keySet());
        keys.sort(Comparator.naturalOrder());

        for (int key : keys) {
            StringBuilder result = new StringBuilder();
            for (int number : nodeNumbers.get(key)) {
                result.append(number).append(" ");
            }
            bw.write(result.toString().trim() + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void inOrder(Node currentNode) {
        if (currentNode == null) {
            return;
        }
        inOrder(currentNode.left);

        currentNode.number = buildingNumber[currentIndex++];
        if (nodeNumbers.containsKey(currentNode.depth)) {
            nodeNumbers.get(currentNode.depth).add(currentNode.number);
        } else {
            List<Integer> nodes = new ArrayList<>();
            nodes.add(currentNode.number);
            nodeNumbers.put(currentNode.depth, nodes);
        }

        inOrder(currentNode.right);
    }

    public static void createTree(Node currentNode, int depth, int currentDepth) {
        if (currentDepth == depth) {
            return;
        }
        currentNode.left = new Node(currentDepth);
        currentNode.right = new Node(currentDepth);
        createTree(currentNode.left, depth, currentDepth + 1);
        createTree(currentNode.right, depth, currentDepth + 1);
    }
}


/*
    중위 순회를 이용하므로, 처음 주어진 배열의 가운데 값이 루트노드이다.
    이후 루트 기준 좌측의 가운데 노드가 루트노드의 왼쪽 노드, 루트 기준 우측의 가운데 노드가 루트노드의 오른쪽 노드가 된다.
    이런식으로 재귀 탐색을 하면 된다.
*/

class Boj9934_2 {
    private static Map<Integer, List<Integer>> treeNumber = new HashMap<>();
    private static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int count = (int) Math.pow(2, n) - 1;
        numbers = new int[count];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        solve(0, count - 1, 0, n);

        StringBuilder result = new StringBuilder();
        List<Integer> keys = new ArrayList<>(treeNumber.keySet());
        keys.sort(Comparator.naturalOrder());
        for (int key : keys) {
            for (int number : treeNumber.get(key)) {
                result.append(number).append(" ");
            }
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void solve(int startIndex, int endIndex, int currentDepth, int depth) {
        if (currentDepth == depth) {
            return;
        }
        int middleIndex = (startIndex + endIndex) / 2;
        if (treeNumber.containsKey(currentDepth)) {
            treeNumber.get(currentDepth).add(numbers[middleIndex]);
        } else {
            List<Integer> nodes = new ArrayList<>();
            nodes.add(numbers[middleIndex]);
            treeNumber.put(currentDepth, nodes);
        }
        solve(startIndex, middleIndex - 1, currentDepth + 1, depth);
        solve(middleIndex + 1, endIndex, currentDepth + 1, depth);
    }
}