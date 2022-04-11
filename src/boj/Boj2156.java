package boj;
/*
* 술잔이 1개 : 현재 자신의 값이 최대 값
* 술잔이 2개 : 2개까지 연속으로 마실 수 있으니 1번쨰와 2번째를 더한 값
* 술잔이 3개 : 경우의 따라 나뉨
*   1, 3번째를 마시는 경우 : 3번째 잔과 1번에서의 최대 합을 더 함
*   2, 3번째를 마시는 경우 : 3번째 잔과 2번째 잔의 합과 i-3번째의 최대값을 더함(dp[2]를 하면 1, 2, 3이렇게 3잩 연속 마실 수 있으므로
*   잔만 더한다)
*   1, 2번째를 마시는 경우 : 2번째 잔까지의 최대합 (dp[2]) 1, 2를 마셔도 3번째인 자신은 마시지 않으므로 괜찮음
*
* 위와같이 점화식이 이루어지면 3이상이더라도 적용이 가능하다.
*/
/*
* [이해를 도와준 블로그 댓글]
* 예로들어 1개의 경우 그 한 개 자체가 최대 합이 될테죠.
* 2개일 경우는 연속으로 두 개까지 선택이 가능하니 어떤 경우이건 dp[2]는 첫 번째 잔arr[1]과 두 번째 잔arr[2]의 합이 될 겁니다.
* 3개일 경우 dp[2]인 1, 2 번째 잔을 선택한 양의 합이 최대일 수도, 1, 3 번째 잔을 선택할 수도 있습니다. 그리고 2, 3번 째 잔을 선택할 수도 있죠.
* 이 때 여기서 2, 3번째 잔을 선택할 경우에는 dp[2]를 호출하면
* dp[2]는 1, 2번째 잔을 합한 값을 갖고 있기 때문에 2번째 잔만 선택하기 위해서는 arr[N-1]이 되어야 할 겁니다.
* 그렇게 해서 선택된 경우의 수 중 최대 합이 dp[3]에 저장되겠죠
*
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Top-Down
class Boj2156_1 {
    static int N;
    static int[] drink;
    static Integer[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        drink = new int[N + 1];
        dp = new Integer[N + 1];

        for (int i = 1; i <= N; i++) {
            drink[i] = Integer.parseInt(bf.readLine());
        }

        dp[0] = 0;
        dp[1] = drink[1];
        if (N > 1) {
            dp[2] = drink[1] + drink[2];
        }
        System.out.println(recur(N));
    }

    public static Integer recur(int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(Math.max(recur(n - 1), recur(n - 2) + drink[n]), drink[n] + drink[n - 1] + recur(n - 3));
        }
        return dp[n];
    }
}

// Bottom-Up
class Boj2156_2 {
    static int N;
    static int[] drink;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        drink = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            drink[i] = Integer.parseInt(bf.readLine());
        }
        dp[1] = drink[1];
        if (N > 1) {
            dp[2] = drink[1] + drink[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(Math.max(dp[i - 1], drink[i] + dp[i - 2]), drink[i] + drink[i - 1] + dp[i - 3]);
        }
        System.out.println(dp[N]);
    }
}