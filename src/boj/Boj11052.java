package boj;

/*
    dp[n] == 카드를 n 장 구매할 때의 최대값
    따라서
    p[1장의 카드를 구매하는 경우] + dp[n - 1장의 카드를 구매하는 경우의 최대값]
    p[2장의 카드를 구매하는 경우] + dp[n - 2장의 카드를 구매하는 경우의 최대값]
                            .
                            .
                            .
    p[n장의 카드를 구매하는 경우] + dp[n - n장의 카드를 구매하는 경우의 최대값]
    위와 같이 가장 높은 비용으로 n개의 카드를 구매하는 값을 구할 수 있다.
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

// Bottom-Up
class Boj11052_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] temp = bf.readLine().split(" ");
        int[] dp = new int[n + 1];
        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(temp[i - 1]);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], p[j] + dp[i - j]);
            }
        }
        System.out.println(dp[n]);
    }
}

// Top-Down
class Boj11052_2 {
    static Integer[] dp;
    static int[] p;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] temp = bf.readLine().split(" ");
        dp = new Integer[n + 1];
        p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(temp[i - 1]);
        }
        dp[0] = 0;
        System.out.println(recur(n));

    }
    public static Integer recur(int n) {
        if (dp[n] == null) {
            dp[n] = 0;
            for (int i = 1; i <= n; i++) {
                dp[n] = Math.max(dp[n], p[i] + recur(n - i));
            }
        }
        return dp[n];
    }
}