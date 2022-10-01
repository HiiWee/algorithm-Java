package boj;

/*
    dp[i]는 i일수에서 받을 수 있는 최대금액이 된다.
    상담을 완료하는데 걸리는 기간을 T[]배열에 저장하고
    상담을 했을때 받는 금액을 P[]배열에 저장했을때

    i일에 방문하게 되면
    i + T[i]일에 P[i]의 돈을 받게되고 이것에 현재 i일에 저장된 최대금액인 dp[i]를 더하여 dp[i + T[i]]에 저장한다.
    위의 값이 최대값일 수 있지만, 다른 날짜에서 계산하여 이미 dp[i + T[i]]에 저장된 값이 더 클 수 있으므로
    두개의 값 중 최대값을 dp[i + T[i]]에 저장한다.

*/
import java.io.*;
import java.util.*;

class Boj15486 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n + 1];
        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 51];

        int max = 0;
        for (int i = 1; i <= n; i++) {
            // 지금까지 구해온 최대값이 큰지 혹은 현재 일수의 최대 금액이 큰지 계산
            // 이후 최대값이 변경될 수 있으니 max값도 갱신한다.
            dp[i] = Math.max(max, dp[i]);
            max = Math.max(dp[i], max);

            // 현재 일수에 시작되는 상담이 끝나는 날에 값을 초기화
            // 현재 까지의 최대값 + 시작하는 상담의 비용이 큰지 이전에 저장되었던 최대 금액이 큰지 계산
            dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
        }

        // 마지막날에 1일 상담이 있다면 그것또한 돈을 받을 수 있으므로 n + 1까지 계산
        max = Math.max(max, dp[n + 1]);
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

}