package boj;

/*
    배열에서 구간합을 구하는데 최대 100,000개가 주어지고, 배열의 행열은 1024까지 주어지므로
    최악의 경우 1천억번을 연산해야 한다. 따라서 단순무식한 방법으로는 불가능하다.

    주어지는 구간합은 첫번쨰 좌표가 좌측 상단 꼭짓점, 두번쨰 좌표가 우측 하단 꼭짓점을 나타내는 직사각형의 합을 말한다.
    예를들어
    1 2 3
    4 5 6
    7 8 9
    에서 1 1 ~ 3 2의 구간합은 1 + 2 + 4 + 5 + 7 + 8 = 27이 된다.

    dp[i][j]는 (1, 1) ~ (i, j)의 구간합을 표현하면
    다음과 같다.
    dp[1][1] = 1
    dp[1][2] = dp[1][2 - 1] + arr[1][2] = 3
    dp[1][3] = dp[1][3 - 1] + arr[1][3] = 6
    dp[2][1] = dp[2 - 1][1] + arr[2][1] = 5
    dp[2][2] = dp[2][2 - 1] + dp[2 - 1][2] - dp[2 - 1][2 - 1] + arr[2][2]

    따라서 점화식은 다음과 같이 도출할 수 있다.
    dp[i][j] = arr[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1]

    하지만 주어지는 사각형의 왼쪽꼭짓점(r1, c1)은 1,1의 위치가 아닌경우도 존재하므로
    두번쨰 좌표인 (r2, c2)의 dp값에서 필요없는 부분을 제외시켜 주어야 한다.
    만약 (2, 2) ~ (3, 4)의 사각형을 구해야 하면 다음과 같이 x영역을 제외해야 한다.
    x x x x
    x o o o
    x o o o
    해당영역은 결국 dp[3][1]과, dp[1][4]가 제외된다. 하지만 (1, 1)의 경우 2번 제외가 되므로 dp[1][1]을 더해준다.
    이를 통해 식을 도출하면 다음과 같다.
    dp[r2][c2] - dp[r1 - 1][c2] - dp[r2][c1 - 1] + dp[r1 - 1][c1 - 1]
*/

import java.util.*;
import java.io.*;

class Boj11660 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][n + 1];
        int[][] arr = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = arr[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            sb.append(dp[r2][c2] - dp[r1 - 1][c2] - dp[r2][c1 - 1] + dp[r1 - 1][c1 - 1]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}