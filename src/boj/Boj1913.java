package boj;

import java.io.*;
import java.util.*;

class Boj1913 {
    private static int[] row = {1, 0, -1, 0}; // 하 우 상 좌
    private static int[] col = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int currentNumber = n * n;

        int targetR = 0;
        int targetC = 0;
        int r = 0;
        int c = 0;
        int nextMove = 0;
        while (currentNumber >= 1) {
            if (currentNumber == target) {
                targetR = r + 1;
                targetC = c + 1;
            }
            map[r][c] = currentNumber--;

            int nextR = r + row[nextMove];
            int nextC = c + col[nextMove];

            if ((nextR < 0 || nextR >= n || nextC < 0 || nextC >= n)
                    || (map[nextR][nextC] != 0)) {
                nextMove = (nextMove + 1) % 4;
                nextR = r + row[nextMove];
                nextC = c + col[nextMove];
            }
            r = nextR;
            c = nextC;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result.append(map[i][j]).append(" ");
            }
            result.append("\n");
        }
        result.append(targetR)
                .append(" ")
                .append(targetC);

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}