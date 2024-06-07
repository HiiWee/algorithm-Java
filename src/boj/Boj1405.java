package boj;

import java.io.*;
import java.util.*;

class Boj1405 {

    static final int[] rows = {0, 0, 1, -1};
    static final int[] cols = {-1, 1, 0, 0};

    static int n;
    static double[] dir = new double[4];
    static boolean[][] map = new boolean[31][31];
    static double sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            dir[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }

        map[15][15] = true;
        search(0, 15, 15, 1.0);

        bw.write(Double.toString(sum));
        bw.flush();
        bw.close();
    }

    public static void search(int count, int r, int c, double percent) {
        if (count == n) {
            sum += percent;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (dir[i] == 0) {
                continue;
            }

            int nextR = r + rows[i];
            int nextC = c + cols[i];

            if (!map[nextR][nextC]) {
                map[nextR][nextC] = true;
                search(count + 1, nextR, nextC, percent * dir[i]);
                map[nextR][nextC] = false;
            }
        }
    }
}
