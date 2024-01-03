package boj;

// 일반적인 배낭문제 풀이
import java.io.*;
import java.util.*;

class Boj17845_1 {

    static int n, k;
    static int[] importances;
    static int[] times;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        importances = new int[k + 1];
        times = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            importances[i] = Integer.parseInt(st.nextToken());
            times[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                // 사용할 수 있는 j공부시간이 현재 시간times[i]보다 크거나 같다면 업데이트 할 수 있음
                // 현재 시간을 선택하는 경우와, 이전의 시간을 선택하는 경우
                if (j >= times[i]) {
                    dp[i][j] = Math.max(importances[i] + dp[i - 1][j - times[i]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        bw.write(dp[k][n] + "");
        bw.flush();
        bw.close();
    }
}

// 최적화 시킨 배낭문제 풀이
class Boj17845_2 {

    static int n, k;
    static int[] importance;
    static int[] times;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        importance = new int[k + 1];
        times = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            importance[i] = Integer.parseInt(st.nextToken());
            times[i] = Integer.parseInt(st.nextToken());
        }

        // 과목의 중요도 합이 최대가 되도록 몇 개만 선택하여 수강하기로 마음먹었다. -> 즉, 몇개의 과목이 중첩되어도 상관없으므로 1차원 배열에 중복하여 저장해도 무관합니다.
        int[] dp = new int[n + 1];

        for (int i = 1; i <= k; i++) {
            for (int j = n; j >= times[i]; j--) {
                // 사용할 수 있는 j공부시간이 현재 시간times[i]보다 크거나 같다면 업데이트 할 수 있음
                // 현재 시간을 선택하는 경우와, 이전의 시간까지만 선택하는 경우 중 최대값을 업데이트 해주면 됩니다.
                dp[j] = Math.max(importance[i] + dp[j - times[i]], dp[j]);
            }
        }


        bw.write(dp[n] + "");
        bw.flush();
        bw.close();
    }
}