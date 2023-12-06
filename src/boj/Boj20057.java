package boj;

import java.io.*;
import java.util.*;

class Boj20057 {

    // 좌 하 우 상
    private static final int[] rows = {0, 1, 0, -1};
    private static final int[] cols = {-1, 0, 1, 0};
    private static final int[][][] dustRows = {
            {{-2, 0, 2}, {-1, -1, 10}, {-1, 0, 7}, {-1, 1, 1}, {0, -2, 5}, {1, -1, 10}, {1, 0, 7}, {1, 1, 1}, {2, 0, 2}},
            {{-1, -1, 1}, {-1, 1, 1}, {0, -2, 2}, {0, -1, 7}, {0, 1, 7}, {0, 2, 2}, {1, -1, 10}, {1, 1, 10}, {2, 0, 5}},
            {{-2, 0, 2}, {-1, -1, 1}, {-1, 0, 7}, {-1, 1, 10}, {0, 2, 5}, {1, -1, 1}, {1, 0, 7}, {1, 1, 10}, {2, 0, 2}},
            {{-2, 0, 5}, {-1, -1, 10}, {-1, 1, 10}, {0, -2, 2}, {0, -1, 7}, {0, 1, 7}, {0, 2, 2}, {1, -1, 1}, {1, 1, 1}}
    };

    private static int n, totalOutDust;
    private static int[][] map;

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

        int moveAmount = 1;
        int currentMoveAmount = 0;
        int directionCount = 0;
        int direction = 0;
        int nextR = n / 2;
        int nextC = n / 2;

        for (int count = 0; count < n * n - 1; count++) {
            nextR += rows[direction];
            nextC += cols[direction];
            // a의 위치
            int aRow = nextR + rows[direction];
            int aCol = nextC + cols[direction];

            moveDust(direction, nextR, nextC, aRow, aCol);
            // 현재 방향으로 얼마나 이동했는지
            currentMoveAmount++;
            // 현재 방향으로 꽉 채워서 이동했다면
            if (currentMoveAmount == moveAmount) {
                // 다음 위치로 이동하도록 변경
                directionCount++;
                direction = (direction + 1) % 4;
                currentMoveAmount = 0;
            }
            // 방향 전환이 두 번 됐다면 현재 방향 이동량을 1증가
            if (directionCount == 2) {
                moveAmount++;
                directionCount = 0;
            }

        }

        bw.write(totalOutDust + "");
        bw.flush();
        bw.close();
    }

    public static void moveDust(int direction, int r, int c, int aRow, int aCol) {
        int currentDust = map[r][c];
        int totalMoveDust = 0;
        for (int i = 0; i < 9; i++) {
            int nextR = r + dustRows[direction][i][0];
            int nextC = c + dustRows[direction][i][1];
            int percentage = dustRows[direction][i][2];
            int moveDust = (int) (currentDust * (percentage / 100.0));
            totalMoveDust += moveDust;

            // 모래가 밖을 벗어나는 위치라면
            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                totalOutDust += moveDust;
                continue;
            }
            map[nextR][nextC] += moveDust;
        }
        if (aRow < 0 || aRow >= n || aCol < 0 || aCol >= n) {
            // a의 위치가 맵 밖을 벗어나면
            totalOutDust += currentDust - totalMoveDust;
        } else {
            map[aRow][aCol] += currentDust - totalMoveDust;
        }
        map[r][c] = 0;
    }
}