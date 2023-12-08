package boj;

import java.io.*;
import java.util.*;

class Boj19238 {

    static class Node {
        int r, c, count;

        Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};
    private static final Map<Integer, Node> destinations = new HashMap<>();

    private static int n, m, fuel;
    private static Node startNode;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        startNode = new Node(
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
                0
        );

        int count = 2;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int customerR = Integer.parseInt(st.nextToken()) - 1;
            int customerC = Integer.parseInt(st.nextToken()) - 1;
            int destinationR = Integer.parseInt(st.nextToken()) - 1;
            int destinationC = Integer.parseInt(st.nextToken()) - 1;
            destinations.put(count, new Node(destinationR, destinationC, 0));
            map[customerR][customerC] = count++;
        }

        while (count-- > 2) {
            // 출발지 노드를 찾음
            Node findNode = bfs();
            // 갈 수 없다면 -1
            if (findNode == null || findNode.count > fuel) {
                fuel = -1;
                break;
            }
            // 출발지 노드까지가는 비용
            int firstAmount = findNode.count;
            // 도착지 노드 번호
            Node destinationNode = destinations.get(map[findNode.r][findNode.c]);
            // 출발지 노드 번호 초기화 (중복 체킹 가능성)
            map[findNode.r][findNode.c] = 0;
            // findNode를 시작 노드로 사용할 것이므로 카운터 초기화
            findNode.count = 0;

            // findNode를 출발지로 도착 번호는 findCount
            startNode = targetBfs(findNode, destinationNode);

            // 도착지에 갈 수 없다면 -1
            if (startNode == null || fuel < firstAmount + startNode.count) {
                fuel = -1;
                break;
            }

            // 연료를 도착지까지 갔을때 * 2를 채움
            fuel = fuel - firstAmount - startNode.count + startNode.count * 2;
            // startNode부터 진행되므로 카운트값 초기화
            startNode.count = 0;
        }

        bw.write(fuel + "");
        bw.flush();
        bw.close();
    }

    public static Node targetBfs(Node startNode, Node destinationNode) {
        boolean[][] visited = new boolean[n][n];
        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);
        visited[startNode.r][startNode.c] = true;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.r == destinationNode.r && curNode.c == destinationNode.c) {
                return curNode;
            }
            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    continue;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC] != 1) {
                    visited[nextR][nextC] = true;
                    que.offer(new Node(nextR, nextC, curNode.count + 1));
                }
            }
        }
        return null;
    }

    public static Node bfs() {
        boolean[][] visited = new boolean[n][n];
        // 출발지 노드를 찾을때 가깝지만, 가장 상단 그리고 가장 좌측에 있는 노드를 찾음
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> {
            if (n1.count != n2.count) {
                return n1.count - n2.count;
            }
            if (n1.r == n2.r) {
                return n1.c - n2.c;
            }
            return n1.r - n2.r;
        });
        que.offer(startNode);
        visited[startNode.r][startNode.c] = true;

        while (!que.isEmpty()) {
            Node curNode = que.poll();
            if (map[curNode.r][curNode.c] >= 2) {
                return curNode;
            }
            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    continue;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC] != 1) {
                    visited[nextR][nextC] = true;
                    que.offer(new Node(nextR, nextC, curNode.count + 1));
                }
            }
        }
        return null;
    }
}