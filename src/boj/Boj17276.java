package boj;

import java.io.*;
import java.util.*;

class Boj17276 {
    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int[][] map;
    private static int[][] copyMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int degree = Integer.parseInt(st.nextToken());
            int[][] rotates = new int[4][n];
            for (int i = 0; i < 4; i++) {

            }
            map = new int[n][n];
            copyMap = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (degree > 0) {
                rotateClockWay(degree / 45);
            } else {
                rotateReverseClockWay(Math.abs(degree) / 45);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result.append(map[i][j]).append(" ");
                }
                result.append("\n");
            }
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void rotateClockWay(int count) {
        Pos[][] rotation = createRotation(map.length);
        copyArray(map, copyMap);
        while (count-- > 0) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (i != 3) {
                        copyMap[rotation[(i + 1) % 4][j].r][rotation[(i + 1) % 4][j].c] = map[rotation[i][j].r][rotation[i][j].c];
                    } else {
                        copyMap[rotation[(i + 1) % 4][map.length - 1 - j].r][rotation[(i + 1) % 4][map.length - 1 - j].c] = map[rotation[i][j].r][rotation[i][j].c];
                    }
                }
            }
            copyArray(copyMap, map);
        }
    }

    public static void rotateReverseClockWay(int count) {
        Pos[][] rotation = createRotation(map.length);
        copyArray(map, copyMap);
        while (count-- > 0) {
            for (int i = 3; i >= 0; i--) {
                for (int j = 0; j < map.length; j++) {
                    if (i != 0) {
                        copyMap[rotation[i - 1][j].r][rotation[i - 1][j].c] = map[rotation[i][j].r][rotation[i][j].c];
                    } else {
                        copyMap[rotation[3][map.length - 1 - j].r][rotation[3][map.length - 1 - j].c] = map[rotation[i][j].r][rotation[i][j].c];
                    }
                }
            }
            copyArray(copyMap, map);
        }
    }

    // 가운데 행, 좌측 대각선, 가운데열, 우측 대각선 순서
    public static Pos[][] createRotation(int length) {
        Pos[][] rotation = new Pos[4][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                rotation[0][j] = new Pos(length / 2, j);
                rotation[1][j] = new Pos(j, j);
                rotation[2][j] = new Pos(j, length / 2);
                rotation[3][j] = new Pos(j, length - 1 - j);
            }
        }
        return rotation;
    }

    public static void copyArray(int[][] from, int[][] to) {
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from.length; j++) {
                to[i][j] = from[i][j];
            }
        }
    }
}