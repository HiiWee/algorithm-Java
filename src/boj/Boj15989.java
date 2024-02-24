package boj;

// 1치원 배열
import java.io.*;

class Boj15989_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int[] dp = new int[10001];
        for (int i = 0; i <= 10000; i++) {
            dp[i] = 1;
        }
        for (int i = 2; i <= 10000; i++) {
            dp[i] += dp[i - 2];
        }
        for (int i = 3; i <= 10000; i++) {
            dp[i] += dp[i - 3];
        }

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int number = Integer.parseInt(br.readLine());
            answer.append(dp[number]).append("\n");
        }


        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}

// 2차원 배열
class Boj15989_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int[][] dp = new int[10001][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= 10000; i++) {
            dp[i][1] = 1;
            dp[i][2] = dp[i - 2][2] + dp[i - 2][1];
            dp[i][3] = dp[i - 3][3] + dp[i - 3][2] + dp[i - 3][1];
        }

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int number = Integer.parseInt(br.readLine());
            answer.append(dp[number][1] + dp[number][2] + dp[number][3]).append("\n");
        }


        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}