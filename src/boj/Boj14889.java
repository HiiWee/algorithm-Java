package boj;

import java.io.*;
import java.util.*;

class Boj14889 {

    static int n, totalSum, minDiff = Integer.MAX_VALUE;
    static int[][] map;
    static int[] eachSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        eachSum = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                totalSum += map[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                eachSum[i] += map[i][j] + map[j][i];
            }
        }

        combination(0, 0, 0);

        bw.write(Integer.toString(minDiff));
        bw.flush();
        bw.close();
    }

    public static void combination(int index, int count, int select) {
        if (count == n / 2) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((select & 1 << i) != 0) {
                    sum += eachSum[i];
                }
            }
            minDiff = Math.min(minDiff, Math.abs(totalSum - sum));
            return;
        }

        for (int i = index; i < n; i++) {
            combination(i + 1, count + 1, select | 1 << i);
        }
    }
}