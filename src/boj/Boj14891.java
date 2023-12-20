package boj;

/*
    1. 톱니 바퀴를 회전시키는데
        - 해당 톱니바퀴의 좌, 우 톱니바퀴가 회전대상이 되는지 파악함
        - 현재 톱니 바퀴를 회전시키고, 회전 대상이 되는 톱니 바퀴를 연쇄적으로 회전 명령을 줌
        - 이때 기존에 돌았던 톱니바퀴도 회전할 수 있으므로 모든 경우의 회전을 돌아가게 해야 합니다.
        현재 위치가 회전하는 위치인지 확인 -> 이후 회전
*/

import java.io.*;
import java.util.*;

class Boj14891 {

    static final int[] CLOCK = {1, -1};

    static class Gear {

        static final int RIGHT = 2;
        static final int LEFT = 6;

        int[] tooth = new int[8];

        public Gear(String line) {
            for (int i = 0; i < line.length(); i++) {
                tooth[i] = line.charAt(i) - '0';
            }
        }

        public boolean shouldMove(int value, boolean isLeft) {
            if (isLeft) {
                return value != tooth[LEFT];
            } else {
                return value != tooth[RIGHT];
            }
        }

        public void move(int way) {
            int[] newTooth = new int[8];
            if (way == 1) {
                for (int i = 0; i < 8; i++) {
                    int next = (i + 1) % 8;
                    newTooth[next] = tooth[i];
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    int next = (i - 1 + 8) % 8;
                    newTooth[next] = tooth[i];
                }
            }
            tooth = newTooth;
        }

        public int getLeft() {
            return tooth[LEFT];
        }

        public int getRight() {
            return tooth[RIGHT];
        }
    }

    static Gear[] gears;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        gears = new Gear[4];
        for (int i = 0; i < 4; i++) {
            gears[i] = new Gear(br.readLine());
        }

        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1;
            int way = Integer.parseInt(st.nextToken());
            if (way == 1) {
                startRotate(n, 0);
            } else {
                startRotate(n, 1);
            }
        }

        int sum = 0;
        int score = 1;
        for (int i = 0; i < 4; i++ ) {
            if (gears[i].tooth[0] == 1) {
                sum += score;
            }
            score *= 2;
        }

        bw.write(sum + "");
        bw.flush();
        bw.close();
    }

    public static void startRotate(int n, int clockIndex) {
        int preLeft = gears[n].getLeft();
        int preRight = gears[n].getRight();
        gears[n].move(CLOCK[clockIndex]);
        // 끝에 존재하는 톱니인 경우
        if (n == 0) {
            if (gears[n + 1].shouldMove(preRight, true)) {
                rotateRightWay(n + 1, (clockIndex + 1) % 2);
            }
        } else if (n == 3) {
            if (gears[n - 1].shouldMove(preLeft, false)) {
                rotateLeftWay(n - 1, (clockIndex + 1) % 2);
            }
        } else {
            if (gears[n + 1].shouldMove(preRight, true)) {
                rotateRightWay(n + 1, (clockIndex + 1) % 2);
            }
            if (gears[n - 1].shouldMove(preLeft, false)) {
                rotateLeftWay(n - 1, (clockIndex + 1) % 2);
            }
        }
    }

    public static void rotateRightWay(int n, int clockIndex) {
        if (n >= 3) {
            gears[n].move(CLOCK[clockIndex]);
            return;
        }
        int preRight = gears[n].getRight();
        gears[n].move(CLOCK[clockIndex]);

        if (gears[n + 1].shouldMove(preRight, true)) {
            rotateRightWay(n + 1, (clockIndex + 1) % 2);
        }
    }

    public static void rotateLeftWay(int n, int clockIndex) {
        if (n == 0) {
            gears[n].move(CLOCK[clockIndex]);
            return;
        }
        int preLeft = gears[n].getLeft();
        gears[n].move(CLOCK[clockIndex]);

        if (gears[n - 1].shouldMove(preLeft, false)) {
            rotateLeftWay(n - 1, (clockIndex + 1) % 2);
        }
    }
}
