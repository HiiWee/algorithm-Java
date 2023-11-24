package boj;

import java.io.*;
import java.util.*;

class Boj17144 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    private static int r, c, t;
    private static int[][] map;
    private static final List<Node> airCleaners = new ArrayList<>();

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                int number = Integer.parseInt(st.nextToken());
                if (number == -1) {
                    airCleaners.add(new Node(i, j));
                }
                map[i][j] = number;
            }
        }

        while (t-- > 0) {
            // 1. 미세먼지 확산
            diffuseFineDust();

            // 2. 공기청정기 작동
            Node upCleaner = airCleaners.get(0);
            Node downCleaner = airCleaners.get(1);

            workAirCleaner(new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}, upCleaner, 0, upCleaner.r + 1);
            workAirCleaner(new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}, downCleaner, downCleaner.r, r);
        }

        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sum += map[i][j];
            }
        }
        sum += 2;

        bw.write(sum + "");
        bw.flush();
        bw.close();
    }

    public static void workAirCleaner(int[][] directions, Node airCleaner, int rStart, int rEnd) {

        int index = 0;
        int preR = airCleaner.r;
        int preC = airCleaner.c;
        int nextR = preR;
        int nextC = preC;

        while (true) {
            nextR += directions[index][0];
            nextC += directions[index][1];

            if (nextR == airCleaner.r && nextC == airCleaner.c) {
                break;
            }
            if (nextR < rStart || nextC < 0 || nextR >= rEnd || nextC >= c) {
                nextR -= directions[index][0];
                nextC -= directions[index][1];
                index++;
                continue;
            }
            map[preR][preC] = map[nextR][nextC];
            map[nextR][nextC] = 0;
            preR = nextR;
            preC = nextC;
        }
        map[airCleaner.r][airCleaner.c] = -1;
    }

    public static void diffuseFineDust() {
        int[][] diffusionMap = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 0 || map[i][j] == -1) {
                    continue;
                }
                int value = map[i][j] / 5;
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nextR = rows[k] + i;
                    int nextC = cols[k] + j;

                    if (nextR < 0 || nextR >= r || nextC < 0 || nextC >= c) {
                        continue;
                    }
                    if (map[nextR][nextC] > -1) {
                        diffusionMap[nextR][nextC] += value;
                        count++;
                    }
                }
                map[i][j] -= count * value;
            }
        }

        mergeMap(diffusionMap);
    }

    public static void mergeMap(int[][] diffusionMap) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += diffusionMap[i][j];
            }
        }
    }
}
