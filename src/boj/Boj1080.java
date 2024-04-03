package boj;

import java.io.*;
import java.util.*;

class Boj1080 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] from = new int[n][m];
        int[][] to = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                from[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                to[i][j] = line.charAt(j) - '0';
            }
        }

        int count = 0;
        for (int i = 0; i + 2 < n; i++) {
            for (int j = 0; j + 2 < m; j++) {
                if (from[i][j] != to[i][j]) {
                    count++;
                    flip(i, j, from);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (from[i][j] != to[i][j]) {
                    bw.write("-1");
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static void flip(int r, int c, int[][] map) {
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                map[i][j] = (map[i][j] + 1) % 2;
            }
        }
    }
}
