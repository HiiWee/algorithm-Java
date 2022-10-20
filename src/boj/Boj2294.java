package boj;

/*
    최소의 동전을 사용하여 동전의 합이 k 가치가 되어야 한다.
    따라서 dp[i][j]를 다음과 같이 정의한다.
     -> i 번째 동전까지 사용할 수 있을때 j가치를 만들기 위한 최소 동전의 수

     만약 j가치가 i번째 동전의 가치보다 크거나 같은 경우(j >= coins[i])
     현재 dp에는 i - 1번째 동전까지의 동전의 수와, coins[i] 가치의 동전을 포함시켰을때의 동전의 수 중 최소를 저장한다.
     -> dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i]])

     j가치가 i번째 동전의 가치보다 작은 경우는 현재 i번째 동전을 이용하면 j를 넘어서므로
     이전의 값을 저장한다.
     -> dp[i][j] = dp[i - 1][j]

     저장을 마치고 나면, dp[n][k]의 값이 최소 동전의 수가 되는데 만약 이것이 0개라면 -1을 출력하고
     그렇지 않다면 그대로 출력한다.
*/

import java.io.*;
import java.util.*;

class Boj {
    private static final int INF = 100000000;
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
        }
        Arrays.fill(dp[0], INF);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i]] + 1);
                }
            }
        }
        if (dp[n][k] == INF) {
            bw.write("-1\n");
        } else {
            bw.write(dp[n][k] + "\n");
        }
        bw.flush();
        bw.close();
    }
}