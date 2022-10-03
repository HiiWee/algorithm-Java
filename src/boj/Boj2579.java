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
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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



// 계단 오르기 복습!

/**
 * 계단을 오를때 마지막 계단(i계단)을 밟는 경우는 2가지가 존재한다.
 * 1. i - 1 계단을 밟고 i 계단을 밟는 경우
 * 2. i - 2 계단을 밟고 i 계단을 밟는 경우
 *
 * 1번 방법의 경우 i - 1은 반드시 밟아야 하므로 배열의 값을 직접 더해주되 i - 2를 밟게되면 3칸연속 밟을 수 없는 조건을 위배하므로
 * i - 3번째까지 계단을 밟았을때 최대의 값을 이용한다. -> dp[i] = arr[i - 1] + dp[i - 3]
 *
 * 2번 방법의 경우 i - 2계단을 밟고 i계단을 밟아야 하는데, i - 2계단까지 밟았을떄의 최대값을 이용해야 하므로 dp[i] = dp[i - 2]가 된다.
 *
 * 따라서 1, 2번 방법중에서 더 최대의 값을 갖는 경우를 i번쨰 dp에 저장한다.
 */

class Boj2579_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = arr[1];
        if (n > 1) {
            dp[2] = arr[1] + arr[2];
        }
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], arr[i - 1] + dp[i - 3]) + arr[i];
        }

        bw.write(dp[n] + "\n");
        bw.flush();
        bw.close();

    }
}