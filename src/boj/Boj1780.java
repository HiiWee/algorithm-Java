package boj;

import java.io.*;
import java.util.*;

class Boj1780 {
    static boolean[][] visited;
    static int n;
    static int[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        visited = new boolean[n][n];
        count = new int[3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        partition(arr, 0, 0, n);
        for (int i = 0; i < 3; i++) {
            bw.write(count[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static boolean isFillSameNumber(int[][] arr, int row, int col, int scale) {
        int num = arr[row][col];
        for (int i = 0; i < scale; i++) {
            for (int j = 0; j < scale; j++) {
                if (num != arr[i + row][j + col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void partition(int[][] arr, int row, int col, int scale) {
        if (scale == 0) {
            return;
        }
        if (isFillSameNumber(arr, row, col, scale)) {
            count[arr[row][col] + 1]++;
        } else {
            int nextScale = scale / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    partition(arr, row + i * nextScale, col + j * nextScale, nextScale);
                }
            }
        }
    }
}