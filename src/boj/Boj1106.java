package boj;

/*
    배낭문제로 풀 수 있으며
    dp[i][j]에서 i는 i번째까지 도시를 이용할 수 있으며, j는 비용을 나타낸다.
    따라서 dp[i][j]는 i번째 도시까지 이용했을때 j비용으로 채울 수 있는 최대 인원수가 된다.

    w: 특정 번째 도시 홍보의 비용
    v: 특정번째 도시의 인원수

    또한 인원수를 채울 수 있다면 도시는 정수배 만큼 해당 도시에 더 투자할 수 있으므로 점화식은 다음과 같다.
    dp[i][j] = Math.max(dp[i - 1][j], v[i] + dp[i][j - w[i]]);

    여기서 j비용은 최대 얼마만큼 나올 수 있는지 알아야 한다. 사람은 최대 1000명을 늘려야 하고
    도시의 비용은 최대 100이다. 또한 비용이 100일때 고작 1명밖에 늘어나지 않는 도시라면
    최악의 경우 비용을 1000 * 100까지 투자해야 한다 따라서 100000이 최악의 경우이므로 열값이 된다.
*/

import java.io.*;
import java.util.*;

class Boj1106_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][100001];
        int[] cost = new int[n + 1];    // v[], 비용
        int[] person = new int[n + 1];  // w[], 인원수
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            person[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 100000; j++) {
                if (j >= cost[i]) {
                    dp[i][j] = Math.max(person[i] + dp[i][j - cost[i]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] >= c) {
                    min = Math.min(j, min);
                }
            }
        }

        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
}

/*
    기존 풀이를 좀 더 단순화 하자!

    배낭문제로 풀 수 있으며 제일 중요한 키워드는 "호텔의 고객을 적어도 C명 늘이기 위해 형택이가 투자해야 하는 돈의 최솟값"이다

    dp[i][j]에서 i는 i번째까지 도시를 이용할 수 있으며, j는 인원수를 나타낸다.
    따라서 dp[i][j]는 i번째 도시까지 이용했을때 적어도 j명을 채울 수 있는 최소 비용을 나타낸다.

    v: 특정 번째 도시 홍보의 비용
    w: 특정번째 도시의 인원수

    "적어도 j명"을 채워야 하므로

    만약 j <= w[i]이라면 w[i]만으로도 채워질 수 있고, 혹시나 이전값이 더 작을 수 있으니
        dp[i][j] = Math.min(dp[i - 1][j], w[i])가 된다.

    j > w[i]라면, 도시는 정수배만큼 더 홍보할 수 있다 즉, 하나의 도시는 중복하여 홍보할 수 있기때문에
        dp[i][j] = Math.min(dp[i - 1][j], v[i] + dp[i][j - w[i]])와 같이
        v[i] + 현재 도시(i)에서 j - w[i]명을 채울 수 있는 경우를 고려하면 동일 도시의 정수배를 고려할 수 있다.
*/

class Boj1106_2 {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][c + 1];
        int[] cost = new int[n + 1];    // v[], 비용
        int[] person = new int[n + 1];  // w[], 인원수
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            person[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp[0], INF);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= c; j++) {
                if (j <= person[i]) {
                    dp[i][j] = Math.min(cost[i], dp[i - 1][j]);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], cost[i] + dp[i][j - person[i]]);
                }
            }
        }


        bw.write(dp[n][c] + "\n");
        bw.flush();
        bw.close();
    }
}