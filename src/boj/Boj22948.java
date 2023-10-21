package boj;

/*
    좌표평도도 원이므로 봤을때 원들이 주어지는 관계를 트리 관계로 치환할 수 있습니다.
    임의의 원을 가장 가까이에 있는 다른 원과 간선을 이어주면 트리가 됩니다.
    이게 가능한 이유는 임의의 원은 단 하나의 원안에 포함되기 때문입니다.

    이런 트리는 스택을 통해 원의 외부, 포함 관계를 판단하여 만들 수 있습니다.
    - 우선 원의 좌, 우 좌표를 계산하고 배열에 기록합니다.
    - 모든 원을 기록했다면 시작부터 끝을 탐색하며, 스택에 담습니다.
    - 스택에 담을때 동일한 번호가 담긴다면, pop을 합니다.
      이때, 현재 스택의 top에 존재하는 값과 pop한 값은 간선이 존재함을 의미합니다.
    - 스택이 비어있다면 0(좌표평면)과의 간선으로 간주합니다.

    이렇게 트리를 만들었다면 BFS 탐색을 통해 시작노드 -> 끝 노드까지 가는 좌표를 탐색하면 됩니다.
*/

import java.util.*;
import java.io.*;

class Boj22948 {

    private static final int OFFSET = 1_000_000;

    private static int n;
    private static int[] poses = new int[2_010_001];
    private static List<Integer>[] tree;

    static class Node {
        int number, count;
        String path;

        Node(int number, int count, String path) {
            this.number = number;
            this.count = count;
            this.path = path;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) + OFFSET;
            int r = Integer.parseInt(st.nextToken());
            poses[x - r] = -num;
            poses[x + r] = num;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int pos : poses) {
            if (pos < 0) {
                stack.push(Math.abs(pos));
            } else if (pos > 0) {
                stack.pop();
                tree[pos].add(stack.peek());
                tree[stack.peek()].add(pos);
            }
        }
        Node findNode = bfs(A, B);

        bw.write(findNode.count + "\n" + findNode.path);
        bw.flush();
        bw.close();
    }

    public static Node bfs(int start, int end) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(start, 1, String.valueOf(start)));

        boolean[] visited = new boolean[n + 1];
        visited[start] = true;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.number == end) {
                return curNode;
            }
            for (int next : tree[curNode.number]) {
                if (!visited[next]) {
                    visited[next] = true;
                    que.offer(new Node(next, curNode.count + 1, curNode.path + " " + next));
                }
            }
        }
        return null;
    }
}