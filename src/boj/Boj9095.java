package boj;

import java.util.Scanner;

class Boj9095 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        int num;
        for (int i = 0; i < cnt; i++) {
            num = sc.nextInt();
            int[] dp = new int[num + 1];
            countSeperateSum(dp, num);
            System.out.println(dp[num]);
        }
    }

    public static void countSeperateSum(int[] dp, int num) {
        dp[1] = 1;
        if (num > 1) dp[2] = 2;
        if (num > 2) {
            dp[3] = 4;
            for (int i = 4; i <= num; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
        }
    }
}
