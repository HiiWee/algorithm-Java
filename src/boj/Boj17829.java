package boj;

// BOTTOM_UP
import java.io.*;
import java.util.*;

class Boj17829_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (n / 2 > 0) {
            n = n / 2;
            int[][] nextMap = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    nextMap[i][j] = getSecondMaxNumber(i * 2, j * 2, map);
                }
            }
            map = nextMap;
        }

        bw.write(Integer.toString(map[0][0]));
        bw.flush();
        bw.close();
    }

    public static int getSecondMaxNumber(int r, int c, int[][] map) {
        int maxNumber = -10000;
        int secondMaxNumber = -10000;
        for (int i = r; i < r + 2; i++) {
            for (int j = c; j < c + 2; j++) {
                if (maxNumber < map[i][j]) {
                    secondMaxNumber = maxNumber;
                    maxNumber = map[i][j];
                } else {
                    secondMaxNumber = Math.max(secondMaxNumber, map[i][j]);
                }
            }
        }
        return secondMaxNumber;
    }
}

// TOP_DOWN
class Boj17829_2 {

    static final int[] ROWS = {0, 1, 1, 0};
    static final int[] COLS = {0, 0, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(Integer.toString(polling(0, 0, n, map)));
        bw.flush();
        bw.close();
    }

    public static int polling(int r, int c, int n, int[][] map) {
        int maxNumber = -10000;
        int secondMaxNumber = -10000;
        if (n == 2) {
            for (int i = r; i < r + 2; i++) {
                for (int j = c; j < c + 2; j++) {
                    if (maxNumber < map[i][j]) {
                        secondMaxNumber = maxNumber;
                        maxNumber = map[i][j];
                    } else {
                        secondMaxNumber = Math.max(secondMaxNumber, map[i][j]);
                    }
                }
            }

            return secondMaxNumber;
        }

        int size = n / 2;
        for (int i = 0; i < 4; i++) {
            int nextR = r + size * ROWS[i];
            int nextC = c + size * COLS[i];

            int number = polling(nextR, nextC, size, map);

            if (maxNumber < number) {
                secondMaxNumber = maxNumber;
                maxNumber = number;
            } else {
                secondMaxNumber = Math.max(secondMaxNumber, number);
            }
        }

        return secondMaxNumber;
    }
}
