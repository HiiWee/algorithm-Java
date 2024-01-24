package programmers;

import java.util.*;

class 아이템_줍기 {

    static class Node {
        int r, c, count;

        public Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    int[][] map = new int[102][102];
    final int[] outRows = {-1, -1, -1, 0, 0, 1, 1, 1};
    final int[] outCols = {-1, 0, 1, -1, 1, -1, 0, 1};
    final int[] rows = {-1, 0, 1, 0};
    final int[] cols = {0, 1, 0, -1};

    public int solution(int[][] rectangles, int characterX, int characterY, int itemX, int itemY) {
        for (int[] rectangle : rectangles) {
            int x1 = rectangle[0];
            int y1 = rectangle[1];
            int x2 = rectangle[2];
            int y2 = rectangle[3];
            for (int i = y1 * 2; i <= y2 * 2; i++) {
                for (int j = x1 * 2; j <= x2 * 2; j++) {
                    map[i][j] = 1;
                }
            }
        }

        return bfs(new Node(characterY * 2, characterX * 2, 0), new Node(itemY * 2, itemX * 2, 0));
    }

    public int bfs(Node start, Node end) {
        boolean[][] visited = new boolean[101][101];
        visited[start.r][start.c] = true;

        Queue<Node> que = new LinkedList<>();
        que.offer(start);

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.r == end.r && curNode.c == end.c) {
                return curNode.count / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (map[nextR][nextC] == 0 || !isEdge(nextR, nextC) || visited[nextR][nextC]) {
                    continue;
                }

                que.offer(new Node(nextR, nextC, curNode.count + 1));
                visited[nextR][nextC] = true;
            }
        }

        return -1;
    }

    public boolean isEdge(int r, int c) {
        for (int i = 0; i < 8; i++) {
            int nextR = r + outRows[i];
            int nextC = c + outCols[i];

            if (map[nextR][nextC] == 0) {
                return true;
            }
        }
        return false;
    }
}