package boj;

/*
    앱들을 비활성화하는 비용을 배낭문제에서 w[]로 두고, v[]를 앱들의 메모리로 둔다.
    만약 반대로 두게되면 10,000,000인 만큼의 열을 생성해야 하므로 메모리를 초과하게 된다.

    dp[i][j]의 의미는 다음과 같다.
    dp[0][0] -> 0번째까지 입력받은 앱을 사용할때 최대0의 비용으로 확보가능한 메모리는 0
    dp[0][3] -> 0번쨰까지 입력받은 앱을 사용할떄 최대3의 비용으로 확보가능한 메모리는 30

    따라서 이를 배낭문제로 두고 풀면 된다.
    각 dp에 저장된 확보가능한 메모리가 초기에 입력된 M값보다 크거나 같다면, 해당 j값(비활성화 비용)의 최소를 갱신한다.

    dp의 최대로 나올 수 있는 비활성화 비용을 나타내며 이는 100 * 100인 10000이므로
    dp는 N + 1행, 10001열로 생성한다.
*/

import java.io.*;
import java.util.*;

class Boj7579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] memories = new int[n + 1];
        int[] cost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n + 1][10001];

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 10000; j++) {
                if (j >= cost[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], memories[i] + dp[i - 1][j - cost[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= m) {
                    min = Math.min(min, j);
                }
            }
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
}