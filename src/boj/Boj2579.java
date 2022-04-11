package boj;

/*
 현재 i위치일 경우
 i-3, i-1, i를 밟는 경우와
 i-2, i를 밟는 경우가 존재
 i-1은 재귀 호출을 하지 않음 : 만약 n=5일 경우 4, 3, 2 ... 이렇게 연속적으로 호출되는데,
 만약 5를 대입하고 dp[4]가 메모제이션 되어있을때 이전 계단(N-3)은 밟은 상태인지 알 수 없다.
 따라서 연속된 블럭은 재귀가 아닌 배열의 값을 직접 더한다.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Top-Down
class Boj2759_1 {
    static int N;
    static Integer[] dp;
    static int[] stair;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new Integer[N + 1];
        stair = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            stair[i] = Integer.parseInt(bf.readLine());
        }
        dp[0] = 0;
        dp[1] = stair[1];
        if (N > 1) {
            dp[2] = stair[1] + stair[2];
        }
        System.out.println(recur(N));
    }

    public static Integer recur(int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(recur(n - 2), recur(n - 3) + stair[n - 1]) + stair[n];
        }
        return dp[n];
    }
}

// Bottom-Up
class Boj2579_2 {
    static int N;
    static int[] dp;
    static int[] stairs;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new int[N + 1];
        stairs = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(bf.readLine());
        }
        dp[1] = stairs[1];
        if (N > 1) {
            dp[2] = stairs[1] + stairs[2];
        }
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 3] + stairs[i - 1], dp[i - 2]) + stairs[i];
        }
        System.out.println(dp[N]);
    }
}