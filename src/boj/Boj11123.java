package boj;

import java.io.*;
import java.util.*;

class Boj11123 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    char c = line.charAt(j);
                    if (c == '.') {
                        map[i][j] = -1;
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 0) {
                        search(map, ++count, i, j, h, w);
                    }
                }
            }
            answer.append(count).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void search(int[][] map, int fill, int r, int c, int h, int w) {
        map[r][c] = fill;
        for (int i = 0; i < 4; i++) {
            int nextR = rows[i] + r;
            int nextC = cols[i] + c;

            if (nextR < 0 || nextR >= h || nextC < 0 || nextC >= w) {
                continue;
            }

            if (map[nextR][nextC] == 0) {
                search(map, fill, nextR, nextC, h, w);
            }
        }
    }
}
