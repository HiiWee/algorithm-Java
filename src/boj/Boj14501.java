package boj;

/*
    DFS 풀이
    먼저 초기 startDay와 sum은 1과 0으로 시작한다.

    1일 ~ N일까지 검사를 하는데 만약 startDay가 n + 1보다 작거나 같다면 최대값을 갱신해준다.

    여기서 n + 1일 까지 검사하는 이유는 마지막날인 N번째날에서 1일의 상담이 존재하면 해당 비용도 포함할 수 있게되고, 해당 비용이 더해지는 날은 N + 1번째 날이기 때문이다.

    만약 시작하는 날이 n + 1의 값을 넘어서게 되면 종료한다.
    결국 재귀적으로 1 ~ n을 시작으로 두고 탐색할 수 있는 모든 경우를 확인하게 되므로 최대 비용을 도출할 수 있다.
    */
import java.util.*;
import java.io.*;

class Boj14501_1 {
    static int n;
    static int[][] arr;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        selectDay(1, 0);

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void selectDay(int startDay, int sum) {
        if (startDay > n + 1) {
            return;
        }
        max = Math.max(max, sum);

        for (int i = startDay; i <= n; i++) {
            selectDay(i + arr[i][0], sum + arr[i][1]);
        }
    }
}


/*
    DP로 풀어보자
*/

class Boj14501_2 {
    static int n;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 6];
        int[] t = new int[n + 6];
        int[] p = new int[n + 6];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // 끝 날짜 + 1 까지 검사해야 마지막 날짜에서 1일을 상담한 경우를 포함하여 게산할 수 있다.
        for (int i = 1; i <= n + 1; i++) {
            // 지금까지의 최대값 현재 dp에 저장해야함
            dp[i] = Math.max(max, dp[i]);
            // dp 갱신, 이전에 계산되어 미리 저장된 값 vs 현재 새로 갱신되는 값
            dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            // 최대값 다시 갱신
            max = Math.max(dp[i], max);
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }
}