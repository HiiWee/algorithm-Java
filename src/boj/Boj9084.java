package boj;

/*
    [2차원 배열을 이용하는 경우]

    배낭문제에서 dp[i][j]일 경우
    i의 값을 i번째 동전까지 사용했을때를 나타내고
    j는 1 ~ 주어진 금액까지를 표기한다.

    따라서 dp[i][j]의 의미는 i번째 동전까지 사용하여 j라는 금액을 만들 수 있는 방법이다.

    여기서 하나의 동전을 한번만 사용하는것이 아닌 중복으로 사용할 수 있다.
    dp[i][j]의 값에서 만약 coin[i]로 j가 나누어떨어진다면 coin[i]만으로 해당 수를 구성할 수 있으므로
    dp[i][j]는 1로 초기화 한다.

    이후 j - coin[i] * k >= 0일때까지의 dp[i][j - coin[i] * k]의 값을
    dp[i][j]에 누적합 하는데 위 식의 의미는 다음과 같다.
    예를들어 동전은 1, 2가 주어지고 만들어야 하는 수가 6이라면
    111111
    11112
    1122
    222
    와 같이 구성할 수 있다. 따라서 2의 개수를 하나씩 늘렸을때의 (6 - 2 *횟수)에서의 dp값을 현재 dp에 누적합한다.

    [1차원 배열 이용]
    위에서 구한 2차원 배열을 조금더 생각해보면 1차원 배열의 dp로 끝낼 수 있다.
    1, 2원 동전으로 10원을 만드는 방법을 구해보자
    	0	1	2	3	4	5	6	7	8	9	10
        ------------------------------------------
    0|	0	0	0	0	0	0	0	0	0	0	0
     |
    1|	0	1	1	1	1	1	1	1	1	1	1
     |
    2|	0	1	2	2	3	3	4	4	5	5	6
    위의 표는 2차원 배열에서의 규칙을 이용하여 구한 표이다. 이를 잘 살펴보면
    dp[i - 1][j]의 값을 dp[i][j]배열에 초기화하고 dp[i][j] += dp[i][j - coins[i]]임을 이용하면
    동일하게 구할 수 있음을 알 수 있다.
    더 나아가 이를 1차원 dp배열을 이용해 누적하여 더하게 되면 dp[i - 1][j]부분을 생략할 수 있으므로
    1차원 dp로도 단순화 시킬 수 있다. -> dp[j] += dp[j - coins[i]]

    단, dp[0]을 1로 초기화(의미상 아무동전도 사용하지 않을경우 1가지 존재)해야 1차원 배열을 이용할 수 있다.

*/

import java.io.*;
import java.util.*;

// 2차원 배열 이용한 좀더 단순무식한 방법
class Boj9084_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());

            int[][] dp = new int[n + 1][m + 1];
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    if (j % coins[i] == 0) {
                        dp[i][j] = 1;
                    }
                    if (j >= coins[i]) {
                        for (int k = 0; j - coins[i] * k >= 0; k++) {
                            dp[i][j] += dp[i - 1][j - coins[i] * k];
                        }
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
            sb.append(dp[n][m]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

// 2차원 배열 이용 + 기존 방식의 단순화
class Boj9084_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());

            int[][] dp = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                dp[i][0] = 1;
            }
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= coins[i]) {
                        dp[i][j] += dp[i][j - coins[i]];
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }

            sb.append(dp[n][m]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

// 단순화한 2차원 배열을 더 단순화 시켜 1차원 배열을 이용
class Boj9084_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());

            int[] dp = new int[m + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (j >= coins[i]) {
                        dp[j] += dp[j - coins[i]];
                    }
                }
            }
            sb.append(dp[m]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
