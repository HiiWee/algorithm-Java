package boj;

/*
    dp[i][j]는 i번째 구슬까지 사용할때 j그램을 딱 맞춰서 측정할 수 있다면 dp[i][j]를 1로 세팅

    우선 구슬 자체도 추가 될 수 있고, 구슬의 무게를 이용해 추를 달 수 있으므로
    j <= 추[i]일 경우는 dp[i][j]  = Math.max(dp[i - 1][j], dp[i - 1][추[i] - j]);
    j > 추[i]일 경우는 dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 추[i]]);를 한다.


    즉 추의 무게가 더 크다면, 현재 추의 무게에서 구슬의 무게를 제외한 무게를 i - 1까지의 추를 사용했을때 측정할 수 있다면
    현재 추를 이용해서 구슬을 잴 수 있다는 의미이고

    구슬의 무게가 더 큰 경우, 현재 구슬의 무게에서 추의 무게를 제외한 무게를 i - 1까지의 추를 사용했을때 측정할 수 있다면
    현재 추를 이용해 구슬을 잴 수 있다는 의미가 된다.

    위 점화식은 절대값으로 간단하게 표현 가능하다.
    dp[i][j] = dp[i - 1][j] || dp[i - 1][Math.abs(j - 추[i])];

    위의 점화식의 같은 경우 i번쨰 추를 기준으로 두고 다른 추 및 구슬의 조합을 검사하는 경우이다.
    하지만 현재 i번째 추와 구슬의 조합을 기준으로 두고 다른 추들은 비교하지 않았으므로 그에대한 비교도 필요하다.
    dp[i][j] = dp[i][j] || dp[i - 1][j + 추[i]];
*/

import java.io.*;
import java.util.*;

class Boj2629 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] pendulum = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            pendulum[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][] dp = new boolean[n + 1][40501];

        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = true;
            for (int j = 1; j <= 40000; j++) {
                // 추의 무게가 더 큰 경우, 구슬의 무게가 더 큰 경우를 고려하기 위해 절대값 사용
                // (i번째 추) 그리고 (다른추 + 구슬)의 조합을 확인함
                dp[i][j] = dp[i - 1][j] || dp[i - 1][Math.abs(j - pendulum[i])];
                // (i번째 추 + 구슬) 그리고 (다른 추)들에 대한 조합을 확인함
                dp[i][j] = dp[i][j] || dp[i - 1][j + pendulum[i]];
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= count; i++) {
            if (dp[n][Integer.parseInt(st.nextToken())]) {
                sb.append("Y").append(" ");
            } else {
                sb.append("N").append(" ");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}