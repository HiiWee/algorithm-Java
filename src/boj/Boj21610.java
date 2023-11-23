package boj;

import java.io.*;
import java.util.*;

class Boj21610 {
    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    private static final int[] dRows = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dCols = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    private static int n, m;
    private static int[][] map;
    private static boolean[][] preCloudCheck = new boolean[n][n];

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
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        preCloudCheck = new boolean[n][n];
        List<Node> clouds = List.of(new Node(n - 1, 0), new Node(n - 1, 1), new Node(n - 2, 0), new Node(n - 2, 1));
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            // 1. 구름 이동
            moveCloud(d, s, clouds);
            // 2. 구름 비내림
            dropRain(clouds);
            // 3. 구름 사라짐
            // 4. 물 복사 버그 시전
            copyWater(clouds);
            // 5. 새로운 구름 생성
            clouds = findClouds();

        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += map[i][j];
            }
        }

        bw.write(sum + "");
        bw.flush();
        bw.close();
    }

    public static void copyWater(List<Node> places) {
        for (Node place : places) {
            int count = 0;
            for (int i = 2; i <= 8; i += 2) {
                int nextR = place.r + dRows[i];
                int nextC = place.c + dCols[i];

                if (nextR < 0 || nextC < 0 || nextC >= n || nextR >= n) {
                    continue;
                }
                if (map[nextR][nextC] > 0) {
                    count++;
                }
            }
            map[place.r][place.c] += count;
        }
    }

    public static void dropRain(List<Node> clouds) {
        for (Node cloud : clouds) {
            map[cloud.r][cloud.c]++;
            preCloudCheck[cloud.r][cloud.c] = true;
        }
    }

    public static void moveCloud(int d, int s, List<Node> clouds) {
        for (Node cloud : clouds) {
            int newR = ((cloud.r + dRows[d] * s) + (n * 25)) % n;
            int newC = ((cloud.c + dCols[d] * s) + (n * 25)) % n;

            cloud.r = newR;
            cloud.c = newC;
        }
    }

    public static List<Node> findClouds() {
        List<Node> newClouds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 이전 구름 제외
                if (preCloudCheck[i][j]) {
                    preCloudCheck[i][j] = false;
                } else if (map[i][j] >= 2) {
                    map[i][j] -= 2;
                    // 현재 구름 세팅
                    newClouds.add(new Node(i, j));
                }
            }
        }
        return newClouds;
    }
}