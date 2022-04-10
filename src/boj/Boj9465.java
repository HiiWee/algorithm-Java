package boj;

import java.util.Scanner;

// top-down
class Boj9465_1 {
    static int caseCnt;
    static int N;
    static int[][] score;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        caseCnt = sc.nextInt();

        for (int i = 0; i < caseCnt; i++) {
            N = sc.nextInt();
            dp = new int[2][N + 1];
            score = new int[2][N + 1];
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k <= N; k++) {
                    score[j][k] = sc.nextInt();
                    dp[j][k] = -1;
                }
            }
            dp[0][1] = score[0][1];
            dp[1][1] = score[1][1];
            int total1 = recur(0, N);
            int total2 = recur(1, N);
            System.out.println(Math.max(total1, total2));
        }
    }
    public static int recur(int col, int row) {
        if (dp[col][row] > -1) return dp[col][row];
        if (col == 0) {
            dp[0][row] = Math.max(recur(1, row - 1), recur(1, row - 2)) + score[0][row];
        } else {
            dp[1][row] = Math.max(recur(0, row - 1), recur(0, row - 2)) + score[1][row];
        }
        return dp[col][row];
    }
}
// bottom-up
class Boj9465_2 {
    static int caseCnt;
    static int N;
    static int[][] score;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        caseCnt = sc.nextInt();

        for (int i = 0; i < caseCnt; i++) {
            N = sc.nextInt();
            dp = new int[2][N + 1];
            score = new int[2][N + 1];
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k <= N; k++) {
                    score[j][k] = sc.nextInt();
                    dp[j][k] = -1;
                }
            }
            dp[0][1] = score[0][1];
            dp[1][1] = score[1][1];
            for (int j = 2; j <= N; j++) {
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + score[0][j];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + score[1][j];
            }
            System.out.println(Math.max(dp[0][N], dp[1][N]));
        }
    }
}
