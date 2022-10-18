package boj;
/*
    무조건 0 -> 1 -> 2 -> 0 ... 순서대로의 우유를 먹을 수 있으며
    이는 곧 mod3과 동일하다 따라서 다음에 방문할 곳에서 먹어야할 우유는
    직전까지 먹었던 최대 우유의 수 % 3의 값과 동일해야 한다.
    (증명: 2개의 우유를 먹었다면 0 -> 1을 먹었으므로 2를 먹어야함 따라서 현재위치의 우유가 2 % 3 -> 2이어야 함)

    예외적인 상황을 줄이기 위해 1행의 모든 열과 1열의 모든 행들은 따로 예외를 두어 초기화하고
    2행2열 부터 n행n열까지 진행하며

    만약 현재위치 기준 북쪽의 최대 우유카운트 % 3이 현재 우유번호와 동일하다면 dp[i][j] = dp[i - 1][j] + 1을 하고
    그렇지 않다면 dp[i][j] = dp[i - 1][j]의 값을 넣어놓는다.

    이후 현재위치 기준 서쪽의 최대 우유카운트 % 3이 현재 우유번호와 동일하다면
        dp[i][j] = Math.max(dp[i][j], dp[i][j - 1])
    그렇지 않다면 dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
*/

import java.io.*;
import java.util.*;

class Boj14722 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (arr[1][1] == 0) {
            dp[1][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            if (dp[1][i - 1] % 3 == arr[1][i]) {
                dp[1][i] = dp[1][i - 1] + 1;
            } else {
                dp[1][i] = dp[1][i - 1];
            }

            if (dp[i - 1][1] % 3 == arr[i][1]) {
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][1] = dp[i - 1][1];
            }
        }


        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                // 북쪽 체크
                if (dp[i - 1][j] % 3 == arr[i][j]) {
                    dp[i][j] = dp[i - 1][j] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

                // 서쪽 체크
                if (dp[i][j - 1] % 3 == arr[i][j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + 1);
                } else {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }

        bw.write(dp[n][n] + "\n");
        bw.flush();
        bw.close();
    }
}