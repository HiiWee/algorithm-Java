package boj;

/*
    dp[i][j] -> i번째 단원까지 공부할 수 있을때 j시간을 투자해서 얻을 수 있는 최대 배점
    [time[i] <= j일 경우]
    만약 j시간에서 time[i]의 단원을 공부하고도 i - 1단원까지 공부했던 내용중에서 남은 시간으로 더 공부할 수 있으면
    해당 수를 더해주어 최대값을 갱신해야 한다.

    [time[i] > j인 경우]
    그렇지 않은경우 직전 단원의(i - 1) j시간까지 공부했던 내용이 최대값이 된다.
*/
import java.util.*;
import java.io.*;

class Boj14728 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[] times = new int[n + 1];
        int[] scores = new int[n + 1];
        int[][] dp = new int[n + 1][t + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            scores[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= t; j++) {
                if (j >= times[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], scores[i] + dp[i - 1][j - times[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        bw.write(dp[n][t] + "\n");
        bw.flush();
        bw.close();
    }

}