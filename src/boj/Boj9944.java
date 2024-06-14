package boj;

import java.io.*;
import java.util.*;

class Boj9944 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};
    static final String FORMAT = "Case %d: %d\n";

    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static int areaCount;
    static int minCount = Integer.MAX_VALUE;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        int tryCount = 0;
        StringBuilder answer = new StringBuilder();

        while ((line = br.readLine()) != null) {
            tryCount++;
            StringTokenizer st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n][m];
            visited = new boolean[n][m];
            areaCount = 0;

            for (int i = 0; i < n; i++) {
                String mapLine = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = mapLine.charAt(j);
                    if (map[i][j] == '.') {
                        areaCount++;
                    }

                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '.') {
                        count = Integer.MAX_VALUE;
                        visited[i][j] = true;
                        search(i, j, 1, 0);
                        if (count != -1) {
                            minCount = Math.min(minCount, count);
                        }
                        visited[i][j] = false;
                    }
                }
            }

            if (minCount == Integer.MAX_VALUE) {
                answer.append(String.format(FORMAT, tryCount, -1));
            } else {
                answer.append(String.format(FORMAT, tryCount, minCount));
            }
            minCount = Integer.MAX_VALUE;
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void search(int r, int c, int sum, int currentCount) {
        if (sum == areaCount) {
            count = Math.min(currentCount, count);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = r + rows[i];
            int nextC = c + cols[i];

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                continue;
            }

            if (!visited[nextR][nextC] && map[nextR][nextC] == '.') {
                int moveCount = goStraight(r, c, i);
                search(r + rows[i] * moveCount, c + cols[i] * moveCount, sum + moveCount, currentCount + 1);
                rollBack(r, c, i, moveCount);
            }
        }
    }

    public static int goStraight(int r, int c, int dir) {
        int count = 0;
        while (true) {
            r += rows[dir];
            c += cols[dir];

            if (r < 0 || r >= n || c < 0 || c >= m || visited[r][c] || map[r][c] == '*') {
                break;
            }
            visited[r][c] = true;
            count++;
        }

        return count;
    }

    public static void rollBack(int r, int c, int dir, int moveCount) {
        int lastR = r + rows[dir] * moveCount;
        int lastC = c + cols[dir] * moveCount;
        while (!(lastR == r && lastC == c)) {
            visited[lastR][lastC] = false;

            lastR -= rows[dir];
            lastC -= cols[dir];
        }
    }
}
