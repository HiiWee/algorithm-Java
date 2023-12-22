package boj;

import java.io.*;
import java.util.*;

class Boj20165 {

    static final int[] rows = {0, 0, 1, -1};
    static final int[] cols = {1, -1, 0, 0};

    static int n, m, r;
    static int[][] map;
    static boolean[][] fallMap;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        fallMap = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (r-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = findDirection(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int defenseR = Integer.parseInt(st.nextToken()) - 1;
            int defenseC = Integer.parseInt(st.nextToken()) - 1;

            // 도미노 쓰러트리기
            attack(r, c, d);
            fallMap[defenseR][defenseC] = false;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (fallMap[i][j]) {
                    result.append("F ");
                } else {
                    result.append("S ");
                }
            }
            result.append("\n");
        }

        bw.write(count + "\n" + result);
        bw.flush();
        bw.close();
    }

    public static void attack(int r, int c, int d) {
        // 이미 쓰러져 있다면 아무일도 일어나지 않음
        if (fallMap[r][c]) {
            return;
        }
        count++;
        fallMap[r][c] = true;
        int iterCount = map[r][c];
        for (int i = 1; i < iterCount; i++) {
            int nextR = r + rows[d] * i;
            int nextC = c + cols[d] * i;

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                continue;
            }
            attack(nextR, nextC, d);
        }
    }

    public static int findDirection(String command) {
        if ("E".equals(command)) {
            return 0;
        } else if ("W".equals(command)) {
            return 1;
        } else if ("S".equals(command)) {
            return 2;
        }
        return 3;
    }
}