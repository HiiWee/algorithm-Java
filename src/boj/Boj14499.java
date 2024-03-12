package boj;

import java.io.*;
import java.util.*;

class Boj14499 {

    // 동 서 북 남
    static final int[] rows = {0, 0, -1, 1};
    static final int[] cols = {1, -1, 0, 0};

    static class Dice {
        int r, c;
        int[] EWDices = new int[4]; // 서위동하
        int[] NSDices = new int[4]; // 남위북하

        public Dice(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int roll(int direction) {
            int nextR = r + rows[direction];
            int nextC = c + cols[direction];
            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                return -1;
            }

            // 동쪽, 서쪽은 서위동하만 변경됨
            // 북쪽, 남쪽은 남위북하만 변경됨
            if (direction == 0) {
                EWDices = moveBack(EWDices);
                NSDices[1] = EWDices[1];
                NSDices[3] = EWDices[3];
            } else if (direction == 1) {
                EWDices = moveForward(EWDices);
                NSDices[1] = EWDices[1];
                NSDices[3] = EWDices[3];
            } else if (direction == 2) {
                NSDices = moveBack(NSDices);
                EWDices[1] = NSDices[1];
                EWDices[3] = NSDices[3];
            } else {
                NSDices = moveForward(NSDices);
                EWDices[1] = NSDices[1];
                EWDices[3] = NSDices[3];
            }

            // 조건에 따른 주사위 면 옮기기
            if (map[nextR][nextC] == 0) {
                map[nextR][nextC] = EWDices[3];
            } else {
                EWDices[3] = map[nextR][nextC];
                NSDices[3] = map[nextR][nextC];
                map[nextR][nextC] = 0;
            }
            r = nextR;
            c = nextC;
            return EWDices[1];
        }

        public int[] moveForward(int[] dices) {
            int[] newDices = new int[4];
            for (int i = 0; i < 4; i++) {
                newDices[i] = dices[(i + 1) % 4];
            }
            return newDices;
        }

        public int[] moveBack(int[] dices) {
            int[] newDices = new int[4];
            for (int i = 0; i < 4; i++) {
                newDices[i] = dices[(i - 1 + 4) % 4];
            }
            return newDices;
        }
    }

    static int n, m, x, y, k;
    static int[][] map;
    static Dice dice;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dice = new Dice(x, y);

        StringBuilder answer = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        while (k-- > 0) {
            int direction = Integer.parseInt(st.nextToken()) - 1;
            int result = dice.roll(direction);
            if (result != -1) {
                answer.append(result).append("\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
