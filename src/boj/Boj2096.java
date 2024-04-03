package boj;

import java.io.*;
import java.util.*;

class Boj2096 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[][][] map = new int[n + 1][3][2]; // 최대, 최소
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            map[i][0][0] = Math.max(map[i - 1][0][0], map[i - 1][1][0]);
            map[i][0][1] = Math.min(map[i - 1][0][1], map[i - 1][1][1]);
            map[i][1][0] = Math.max(map[i - 1][0][0], Math.max(map[i - 1][1][0], map[i - 1][2][0]));
            map[i][1][1] = Math.min(map[i - 1][0][1], Math.min(map[i - 1][1][1], map[i - 1][2][1]));
            map[i][2][0] = Math.max(map[i - 1][2][0], map[i - 1][1][0]);
            map[i][2][1] = Math.min(map[i - 1][2][1], map[i - 1][1][1]);

            for (int j = 0; j < 3; j++) {
                int number = Integer.parseInt(st.nextToken());
                map[i][j][0] += number;
                map[i][j][1] += number;
            }
        }

        int max = Math.max(Math.max(map[n][0][0], map[n][1][0]), map[n][2][0]);
        int min = Math.min(Math.min(map[n][0][1], map[n][1][1]), map[n][2][1]);

        bw.write(max + " " + min);
        bw.flush();
        bw.close();
    }
}
