package boj;

import java.io.*;
import java.util.*;

class Boj12100 {

    static int n;
    static int[][] map;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        permutations(new int[5], 0);

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }


    public static void permutations(int[] out, int count) {
        if (count == 5) {
            int[][] copyMap = copyMap(map);
            moveBlock(out, copyMap);
            max = Math.max(max, findMaxValue(copyMap));
            return;
        }

        for (int i = 0; i < 4; i++) {
            out[count] = i;
            permutations(out, count + 1);
        }
    }

    public static int[][] copyMap(int[][] map) {
        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    public static int findMaxValue(int[][] map) {
        int value = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                value = Math.max(value, map[i][j]);
            }
        }
        return value;
    }

    public static void moveBlock(int[] directions, int[][] map) {
        for (int direction : directions) {
            if (direction == 0) {
                up(map);
            } else if (direction == 1) {
                right(map);
            } else if (direction == 2) {
                down(map);
            } else {
                left(map);
            }
        }
    }

    public static void up(int[][] map) {
        for (int i = 0; i < n; i++) {
            int index = 0;
            int block = 0;
            for (int j = 0; j < n; j++) {
                if (map[j][i] == 0) {
                    continue;
                }
                if (block == map[j][i]) {
                    map[index - 1][i] *= 2;
                    map[j][i] = 0;
                    block = 0;
                } else {
                    block = map[j][i];
                    map[j][i] = 0;
                    map[index++][i] = block;
                }
            }
        }
    }

    public static void right(int[][] map) {
        for (int i = 0; i < n; i++) {
            int index = n - 1;
            int block = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (map[i][j] == 0) {
                    continue;
                }
                if (block == map[i][j]) {
                    map[i][index + 1] *= 2;
                    map[i][j] = 0;
                    block = 0;
                } else {
                    block = map[i][j];
                    map[i][j] = 0;
                    map[i][index--] = block;
                }
            }
        }
    }

    public static void down(int[][] map) {
        for (int i = 0; i < n; i++) {
            int index = n - 1;
            int block = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (map[j][i] == 0) {
                    continue;
                }
                if (block == map[j][i]) {
                    map[index + 1][i] *= 2;
                    map[j][i] = 0;
                    block = 0;
                } else {
                    block = map[j][i];
                    map[j][i] = 0;
                    map[index--][i] = block;
                }
            }
        }
    }

    public static void left(int[][] map) {
        for (int i = 0; i < n; i++) {
            int index = 0;
            int block = 0;
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                if (block == map[i][j]) {
                    map[i][index - 1] *= 2;
                    map[i][j] = 0;
                    block = 0;
                } else {
                    block = map[i][j];
                    map[i][j] = 0;
                    map[i][index++] = block;
                }
            }
        }
    }
}