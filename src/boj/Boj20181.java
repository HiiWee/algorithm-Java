package boj;

import java.io.*;
import java.util.*;

class Boj20181 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] numbers = new int[n];
        long[] dp = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // left이상 right 미만의 범위 -> left의 간섭을 받지 않기 위해 right 미만
        int left = 0;
        int right = 1;
        long sum = numbers[0];
        while (right <= n) {
            if (sum >= k) {
                while (sum >= k) {
                    dp[right] = Math.max(dp[right], dp[left] + sum - k);
                    sum -= numbers[left++];
                }

            } else {
                dp[right] = Math.max(dp[right], dp[right - 1]);
                if (right == n) {
                    break;
                }
                sum += numbers[right++];
            }
        }

        bw.write(dp[n] + "");
        bw.flush();
        bw.close();
    }
}