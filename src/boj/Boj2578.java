package boj;

import java.io.*;
import java.util.*;

class Boj2578 {
    static class BingoNumber {
        int r, c;

        BingoNumber(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static BingoNumber[] bingoNumbers = new BingoNumber[26];
    private static boolean[][] visited = new boolean[5][5];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                bingoNumbers[Integer.parseInt(st.nextToken())] = new BingoNumber(i, j);
            }
        }

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int number = Integer.parseInt(st.nextToken());
                visited[bingoNumbers[number].r][bingoNumbers[number].c] = true;
                if (isBingo() >= 3) {
                    bw.write((i * 5 + j + 1) + "");
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }
    }

    public static int isBingo() {
        int bingoCount = 0;

        int leftDiagonal = 0;
        int rightDiagonal = 0;
        for (int i = 0; i < 5; i++) {
            int colCount = 0;
            int rowCount = 0;
            if (visited[i][i]) {
                leftDiagonal++;
            }
            if (visited[i][4 - i]) {
                rightDiagonal++;
            }
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) {
                    colCount++;
                }
                if (visited[j][i]) {
                    rowCount++;
                }
            }
            if (colCount == 5) {
                bingoCount++;
            }
            if (rowCount == 5) {
                bingoCount++;
            }
        }

        if (leftDiagonal == 5) {
            bingoCount++;
        }
        if (rightDiagonal == 5) {
            bingoCount++;
        }

        return bingoCount;
    }
}