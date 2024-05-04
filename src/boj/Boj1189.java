package boj;

import java.io.*;
import java.util.*;

class Boj1189 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int r, c, k;
    static int[][] map;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                char c = line.charAt(j);
                if (c == 'T') {
                    map[i][j] = -1;
                }
            }
        }

        map[r - 1][0] = 1;
        search(r - 1, 0);

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
    }

    public static void search(int row, int col) {
        if (map[row][col] > k) {
            return;
        }
        if (row == 0 && col == c - 1) {
            if (map[row][col] == k) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = rows[i] + row;
            int nextC = cols[i] + col;

            if (nextR < 0 || nextR >= r || nextC < 0 || nextC >= c) {
                continue;
            }
            if (map[nextR][nextC] == 0) {
                map[nextR][nextC] = map[row][col] + 1;
                search(nextR, nextC);
                map[nextR][nextC] = 0;
            }
        }
    }
}
