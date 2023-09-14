package boj;

import java.io.*;
import java.util.*;

class Boj16926 {
    private static int[] row = {0, 1, 0, -1}; // 우, 하, 좌, 상
    private static int[] col = {1, 0, -1, 0};
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int count = Math.min(n, m) / 2;
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (r-- > 0) {
            rotateMap(count);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result.append(map[i][j]).append(" ");
            }
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void rotateMap(int count) {
        for (int i = 0; i < count; i++) {
            int r = i;
            int c = i;
            int firstCell = map[i][i];
            for (int j = 0; j < 4; j++) {
                while (true) {
                    int nextR = r + row[j];
                    int nextC = c + col[j];

                    if (nextR >= map.length - i || nextR - i < 0 || nextC >= map[0].length - i || nextC - i < 0) {
                        break;
                    }

                    map[r][c] = map[nextR][nextC];
                    r = nextR;
                    c = nextC;
                }
            }
            map[i + 1][i] = firstCell;
        }
    }
}