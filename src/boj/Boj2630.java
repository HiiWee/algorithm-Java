package boj;

import java.io.*;
import java.util.*;

class Boj2630 {
    static int n;
    static int[] colorCount = new int[2];
    static int[][] paper;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        paper = new int[n][n];

        inputPaper(br);
        dividePaper(0, 0, n);

        bw.write(colorCount[0] + "\n");
        bw.write(colorCount[1] + "\n");
        bw.flush();
        bw.close();
    }

    public static void inputPaper(final BufferedReader br) throws IOException {
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void dividePaper(final int row, final int col, final int size) {
        if (isSameColor(row, col, size)) {
            colorCount[paper[row][col]]++;
            return;
        }
        dividePaper(row, col, size / 2);
        dividePaper(row + size / 2, col, size / 2);
        dividePaper(row, col + size / 2, size / 2);
        dividePaper(row + size / 2, col + size / 2, size / 2);
    }

    public static boolean isSameColor(final int row, final int col, final int size) {
        int color = paper[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (paper[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}