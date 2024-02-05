package boj;

import java.io.*;
import java.util.*;

class Boj1799 {

    static final int[] rows = {1, 1, -1, -1};
    static final int[] cols = {1, -1, -1, 1};

    static int n;
    static int[][] map;
    static int maxCount;
    static int totalCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 흑색칸 0부터 시작
        search(0, 0, 0, 0);
        totalCount += maxCount;
        maxCount = 0;
        // 백색칸 1부터 시작
        search(0, 1, 0, 1);
        totalCount += maxCount;

        bw.write(totalCount + "");
        bw.flush();
        bw.close();
    }

    public static void search(int row, int col, int count, int color) {
        if (col >= n) {
            col = nextCol(color, ++row);
        }
        if (row == n) {
            maxCount = Math.max(count, maxCount);
            return;
        }

        if (map[row][col] == 0) {
            search(row, col + 2, count, color);
            return;
        }

        if (isPossible(row, col)) {
            map[row][col] = 2;
            search(row, col + 2, count + 1, color);
            map[row][col] = 1;
        }
        // 현재 위치에 놓지 않는 경우가 더 많이 놓을 수 있다.
        search(row, col + 2, count, color);
    }

    public static int nextCol(int color, int row) {
        if (color == 0) {
            if (row % 2 == 0) {
                return 0;
            }
            return 1;
        }
        if (row % 2 == 0) {
            return 1;
        }
        return 0;

    }

    public static boolean isPossible(int row, int col) {
        for (int k = 0; k < 4; k++) {
            int nextR = row;
            int nextC = col;
            for (int i = 0; i < n; i++) {
                nextR += rows[k];
                nextC += cols[k];
                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    break;
                }
                if (map[nextR][nextC] == 2) {
                    return false;
                }
            }
        }
        return true;
    }
}