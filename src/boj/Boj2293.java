package boj;

/*
    1. 2차원 DP배열을 이용해 풀이

        배낭문제로 한번 풀어보자! dp[i][j]
        i번째 동전까지 사용할 수 있을때 j가치를 만들기 위한 최대 경우의 수를 나타낸다.

        각각의 동전은 몇개라도 연속해서 사용할 수 있다.

        따라서 두 가지의 상황이 나뉘게 된다.
        1. coins[i] <= j일 경우 j원을 만드는데 coins[i]를 넘어서므로
           현재 coins[i]를 제외한 j - coins[i]원을 만드는 최대 경우와 coins[i]를 사용하지 않고, i - 1코인까지의 경우의 수를 더해주어야 한다.
               j - coins[i]원을 만드는 최대 경우를 더하는 이유는
               현재 coins[i]를 추가하는 경우 나머지는 j-coins[i]를 채우는 경우와 동일해지기 떄문이다.

        2. coins[i] > j일 경우는 직전 코인까지 사용했을때의 경우의수를 그대로 대입한다.

    2. 1차원 DP 배열 이용
        위의 과정을 조금 더 단순하게 생각해보자
        j값과 coins[i]이 관계가 어떻든 항상 dp[i - 1][j], 즉 직전코인까지 사용했을때의 최대 경우의 수는 더해주고 시작한다.
        이는 곧 모든 코인의 경우를 1차원 dp배열에 누적을하여 더할 수도 있다.

        따라서 j >= coins[i]일 경우메
            dp[j] += dp[j - coins[i]]로 점화식을 단순화 할 수 있다.

*/

import java.io.*;
import java.util.*;

// 2차원 dp 이용
class Boj2293_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n + 1];
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
            dp[i][0] = 1;
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        bw.write(dp[n][k] + "\n");
        bw.flush();
        bw.close();
    }
}

// 1차원 dp 이용
class Boj2293_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n + 1];
        int[] dp = new int[k + 1];

        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= coins[i]) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        }

        bw.write(dp[k] + "\n");
        bw.flush();
        bw.close();
    }
}