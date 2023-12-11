package boj;

import java.io.*;
import java.util.*;

class Boj19236 {

    static class Fish {
        int r, c, dir;
        boolean isAlive;

        Fish(int r, int c, int dir, boolean isAlive) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static class Shark {
        int r, c, dir, sum;

        Shark(int r, int c, int dir, int sum) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.sum = sum;
        }
    }

    private static final int[] rows = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] cols = {0, -1, -1, -1, 0, 1, 1, 1};
    private static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] map = new int[4][4];
        Fish[] fishes = new Fish[17];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int fishNumber = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                map[i][j] = fishNumber;
                fishes[fishNumber] = new Fish(i, j, dir - 1, true);
            }
        }

        int fishNumber = map[0][0];
        map[0][0] = 0;
        fishes[fishNumber].isAlive = false;
        startMove(map, fishes, new Shark(0, 0, fishes[fishNumber].dir, fishNumber));

        bw.write(max + "");
        bw.flush();
        bw.close();
    }

    public static void startMove(int[][] map, Fish[] fishes, Shark shark) {
        int[][] copyMap = copyMap(map);
        Fish[] copyFishes = copyFishes(fishes);
        max = Math.max(max, shark.sum);

        moveFish(copyMap, copyFishes, shark);

        for (int i = 1; i < 4; i++) {
            int nextR = shark.r + rows[shark.dir] * i;
            int nextC = shark.c + cols[shark.dir] * i;

            if (nextR < 0 || nextC < 0 || nextR >= 4 || nextC >= 4) {
                break;
            }
            if (copyMap[nextR][nextC] != 0) {
                int fishNumber = copyMap[nextR][nextC];
                copyMap[nextR][nextC] = 0;
                copyFishes[fishNumber].isAlive = false;
                startMove(copyMap, copyFishes, new Shark(nextR, nextC, copyFishes[fishNumber].dir, shark.sum + fishNumber));

                copyMap[nextR][nextC] = fishNumber;
                copyFishes[fishNumber].isAlive = true;
            }
        }
    }

    public static void moveFish(int[][] map, Fish[] fishes, Shark shark) {
        for (int i = 1; i <= 16; i++) {
            Fish fish = fishes[i];

            // 이미 죽은 물고기나, 상어의 위치라면 불가능
            if (!fish.isAlive || fish.r == shark.r && fish.c == shark.c) {
                continue;
            }
            for (int j = 0; j < 8; j++) {
                int nextDir = (fish.dir + j) % 8;
                int nextR = fish.r + rows[nextDir];
                int nextC = fish.c + cols[nextDir];

                if (nextR < 0 || nextR >= 4 || nextC < 0 || nextC >= 4) {
                    continue;
                }
                if (nextR == shark.r && nextC == shark.c) {
                    continue;
                }
                // 빈칸이라면
                fish.dir = nextDir;
                if (map[nextR][nextC] == 0) {
                    map[nextR][nextC] = map[fish.r][fish.c];
                    map[fish.r][fish.c] = 0;
                    fish.r = nextR;
                    fish.c = nextC;
                } else {
                    // 물고기 자리 스왑
                    Fish swapFish = fishes[map[nextR][nextC]];
                    swapFish(fish, swapFish);
                    int tempNumber = map[fish.r][fish.c];
                    map[fish.r][fish.c] = map[swapFish.r][swapFish.c];
                    map[swapFish.r][swapFish.c] = tempNumber;
                }
                break;
            }
        }
    }

    public static void swapFish(Fish from, Fish to) {
        int tempR = from.r;
        int tempC = from.c;
        from.r = to.r;
        from.c = to.c;
        to.r = tempR;
        to.c = tempC;
    }

    public static Fish[] copyFishes(Fish[] fishes) {
        Fish[] copyFishes = new Fish[17];
        for (int i = 1; i <= 16; i++) {
            copyFishes[i] = new Fish(fishes[i].r, fishes[i].c, fishes[i].dir, fishes[i].isAlive);
        }
        return copyFishes;
    }

    public static int[][] copyMap(int[][] map) {
        int[][] copyMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }
}