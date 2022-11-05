package boj;

/*
    물감은 최대 7개까지 섞을 수 있다. N값은 최대 30이 주어지므로 7개까지 제한을 걸어줘야 한다.
    3개의 물감이 순서대로 1, 2, 3이 주어진다면
    1, 2, 3, 12, 13, 23, 123의 물감을 섞는 경우가 존재한다.

    모든 조합을 구해야하므로 백트래킹을 이용하면 된다.
    단 7개의 물감을 넘어서는 경우는 막아야하고,
    각각의 경우에서 RGB값을 구하여 문두리색을 구해야 한다.
*/

import java.io.*;
import java.util.*;

class Boj20950 {
    static int n;
    static int[][] colors;
    static int gomdooriR, gomdooriG, gomdooriB;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        colors = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            colors[i][0] = Integer.parseInt(st.nextToken());
            colors[i][1] = Integer.parseInt(st.nextToken());
            colors[i][2] = Integer.parseInt(st.nextToken());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        gomdooriR = Integer.parseInt(st.nextToken());
        gomdooriG = Integer.parseInt(st.nextToken());
        gomdooriB = Integer.parseInt(st.nextToken());

        backtracking(0, 0, 0, 0, 0);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    public static void backtracking(int r, int g, int b, int depth, int count) {
        if (count > 7) {
            return;
        }
        if (depth == n) {
            if (count < 2) {
                return;
            }
            updateMinValue(r, g, b, count);
            return;
        }
        backtracking(r + colors[depth][0], g + colors[depth][1], b + colors[depth][2], depth + 1, count + 1);
        backtracking(r, g, b, depth + 1, count);

    }

    public static void updateMinValue(int r, int g, int b, int count) {
        int calcR = 0;
        int calcG = 0;
        int calcB = 0;
        if (r != 0) {
            calcR = r / count;
        }
        if (g != 0) {
            calcG = g / count;
        }
        if (b != 0) {
            calcB = b / count;
        }
        int differenceOfColor = Math.abs(calcR - gomdooriR) + Math.abs(calcG - gomdooriG) + Math.abs(calcB - gomdooriB);
        result = Math.min(result, differenceOfColor);
    }
}