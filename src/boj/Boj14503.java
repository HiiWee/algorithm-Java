package boj;

import java.io.*;
import java.util.*;

class Boj14503 {

    static class Node {
        int r, c, d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    // 북, 동, 남, 서
    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, 1, 0, -1};
    static int n, m;
    static int[][] map;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        st = new StringTokenizer(br.readLine());
        Node cleaner = new Node(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        );

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean(cleaner);

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static void clean(Node cleaner) {
        // 벽이라면 종료함
        if (map[cleaner.r][cleaner.c] == 1) {
            return;
        }
        // 청소되지 않았다면
        if (map[cleaner.r][cleaner.c] == 0) {
            map[cleaner.r][cleaner.c] = 2;
            count++;
        }

        // 현재 주변 4칸에 청소되지 않은 빈 칸이 있다면
        if (hasDirtyArea(cleaner)) {
            for (int i = 0; i < 4; i++) {
                cleaner.d = (cleaner.d - 1 + 4) % 4; // 반시계방향 회전
                // 바라보는 방향 앞쪽 칸이 청소되지 않은 빈 칸이라면 한 칸 전진하고 종료
                Node nextCleaner = moveForward(cleaner);
                if (map[nextCleaner.r][nextCleaner.c] == 0) {
                    clean(nextCleaner);
                    break;
                }
            }
        } else {
            Node nextCleaner = moveBack(cleaner);
            clean(nextCleaner);
        }
    }

    public static Node moveBack(Node node) {
        int oppositeDirection = (node.d + 2) % 4;
        int nextR = node.r + rows[oppositeDirection];
        int nextC = node.c + cols[oppositeDirection];
        // 바라보는 방향은 유지하고 반대로 이동한 청소기
        return new Node(nextR, nextC, node.d);
    }

    public static boolean isNextAreaDirty(Node node) {
        int nextR = node.r + rows[node.d];
        int nextC = node.c + cols[node.d];
        return map[nextR][nextC] == 0;
    }

    public static Node moveForward(Node node) {
        int nextR = node.r + rows[node.d];
        int nextC = node.c + cols[node.d];
        return new Node(nextR, nextC, node.d);
    }

    public static boolean hasDirtyArea(Node node) {
        for (int i = 0; i < 4; i++) {
            int nextR = node.r + rows[i];
            int nextC = node.c + cols[i];

            if (map[nextR][nextC] == 0) {
                return true;
            }
        }
        return false;
    }
}