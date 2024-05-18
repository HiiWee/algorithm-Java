package boj;

import java.io.*;
import java.util.*;

class Boj3184 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int r, c;
    static char[][] map;
    static boolean[][] visited;
    static int wolf, sheep, wolfCount, sheepCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
            }
        }


        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (!visited[i][j] && map[i][j] != '#') {
                    dfs(i, j);

                    if (sheepCount > wolfCount) {
                        sheep += sheepCount;
                    } else {
                        wolf += wolfCount;
                    }
                    wolfCount = 0;
                    sheepCount = 0;
                }
            }
        }

        bw.write(sheep + " " + wolf);
        bw.flush();
        bw.close();
    }

    public static void dfs(int startR, int startC) {
        visited[startR][startC] = true;

        if (map[startR][startC] == 'v') {
            wolfCount++;
        } else if (map[startR][startC] == 'o') {
            sheepCount++;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = startR + rows[i];
            int nextC = startC + cols[i];

            if (nextR < 0 || nextR >= r || nextC < 0 || nextC >= c) {
                continue;
            }

            if (!visited[nextR][nextC] && map[nextR][nextC] != '#') {
                dfs(nextR, nextC);
            }
        }

    }
}
