package boj;

import java.io.*;
import java.util.*;

class Boj20208 {

    static int[] home;
    static int n, m, h;
    static int[][] map;
    static int max;
    static List<int[]> mints = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        home = new int[2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    home[0] = i;
                    home[1] = j;
                } else if (map[i][j] == 2) {
                    mints.add(new int[]{i, j});
                }
            }
        }

        search(0, home, m, 0);

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }

    public static void search(int index, int[] current, int hp, int count) {
        if (hp >= getDistanceFrom(current, home)) {
            max = Math.max(count, max);
        }

        if (index == mints.size()) {
            return;
        }

        for (int[] mint : mints) {
            if (map[mint[0]][mint[1]] == 2) {
                int toMint = getDistanceFrom(current, mint);
                if (hp >= toMint) {
                    map[mint[0]][mint[1]] = 0;
                    search(index + 1, mint, hp - toMint + h, count + 1);
                    map[mint[0]][mint[1]] = 2;
                }
            }
        }
    }

    private static int getDistanceFrom(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }
}
