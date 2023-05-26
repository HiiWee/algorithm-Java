package boj;

/*
    핵심: 트리에 열 번호가 지정되는 순서는 중위순회에 의해 결정된다.

    각 노드는 자기번호와 자신의 왼쪽, 오른쪽 노드의 번호와, 부모 노드의 번호를 저장한다.
    이후 모든 입력을 받게되고, 부모노드가 초기화되지 않은 노드가 루트노드가 된다.

    이후 각 레벨의 최소 열 번호와 최대 열 번호를 저장할 배열을 선언한다.
    이때 배열의 크기는 이진 편향트리로 구성되었을때 가장 레벨이 높게 생성되므로 주어진 n값을 이용한다.

    루트 노드 부터 DFS를 돌면서 노드들의 열 번호를 구하는데, 이때 최소 열 번호, 최대 열 번호 배열을 갱신한다.
*/

import java.io.*;
import java.util.*;

class Boj2250 {
    static class Node {
        int num, left, right;
        Node(int num, int left, int right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }
    }
    private static Node[] nodes;
    private static int[] minColumns;
    private static int[] maxColumns;
    private static int columnNumber = 1;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];
        minColumns = new int[n + 1];
        maxColumns = new int[n + 1];
        Arrays.fill(minColumns, Integer.MAX_VALUE);
        Arrays.fill(maxColumns, Integer.MIN_VALUE);
        int[] parents = new int[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            if (left != -1) {
                parents[left] = num;
            }
            if (right != -1) {
                parents[right] = num;
            }
            nodes[num] = new Node(num, left, right);
        }
        int root = -1;
        for (int i = 1; i <= n; i++) {
            if (parents[i] == 0) {
                root = i;
                break;
            }
        }
        inorder(root, 1);

        int maxLength = 0;
        int level = -1;
        for (int i = n; i >= 1; i--) {
            if (maxColumns[i] == Integer.MIN_VALUE || minColumns[i] == Integer.MAX_VALUE) {
                continue;
            }
            if (maxLength <= maxColumns[i] - minColumns[i] + 1) {
                level = i;
                maxLength = maxColumns[i] - minColumns[i] + 1;
            }
        }

        bw.write(level + " " + maxLength);
        bw.flush();
        bw.close();
    }

    public static void inorder(int nodeNumber, int level) {
        if (nodeNumber == -1) {
            return;
        }
        inorder(nodes[nodeNumber].left, level + 1);
        minColumns[level] = Math.min(minColumns[level], columnNumber);
        maxColumns[level] = Math.max(maxColumns[level], columnNumber);
        columnNumber++;
        inorder(nodes[nodeNumber].right, level + 1);
    }
}