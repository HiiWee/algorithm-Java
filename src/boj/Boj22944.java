package boj;

import java.io.*;
import java.util.*;

class Boj22944_1 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    private static int n, h, d;
    private static char[][] map;
    private static int[][] visited;

    static class Node {
        int r, c, hp, umbrella, count;

        Node(int r, int c, int hp, int umbrella, int count) {
            this.r = r;
            this.c = c;
            this.hp = hp;
            this.umbrella = umbrella;
            this.count = count;
        }

        public void removeHp() {
            if (umbrella > 0) {
                umbrella--;
            } else {
                hp--;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new char[n][n];
        visited = new int[n][n];

        Queue<Node> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    que.offer(new Node(i, j, h, 0, 0));
                }
            }
        }
        visited[que.peek().r][que.peek().c] = que.peek().hp;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    continue;
                }

                if (map[nextR][nextC] == 'E') {
                    bw.write((curNode.count + 1) + "");
                    bw.flush();
                    bw.close();
                    return;
                }

                int nextUmbrella = curNode.umbrella;
                int nextHp = curNode.hp;
                if (map[nextR][nextC] == 'U') {
                    nextUmbrella = d;
                }
                Node nextNode = new Node(nextR, nextC, nextHp, nextUmbrella, curNode.count + 1);
                nextNode.removeHp();
                if (nextNode.hp == 0) {
                    continue;
                }
                if (visited[nextR][nextC] < nextNode.hp + nextNode.umbrella) {
                    visited[nextR][nextC] = nextNode.hp + nextNode.umbrella;
                    que.offer(nextNode);
                }
            }
        }

        bw.write("-1");
        bw.flush();
        bw.close();
    }
}

// 백트래킹 풀이: 시작점, 종점, 우산만 가지고 탐색
class Boj22944_2 {

    static class Node {
        int r, c, count;

        public Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    static int n, h, d;
    static int minCount = Integer.MAX_VALUE;
    static Node startNode;
    static Node endNode;
    static List<Node> umbrellas = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = line.charAt(j);

                if (c == 'S') {
                    startNode = new Node(i, j, 0);
                }
                if (c == 'E') {
                    endNode = new Node(i, j, 0);
                }
                if (c == 'U') {
                    umbrellas.add(new Node(i, j, 0));
                }
            }
        }
        visited = new boolean[umbrellas.size()];

        search(startNode, h, 0);

        if (minCount == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(minCount + "");
        }
        bw.flush();
        bw.close();
    }

    // 남은칸 보다 피가 동일하거나 크다면 갈 수 있음
    public static void search(Node curNode, int curHp, int curUmbrella) {
        int distance = getDistance(curNode, endNode);
        // 현재 지점에서 도착지까지 체력 + 우산으로 갈 수 있는 경우 최소 거리를 갱신함
        if (distance <= curHp + curUmbrella) {
            minCount = Math.min(minCount, curNode.count + distance);
            return;
        }

        for (int i = 0; i < umbrellas.size(); i++) {
            Node umbrella = umbrellas.get(i);
            int distanceToUmbrella = getDistance(curNode, umbrella);
            // 사용한 우산이거나, 우산 + 체력으로도 못간다면 다음 우산 탐색
            if (visited[i] || distanceToUmbrella > curHp + curUmbrella) {
                continue;
            }
            visited[i] = true;
            // 우산까지 가는데 현재 가지고 있는 우산으로 갈 수 있다면
            if (distanceToUmbrella <= curUmbrella) {
                search(new Node(umbrella.r, umbrella.c, curNode.count + distanceToUmbrella), curHp, d);
            } else {
                // 우산 + 체력까지 사용해야 한다면
                int nextHp = curHp - (distanceToUmbrella - curUmbrella);
                search(new Node(umbrella.r, umbrella.c, curNode.count + distanceToUmbrella), nextHp, d);
            }
            visited[i] = false;
        }
    }

    public static int getDistance(Node fromNode, Node endNode) {
        return Math.abs(fromNode.r - endNode.r) + Math.abs(fromNode.c - endNode.c);
    }

}