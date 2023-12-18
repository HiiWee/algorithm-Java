package boj;

import java.io.*;
import java.util.*;

class Boj5212 {

    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, 1, 0, -1};

    static int r, c;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        bw.write(floodWater());
        bw.flush();
        bw.close();
    }
    public static String floodWater() {
        char[][] copyMap = new char[r][c];

        int minR = Integer.MAX_VALUE;
        int maxR = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE;
        int maxC = Integer.MIN_VALUE;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != 'X') {
                    copyMap[i][j] = '.';
                    continue;
                }
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nextR = i + rows[k];
                    int nextC = j + cols[k];

                    if (nextR < 0 || nextR >= r || nextC < 0 || nextC >= c) {
                        count++;
                        continue;
                    }
                    if (map[nextR][nextC] == '.') {
                        count++;
                    }
                }
                if (count >= 3) {
                    copyMap[i][j] = '.';
                } else {
                    copyMap[i][j] = 'X';
                    maxR = Math.max(maxR, i);
                    maxC = Math.max(maxC, j);
                    minR = Math.min(minR, i);
                    minC = Math.min(minC, j);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = minR; i <= maxR; i++) {
            for (int j = minC; j <= maxC; j++) {
                result.append(copyMap[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }
}