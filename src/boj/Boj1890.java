package boj;

/*
    각 모든 칸을 순차적으로 방문하면서 해당 칸의 value를 행과 열에 더해주며 더한 행과 열의 값이 n보다 작다면 현재 칸의 dp 값을 해당 칸의 dp값에 누적합 시켜준다.
    마지막 n-1행 n-1열은 제외하고 모든 칸을 탐색하면 dp[n-1][n-1]에는 모든 경우의수가 담겨있다.
*/
import java.util.*;
import java.io.*;

class Boj1890 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[n][n];
        dp[0][0] = 1L;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                    break;
                }
                if (dp[i][j] == 0) {
                    continue;
                }
                int nextMove = map[i][j];
                // 아래로 갈 수 있는지 확인 및 업데이트
                if (i + nextMove < n) {
                    dp[i + nextMove][j] += dp[i][j];
                }
                // 우측으로 갈 수 있는지 확인 및 업데이트
                if (j + nextMove < n) {
                    dp[i][j + nextMove] += dp[i][j];
                }
            }
        }

        bw.write(dp[n - 1][n - 1] + "\n");
        bw.flush();
        bw.close();
    }
}