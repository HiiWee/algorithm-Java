package boj;

import java.io.*;
import java.util.*;

class Boj17837 {

    static class Node {
        int r, c, number, dir;

        public Node(int number, int dir, int r, int c) {
            this.number = number;
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    // 상 좌 하 우
    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, -1, 0, 1};
    // 우좌상하로 두어 인덱싱
    static final int[] move = {3, 1, 0, 2};
    static int n, k;
    static int[][] map;
    static Node[] nodes;
    static List<Node>[][] nodeMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nodes = new Node[k + 1];
        map = new int[n][n];
        nodeMap = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                nodeMap[i][j] = new ArrayList<>();
            }
        }
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            nodes[i] = new Node(i, move[d], r, c);
            nodeMap[r][c].add(nodes[i]);
        }

        int turn = 1;
        while (true) {
            if (turn > 1000) {
                bw.write("-1");
                bw.flush();
                bw.close();
                return;
            }
            boolean shouldEnd = false;
            for (int i = 1; i <= k; i++) {
                shouldEnd |= moveNode(i);
            }
            if (shouldEnd) {
                break;
            }
            turn++;
        }

        bw.write(turn + "");
        bw.flush();
        bw.close();
    }

    public static boolean moveNode(int number) {
        Node curNode = nodes[number];
        // 다음 위치를 확인
        int nextR = curNode.r + rows[curNode.dir];
        int nextC = curNode.c + cols[curNode.dir];

        // 파란색, 격자 밖이면 방향을 바꾸고 다시 진행하는데 그래도 안되면 가만히 있음
        if (!isPossible(nextR, nextC)) {
            curNode.dir = (curNode.dir + 2) % 4;
            nextR = curNode.r + rows[curNode.dir];
            nextC = curNode.c + cols[curNode.dir];

            // 그래도 전진할 수 없다면 아무것도 하지 않음
            if (!isPossible(nextR, nextC)) {
                return false;
            }
        }
        // 흰색, 빨간색에 따라 동작을 진행함
        if (map[nextR][nextC] == 0) {
            return move(curNode, nextR, nextC, true);
        }
        return move(curNode, nextR, nextC, false);
    }

    public static boolean move(Node node, int nextR, int nextC, boolean isWhite) {
        // 이동하려는 노드의 인덱스와 마지막 노드의 인덱스를 가져와서 잘라냄
        List<Node> curNodes = nodeMap[node.r][node.c];
        int startIndex = curNodes.indexOf(node);
        int endIndex = curNodes.size() - 1;
        List<Node> moveNodes = new ArrayList<>();

        // 노드 옮기기
        if (isWhite) {
            for (int i = startIndex; i <= endIndex; i++) {
                Node moveNode = curNodes.get(i);
                moveNodes.add(curNodes.get(i));
                moveNode.r = nextR;
                moveNode.c = nextC;
            }
        } else {
            // 빨간색 지역은 역순으로 추가되어야 함
            for (int i = endIndex; i >= startIndex; i--) {
                Node moveNode = curNodes.get(i);
                moveNodes.add(curNodes.get(i));
                moveNode.r = nextR;
                moveNode.c = nextC;
            }
        }
        // 기존 노드 지우기
        curNodes.removeAll(moveNodes);
        // 새로운 좌표에 노드 삽입
        nodeMap[nextR][nextC].addAll(moveNodes);
        // 4개이상 모이면 종료되어야 하므로 조건 반환
        return nodeMap[nextR][nextC].size() >= 4;
    }

    public static boolean isPossible(int r, int c) {
        // 격자 밖을 벗어나거나, 파란색인 경우 방향을 바꿈
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return false;
        }
        return map[r][c] != 2;
    }
}