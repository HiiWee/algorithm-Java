package boj;

import java.io.*;

class Boj2670 {

    static int n;
    static double[] arr;
    static double[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new double[n];
        dp = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }

        dp[0] = arr[0];
        double max = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i], dp[i - 1] * arr[i]);
            max = Math.max(max, dp[i]);
        }

        bw.write(String.format("%.3f", max));
        bw.flush();
        bw.close();
    }
}