package boj;

import java.io.*;
import java.util.*;

class Boj20055 {

    static int n, k;
    static int[] arr;
    static boolean[] robot;
    static int zeroCount = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[2 * n];
        robot = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 1;
        while (true) {
            // 컨베이어 벨트 이동
            rotate();
            // 로봇 이동
            moveRobot();
            // 로봇 올리기
            raiseRobot();
            if (zeroCount >= k) {
                break;
            }
            count++;
        }

        bw.write(count + "");
        bw.flush();
        bw.close();
    }

    public static void raiseRobot() {
        if (arr[0] > 0) {
            robot[0] = true;
            arr[0]--;
            if (arr[0] == 0) {
                zeroCount++;
            }
        }
    }

    public static void moveRobot() {
        for (int i = n - 1; i > 1; i--) {
            if (robot[i - 1]) {
                // 다음칸의 내구도가 1이상이고, 로봇이 없다면 이동 가능
                if (arr[i] > 0 && !robot[i]) {
                    robot[i] = true;
                    robot[i - 1] = false;
                    arr[i]--;
                    // 이동한 위치의 컨베이어 벨트가 내구도가 0이되면 카운팅
                    if (arr[i] == 0) {
                        zeroCount++;
                    }
                }
            }
        }
        // 내리는 칸의 로봇은 항상 하차함
        robot[n - 1] = false;
    }

    public static void rotate() {
        int tempArr = arr[2 * n - 1];
        boolean tempRobot = robot[n - 1];
        for (int i = 2 * n - 1; i >= 1; i--) {
            if (i < n) {
                robot[i] = robot[i - 1];
            }
            arr[i] = arr[i - 1];
        }
        arr[0] = tempArr;
        robot[0] = tempRobot;
        // 컨베이어 벨트가 움직이고, 로봇이 내리는 위치에 있다면 로봇 내려줌
        if (robot[n - 1]) {
            robot[n - 1] = false;
        }
    }
}
