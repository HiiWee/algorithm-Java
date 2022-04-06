/*
*
* 2*n에서 가로 길이가 n일때 맨 끝에 1x2, 2X2, 2x1이 올 수 있음
* 만약 2x1이 온다면 n-1의 길이를 가진 2*n 타일의 경우의 수
* 만약 2x2, 2x1이 온다면 n-2의 길이를 가진 2*n 타일의 경우의 수다.
* 하지만 피보나치와 다르게 가로길이가 2인경우가 2개 있으므로
* f(n) = f(n - 1) + 2 * f(n - 2)
*
* */

package boj;

import java.util.Scanner;

class Boj11727 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        dp[1] = 1;
        if (n > 1) dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
        }
        System.out.println(dp[n]);

    }
}