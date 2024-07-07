package boj;

import java.io.*;
import java.util.*;

class Boj16571 {

    static int[][] map = new int[3][3];
    static int nowTurn;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int zeroCount = 0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    zeroCount++;
                }
            }
        }

        if (zeroCount % 2 == 0) {
            nowTurn = 2;
        } else {
            nowTurn = 1;
        }

        int answer = search(nowTurn);
        if (answer == 1) {
            bw.write("W");
        } else if (answer == 0) {
            bw.write("D");
        } else {
            bw.write("L");
        }
        bw.flush();
        bw.close();
    }

    public static int search(int turn) {
        int result = 2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = turn;
                    if (isEnd(turn)) {
                        result = -1;
                    }
                    result = Math.min(search(3 - turn), result);
                    map[i][j] = 0;
                }
            }
        }

        if (result == 2 || result == 0) {
            return 0;
        }
        return -result;
    }

    public static boolean isEnd(int winningTurn) {
        for (int i = 0; i < 3; i++) {
            if (winningTurn == map[i][0] && map[i][1] == map[i][0] && map[i][1] == map[i][2]) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (winningTurn == map[0][i] && map[1][i] == map[0][i] && map[1][i] == map[2][i]) {
                return true;
            }
        }

        if (map[1][1] == winningTurn && map[1][1] == map[0][0] && map[2][2] == map[0][0]) {
            return true;
        }
        return map[1][1] == winningTurn && map[1][1] == map[2][0] && map[0][2] == map[1][1];
    }
}
