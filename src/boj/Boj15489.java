package boj;

import java.io.*;
import java.util.*;

class Boj15489 {

    static long[][] map = new long[31][31];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        map[1][1] = 1L;
        for (int i = 2; i <= 30; i++) {
            for (int j = 1; j <= i; j++) {
                map[i][j] = map[i - 1][j] + map[i - 1][j - 1];
            }
        }

        long sum = 0;
        for (int i = r; i < r + w; i++) {
            for (int j = c; j <= c + (i - r); j++) {
                sum += map[i][j];
            }
        }

        bw.write(Long.toString(sum));
        bw.flush();
        bw.close();
    }
}