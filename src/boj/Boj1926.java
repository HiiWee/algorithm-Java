package boj;

import java.io.*;
import java.util.*;

class Boj1926 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int n, m;
    static int[][] map;
    static int count;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    count++;
                    max = Math.max(max, search(i, j));
                }
            }
        }

        bw.write(count + "\n" + max);
        bw.flush();
        bw.close();
    }

    public static int search(int r, int c) {
        int count = 1;
        map[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            int nextR = r + rows[i];
            int nextC = c + cols[i];

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                continue;
            }

            if (map[nextR][nextC] == 1) {
                count += search(nextR, nextC);
            }
        }

        return count;
    }
}
