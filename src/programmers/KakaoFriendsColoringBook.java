package programmers;

import java.util.*;

public class KakaoFriendsColoringBook {

    static class Solution_DFS {
        boolean[][] visited;
        int[] row = {-1, 0, 1, 0};
        int[] col = {0, 1, 0, -1};
        int M, N;
        int maxCount = 0;
        int count = 1;

        public int[] solution(int m, int n, int[][] picture) {
            visited = new boolean[m][n];
            M = m;
            N = n;

            int numberOfArea = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (picture[i][j] != 0 && !visited[i][j]) {
                        DFS(picture, i, j);
                        maxCount = Math.max(maxCount, count);
                        numberOfArea++;
                        count = 1;
                    }
                }
            }
            return new int[]{numberOfArea, maxCount};

        }

        public void DFS(int[][] picture, int r, int c) {
            visited[r][c] = true;

            for (int i = 0; i < 4; i++) {
                int nextR = r + row[i];
                int nextC = c + col[i];
                if (nextR < 0 || nextR >= M || nextC < 0 || nextC >= N) {
                    continue;
                }
                if (!visited[nextR][nextC] && picture[r][c] == picture[nextR][nextC]) {
                    count++;
                    DFS(picture, nextR, nextC);
                }
            }
        }
    }

    static class Solution_BFS {
        static class Pos {
            int r, c;

            public Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        boolean[][] visited;
        int[] row = {-1, 0, 1, 0};
        int[] col = {0, 1, 0, -1};
        int M, N;
        int maxCount = 0;
        int count = 1;

        public int[] solution(int m, int n, int[][] picture) {
            visited = new boolean[m][n];
            M = m;
            N = n;

            int numberOfArea = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (picture[i][j] != 0 && !visited[i][j]) {
                        BFS(picture, i, j);
                        maxCount = Math.max(maxCount, count);
                        numberOfArea++;
                        count = 1;
                    }
                }
            }

            return new int[]{numberOfArea, maxCount};
        }

        public void BFS(int[][] arr, int r, int c) {
            Queue<Pos> que = new LinkedList<>();
            visited[r][c] = true;
            que.add(new Pos(r, c));

            while (!que.isEmpty()) {
                Pos p = que.poll();

                for (int i = 0; i < 4; i++) {
                    int nextR = p.r + row[i];
                    int nextC = p.c + col[i];

                    if (nextR < 0 || nextR >= M || nextC < 0 || nextC >= N) {
                        continue;
                    }

                    if (!visited[nextR][nextC] && arr[r][c] == arr[nextR][nextC]) {
                        que.add(new Pos(nextR, nextC));
                        visited[nextR][nextC] = true;
                        count++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution_DFS solution_dfs = new Solution_DFS();
        Solution_BFS solution_bfs = new Solution_BFS();

        int m = 6;
        int n = 4;
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        int[] answers = {4, 5};


        System.out.println("solution_dfs = " + solution_dfs);
        int flag = 0;
        int[] answerDFS = solution_dfs.solution(m, n, picture);
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == answerDFS[i]) {
                flag++;
            }
        }
        if (flag == 2) {
            System.out.println("Test is passed");
        }
        System.out.println();


        System.out.println("solution_bfs = " + solution_bfs);
        flag = 0;
        int[] answerBFS = solution_dfs.solution(m, n, picture);
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == answerBFS[i]) {
                flag++;
            }
        }
        if (flag == 2) {
            System.out.println("Test is passed");
        }
    }
}
