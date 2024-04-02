package boj;

import java.io.*;
import java.util.*;

class Boj1455 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int count = 0;
        for (int j = m - 1; j >= 0; j--) {
            for (int i = n - 1; i >= 0; i--) {
                if (map[i][j] == 1) {
                    flip(i, j, map);
                    count++;
                }
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static void flip(int r, int c, int[][] map) {
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                map[i][j] = (map[i][j] + 1) % 2;
            }
        }
    }
}
