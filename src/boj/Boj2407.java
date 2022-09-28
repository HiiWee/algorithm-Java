package boj;
/*
* 조합의 성질을 이용하자
* 1C1 = 1;
* 2C1 = 1C0 + 1C1
* -> 좌측 다리 2개에서 우측 다리 1개를 연결할때(교차되지않고), 좌측에서 상단의 1개를 연결하면 남은 우측다리는 0개이므로 1C0
* -> 좌측에서 상단의 다리를 연결하지 않고 아래 다리를 연결하는 경우 1C1
* 이런점을 반복하다 보면 n과 m값이 증가해도 순차적으로 구할 수 있다.
* */
import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        BigInteger[][] dp = new BigInteger[101][101];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = new BigInteger("1");
            for (int j = 1; j <= i; j++) {
                if (i == j) {
                    dp[i][j] = new BigInteger("1");
                } else {
                    dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);
                }
            }
        }
        bw.write(dp[n][m] + "\n");
        bw.flush();
        bw.close();
    }
}
