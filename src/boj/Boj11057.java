package boj;

// DP 문제 (10844와 동일 유형)
// Top-Down (재귀식)
import java.util.Scanner;

class Boj11057_1 {
    static int N;
    static int[][] dp;
    static final int MOD = 10007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N + 1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += recur(N, i);
        }
        System.out.println(result % MOD);

    }

    public static int recur(int digit, int val) {
        if (digit == 1) {
            return dp[digit][val];
        }
        if (dp[digit][val] == 0) {
            if (val == 0) {
                dp[digit][val] = recur(digit - 1, 0);
            } else {
                int sum = 0;
                for (int i = 0; i <= val; i++) {
                    sum += recur(digit - 1, i);
                }
                dp[digit][val] = sum;
            }
        }
        return dp[digit][val] % MOD;
    }
}

// Bottom-Up (반복문)
class Boj10557_2 {
    static int N;
    static final int MOD = 10007;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N + 1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][0] % MOD;
                } else {
                    int sum = 0;
                    for (int k = 0; k <= j; k++) {
                        sum += dp[i - 1][k] % MOD;
                    }
                    dp[i][j] = sum;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += dp[N][i];
        }
        System.out.println(result % MOD);
    }
}