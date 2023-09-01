package boj;

import java.io.*;
import java.util.*;

class Boj4396 {
    private static int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] col = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static char[][] map;
    private static String[][] resultMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        resultMap = new String[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            Arrays.fill(resultMap[i], ".");
            for (int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                char value = line.charAt(j);
                if (value == 'x') {
                    setCount(i, j);
                }
            }
        }

        bw.write(getResult(resultMap));
        bw.flush();
        bw.close();
    }

    private static String getResult(final String[][] resultMap) {
        StringBuilder result = new StringBuilder();
        for (String[] resultRow : resultMap) {
            for (String resultCell : resultRow) {
                result.append(resultCell);
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static void fillFailResult() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == '*') {
                    resultMap[i][j] = "*";
                }
            }
        }
    }

    public static void setCount(int r, int c) {
        int n = map.length;

        if (map[r][c] == '*') {
            fillFailResult();
            return;
        }

        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nextR = r + row[i];
            int nextC = c + col[i];

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                continue;
            }
            if (map[nextR][nextC] == '*') {
                count++;
            }
        }
        resultMap[r][c] = String.valueOf(count);
    }
}