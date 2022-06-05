package boj;

import java.io.*;

class Boj1992 {
    static StringBuilder sb;

    public static void main(String[] ars) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        makeQuadTree(arr, 0, 0, n);
        bw.write(sb + "");
        bw.flush();
        bw.close();
    }

    public static boolean isSingleElement(int[][] arr, int row, int col, int n) {
        int num = arr[row][col];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (num != arr[i + row][j + col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void makeQuadTree(int[][] arr, int row, int col, int n) {
        if (n == 0) {
            return;
        }
        if (isSingleElement(arr, row, col, n)) {
            // 검사부분이 하나의 트리로 이루어져 있다면 append하기
            sb.append(arr[row][col]);
        } else {
            sb.append("(");
            // 아니라면 쪼개서 다시 검사 --> 재귀
            int nextN = n / 2;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    makeQuadTree(arr, row + nextN * i, col + nextN * j, nextN);
                }
            }
            sb.append(")");
        }
    }

}