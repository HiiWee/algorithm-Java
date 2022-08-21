package boj;

/*
    먼저 제일 바깥쪽 for문에서 상담 시작날을 순차적으로 선택함
        그 다음 for문에서는 시작하는날이 끝나는 날부터 선택할 수 있는 남은 상담을 하나씩 선택함
            그 다음 for문에서는 직전 for문이 끝나는날부터 선택할 수 있는 상담날을 하나씩 선택한다.
    상담일이 3중 for문중 어디서라도 확정이되면 즉각 최대 수익을 갱신해야 하므로 모든 for문에서 검사한다.

    단, 필요 상담일Ti의 값을 더했을때 N값을 넘어서면 고르지 않아야 한다.
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