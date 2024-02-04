package boj;

import java.io.*;
import java.util.*;

// 가장 큰 것부터 차례대로 채우고 최소 카운트를 갱신했다면 최대 5개까지 사용하는 선에서 모든 경우를 탐색한다.
class Boj17136 {

    static int[][] map = new int[10][10];
    static int minCount = Integer.MAX_VALUE;
    static int[] sideCount = new int[6];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        fillPaper(0, 0, 0);

        if (minCount == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(Integer.toString(minCount));
        }
        bw.flush();
        bw.close();
    }

    public static void fillPaper(int r, int c, int count) {
        if (c == 10) {
            c = 0;
            r++;
        }
        if (r == 10) {
            minCount = Math.min(minCount, count);
            return;
        }
        if (map[r][c] == 0) {
            fillPaper(r, c + 1, count);
            return;
        }
        int side = getSide(r, c);

        for (int i = side; i > 0; i--) {
            if (sideCount[i] < 5) {
                fill(r, c, i);
                sideCount[i]++;
                fillPaper(r, c + 1, count + 1);
                remove(r, c, i);
                sideCount[i]--;
            }

        }
    }

    public static void remove(int r, int c, int side) {
        for (int i = r; i < r + side; i++) {
            for (int j = c; j < c + side; j++) {
                map[i][j] = 1;
            }
        }
    }

    public static void fill(int r, int c, int side) {
        for (int i = r; i < r + side; i++) {
            for (int j = c; j < c + side; j++) {
                map[i][j] = 0;
            }
        }
    }

    public static int getSide(int row, int col) {
        for (int i = 5; i > 0; i--) {
            if (row + i >= 11 || col + i >= 11) {
                continue;
            }
            boolean shouldPass = false;
            for (int r = row; r < row + i; r++) {
                for (int c = col; c < col + i; c++) {
                    if (map[r][c] == 0) {
                        shouldPass = true;
                        break;
                    }
                }
                if (shouldPass) {
                    break;
                }
            }
            if (!shouldPass) {
                return i;
            }
        }
        return 1;
    }
}