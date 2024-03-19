package boj;

import java.io.*;
import java.util.*;

class Boj15684 {

    static int n, m, h;
    static boolean[][] map;
    static boolean isFinish;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new boolean[h][n];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = true;
        }

        int answer = -1;
        for (int i = 0; i <= 3; i++) {
            search(0, 0, i);
            if (isFinish) {
                answer = i;
                break;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
    }

    public static void search(int r, int count, int target) {
        if (isFinish) {
            return;
        }
        if (count == target) {
            if (isPossible()) {
                isFinish = true;
            }
            return;
        }

        for (int i = r; i < h; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (canSetRow(i, j)) {
                    map[i][j] = true;
                    search(i, count + 1, target);
                    map[i][j] = false;
                }
            }
        }
    }

    // 사다리타기가 전부 가능한지 확인
    public static boolean isPossible() {
        for (int i = 0; i < n; i++ ) {
            if (climb(i) != i) {
                return false;
            }
        }
        return true;
    }

    public static int climb(int col) {
        for (int i = 0; i < h; i++) {
            if (map[i][col]) {
                col++;
            } else if (col != 0 && map[i][col - 1]) {
                col--;
            }
        }
        return col;
    }

    public static boolean canSetRow(int r, int c) {
        if (c == 0) {
            return !map[r][c] && !map[r][c + 1];
        } else if (c == n - 1) {
            return !map[r][c] && !map[r][c - 1];
        }
        return !map[r][c] && !map[r][c - 1] && !map[r][c + 1];
    }
}
