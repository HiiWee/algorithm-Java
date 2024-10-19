package leetcode;

import java.util.*;

class LeetCode130 {

    int n, m;
    boolean[][] visited;
    char[][] board;
    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, 1, -1};

    public void solve(char[][] board) {
        this.board = board;
        n = board.length;
        m = board[0].length;
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && board[i][j] == 'O') {
                    search(i, j);
                }
            }
        }
    }

    public void search(int r, int c) {
        Queue<int[]> que = new ArrayDeque<>();
        List<int[]> nodes = new ArrayList<>();
        int[] start = {r, c};
        que.offer(start);
        nodes.add(start);
        visited[r][c] = true;

        boolean isNearByEdge = false;

        while (!que.isEmpty()) {
            int[] current = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = current[0] + rows[i];
                int nextC = current[1] + cols[i];

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) {
                    isNearByEdge = true;
                    continue;
                }
                if (!visited[nextR][nextC] && board[nextR][nextC] == 'O') {
                    visited[nextR][nextC] = true;
                    int[] node = {nextR, nextC};
                    que.offer(node);
                    nodes.add(node);
                }
            }
        }

        if (!isNearByEdge) {
            for (int[] node : nodes) {
                board[node[0]][node[1]] = 'X';
            }
        }
    }
}
