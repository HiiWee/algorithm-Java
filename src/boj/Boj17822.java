package boj;

/*
    원판은 2차원 배열로 나타낼 수 있음
    하나의 원판에서의 인접은 N열은, N - 1열과 1열과 인접하다는 것과, i행은, i + 1행, i - 1행과 인접하다는것으로 표현됨
    즉, 2차원 배열에서 열들은 순환함

    - 시계 방향 N칸 회전 -> 배열에서 열을 N칸씩 우로 이동
    - 반시계 방향 N칸 회전 -> 배열에서 열을 N칸씩 좌로 이동

    원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다. (BFS)
    그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
    없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
*/
import java.io.*;
import java.util.*;

class Boj17822 {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    private static int n, m, t;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (d == 0) {
                rotate(x, k);
            } else {
                rotate(x, -k);
            }

            // 원판에 수가 있다면 지우기
            boolean hasNearNumber = false;
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && map[i][j] != 0) {
                        hasNearNumber |= bfs(visited, new Node(i, j));
                    }
                }
            }

            if (!hasNearNumber) {
                updateNumber();
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += map[i][j];
            }
        }

        bw.write(sum + "");
        bw.flush();
        bw.close();
    }


    public static void updateNumber() {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    count++;
                }
                sum += map[i][j];
            }
        }

        double avg = sum / (double) count;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                if (map[i][j] > avg) {
                    map[i][j]--;
                } else if (map[i][j] < avg) {
                    map[i][j]++;
                }
            }
        }
    }

    public static boolean bfs(boolean[][] visited, Node startNode) {
        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);
        visited[startNode.r][startNode.c] = true;

        int targetNumber = map[startNode.r][startNode.c];
        map[startNode.r][startNode.c] = 0;
        int count = 1;
        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = (curNode.c + cols[i] + m) % m;

                if (nextR < 0 || nextR >= n) {
                    continue;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC] == targetNumber) {
                    count++;
                    visited[nextR][nextC] = true;
                    map[nextR][nextC] = 0;
                    que.offer(new Node(nextR, nextC));
                }
            }
        }

        if (count == 1) {
            map[startNode.r][startNode.c] = targetNumber;
            return false;
        }
        return true;
    }

    public static void rotate(int x, int k) {
        int[] index = new int[m];
        for (int i = 0; i < n; i++) {
            if ((i + 1) % x != 0) {
                continue;
            }
            for (int j = 0; j < m; j++) {
                index[(j + k + m) % m] = map[i][j];
            }
            for (int j = 0; j < m; j++) {
                map[i][j] = index[j];
            }
        }
    }
}