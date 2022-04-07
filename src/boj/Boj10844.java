package boj;

/*
1 : 1, 2, 3, 4, 5, 6, 7, 8, 9                    8개    >> 8
2 : down > 10, 21, 32, 43, 54, 65, 76, 87, 98    9개
  : up   > 12, 23, 34, 45, 56, 67, 78, 89        8개    >> 17

3 : down down > 210, 321, 432, 543, 654, 765, 876, 987      8개
  : down up   > 101, 212, 323, 434, 545, 656, 767, 878, 989 9개
  : up   down > 121, 232, 343, 454, 565, 676, 787, 898      8개
  : up   up   > 123, 234, 345, 456, 567, 678                6개

*/

/*
 * 1. 각 자릿수 끼리의 차는 1이어야 한다. (계단수)
 * 특정 자연수가 N 자릿수 일 때 N은 0이 오면 안됨
 * 특정 자릿수가 0혹은 9일 경우 다음 자릿수의 자릿값은 특정 수 만 올 수 있다.
 * 0 -> 1, 9 -> 8 이 점을 조건으로 걸고 점화식(Top-Down)혹은 바텀업 방식으로 구현할 수 있다.
 *
 * */
import java.util.Scanner;

// Bottom-Up 풀이 (자릿수 순서가 아닌 왼쪽부터 오른쪽으로 수를 채우는 경우의 수 계산)
// dp[2][1] -> 두번째에 순서에 1이 올 수 있는 경우 21
class Boj10844_1 {

    static long[][] dp;
    static final long MOD = 1000000000L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new long[n + 1][10];

        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][1] % MOD;
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][8] % MOD;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }

        long result = 0;
        for (int i = 0; i < 10; i++) {
            result += dp[n][i];
        }
        System.out.println(result % MOD);

    }
}
// Top-Down 방식
// dp[2][1] --> 10, 12 digit이 자릿수라는 의미
class Boj10844_2 {
    static Long[][] dp;
    static final long MOD = 1000000000L;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new Long[N + 1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1L;
        }

        long result = 0L;
        for (int i = 1; i < 10; i++) {
            result += recur(N, i);
        }

        System.out.println(result % MOD);
    }

    public static Long recur(int digit, int value) {
        if (digit == 1) {
            return dp[digit][value];
        }

        if (dp[digit][value] == null) {
            if (value == 0) {
                dp[digit][value] = recur(digit - 1, 1);
            } else if (value == 9) {
                dp[digit][value] = recur(digit - 1, 8);
            } else {
                dp[digit][value] = recur(digit - 1, value + 1) + recur(digit - 1, value - 1);
            }
        }
        return dp[digit][value] % MOD;
    }
}
