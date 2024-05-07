package boj;

import java.io.*;

class Boj1553 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, 1, -1};

    static final int[][] map = new int[8][7];
    static final boolean[][] usedPieces = new boolean[7][7];
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 8; i++) {
            String line = br.readLine();
            for (int j = 0; j < 7; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        search(0, 0, new boolean[8][7]);

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static void search(int r, int c, boolean[][] visited) {
        if (c == 7) {
            c = 0;
            r++;
        }
        if (r == 8) {
            count++;
            return;
        }

        if (visited[r][c]) {
            search(r, c + 1, visited);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = r + rows[i];
            int nextC = c + cols[i];

            if (nextR < 0 || nextR >= 8 || nextC < 0 || nextC >= 7) {
                continue;
            }
            if (visited[nextR][nextC]) {
                continue;
            }

            if (usedPieces[map[r][c]][map[nextR][nextC]] || usedPieces[map[nextR][nextC]][map[r][c]]) {
                continue;
            }

            usedPieces[map[r][c]][map[nextR][nextC]] = true;
            usedPieces[map[nextR][nextC]][map[r][c]] = true;
            visited[r][c] = true;
            visited[nextR][nextC] = true;
            search(r, c + 1, visited);
            visited[r][c] = false;
            visited[nextR][nextC] = false;
            usedPieces[map[r][c]][map[nextR][nextC]] = false;
            usedPieces[map[nextR][nextC]][map[r][c]] = false;
        }
    }
}

