package boj;

import java.io.*;
import java.util.*;

class Boj2234 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 서, 북, 동, 남
    static final int[] rows = {0, -1, 0, 1};
    static final int[] cols = {-1, 0, 1, 0};

    static int m, n;
    static int[][] map;
    static int[][] fillMap;
    static Map<Integer, Integer> roomCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        fillMap = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1, 2 동시에 구하기
        int roomCount = 0;
        int maxSize = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    roomCount++;
                    maxSize = Math.max(maxSize, searchRoom(i, j, visited, roomCount));
                }
            }
        }

        // 3. 인접한 방의 조합 모두 찾아서 최대합 구하기
        int maxSumSize = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int number = fillMap[i][j];

                if (i > 0 && number != fillMap[i - 1][j]) {
                    maxSumSize = Math.max(maxSumSize, roomCounts.get(number) + roomCounts.get(fillMap[i - 1][j]));
                }

                if (j > 0 && number != fillMap[i][j - 1]) {
                    maxSumSize = Math.max(maxSumSize, roomCounts.get(number) + roomCounts.get(fillMap[i][j - 1]));
                }
            }
        }

        bw.write(roomCount + "\n" + maxSize + "\n" + maxSumSize);
        bw.flush();
        bw.close();
    }

    public static int searchRoom(int r, int c, boolean[][] visited, int fillNumber) {
        int count = 1;
        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(r, c));
        visited[r][c] = true;
        fillMap[r][c] = fillNumber;
        while (!que.isEmpty()) {

            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                // 해당 방향에 벽이 있다면 패스
                if ((map[curNode.r][curNode.c] & (1 << i)) > 0) {
                    continue;
                }
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n) {
                    continue;
                }

                if (!visited[nextR][nextC]) {
                    count++;
                    que.offer(new Node(nextR, nextC));
                    visited[nextR][nextC] = true;
                    fillMap[nextR][nextC] = fillNumber;
                }
            }
        }
        roomCounts.put(fillNumber, count);
        return count;
    }
}
