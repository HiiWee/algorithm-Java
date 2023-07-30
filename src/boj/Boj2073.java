package boj;

// 2차원 배열을 이용한 DP 풀이 - 덜 효율적
import java.io.*;
import java.util.*;

class Boj2073_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken()); // 파이프로 만들어야 하는 거리
        int p = Integer.parseInt(st.nextToken()); // 주어지는 파이프의 개수

        int[][] dp = new int[p + 1][d + 1];
        int[] l = new int[p + 1]; // 파이프 길이
        int[] c = new int[p + 1]; // 파이프 용량

        for (int i = 1; i <= p; i++) {
            st = new StringTokenizer(br.readLine());
            l[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= d; j++) {
                if (j == l[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], c[i]);
                } else if (j > l[i]) {
                    dp[i][j] = Math.max(Math.min(dp[i - 1][j - l[i]], c[i]), dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        bw.write(dp[p][d] + "");
        bw.flush();
        bw.close();
    }
}

// 1차원 배열을 이용한 DP 풀이 - 공간복잡도 효율적

class Boj2073_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken()); // 파이프로 만들어야 하는 거리
        int p = Integer.parseInt(st.nextToken()); // 주어지는 파이프의 개수

        int[] dp = new int[d + 1];

        dp[0] = Integer.MAX_VALUE;

        for (int i = 1; i <= p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for (int j = d; j >= l; j--) {
                dp[j] = Math.max(dp[j], Math.min(dp[j - l], c));
            }
        }
        bw.write(dp[d] + "");
        bw.flush();
        bw.close();
    }
}
