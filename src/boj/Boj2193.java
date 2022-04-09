package boj;

/*
피보나치 수열이 나타나는 유명한 형태입니다.

f(i)를 길이 i의 이친수의 개수라고 하면 f(1) = 2, f(2) = 3이 성립합니다.

길이 i의 수열은 맨 끝이 0이거나 1입니다.

0인 수열인 나머지 자리를 채우는 경우의 수인 f(i-1)개 있습니다.
1인 수열은 뒤에서 두 번째 수가 0이 되어야 하므로 숫자 2개가 고정되어 f(i-2)개 있습니다.
이를 합하면 f(i) = f(i-1) + f(i-2)가 성립하고, 피보나치 수열의 점화식과 동일합니다.
* */
import java.util.Scanner;

// Bottom-Up
class Boj2193_1 {

    static int N;
    static long[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new long[N + 1];
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (i == 2) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        System.out.println(dp[N]);
    }
}

// Top-Down
class Boj2193_2 {

    static int N;
    static long[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new long[N + 1];
        dp[1] = 1;
        fibonacci(N);
        System.out.println(dp[N]);
    }

    public static long fibonacci(int n) {
        if (dp[n] > 0) return dp[n];

        if (n == 2) dp[2] = 1;
        else dp[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return dp[n];
    }
}
