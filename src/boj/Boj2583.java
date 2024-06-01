package boj;

import java.io.*;
import java.util.*;

class Boj2583 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int n, m, k;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            for (int r = r1; r < r2; r++) {
                for (int c = c1; c < c2; c++) {
                    map[r][c] = 1;
                }
            }
        }
        int count = 0;
        List<Integer> areas = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count++;
                    areas.add(dfs(i, j));
                }
            }
        }

        areas.sort(Comparator.naturalOrder());
        StringBuilder answer = new StringBuilder();
        answer.append(count).append("\n");
        for (int area : areas) {
            answer.append(area).append(" ");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static int dfs(int r, int c) {
        int count = 1;
        map[r][c] = 1;
        for (int i = 0; i < 4; i++) {
            int nextR = r + rows[i];
            int nextC = c + cols[i];

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                continue;
            }

            if (map[nextR][nextC] == 0) {
                count += dfs(nextR, nextC);
            }
        }

        return count;
    }
}
