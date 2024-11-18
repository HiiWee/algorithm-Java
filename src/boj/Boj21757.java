package boj;

import java.io.*;
import java.util.*;

class Boj21757 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arr[i] += arr[i - 1];
        }

        long[] dp = new long[4];
        dp[0] = 1;

        long answer = 0;
        if (arr[n] == 0) {
            long count = 0;
            for (int i = 1; i <= n; i++) {
                if (arr[i] == 0) {
                    count++;
                }
            }
            answer = (count - 1) * (count - 2) * (count - 3) / 6;
        } else if (arr[n] % 4 == 0) {
            int target = arr[n] / 4;
            for (int i = 1; i <= n; i++) {
                int count = arr[i] / target;
                if (arr[i] % target == 0 && count > 0 && count < 4) {
                    dp[count] += dp[count - 1];
                }
            }
            answer = dp[3];
        }

        bw.write(Long.toString(answer));
        bw.flush();
        bw.close();
    }
}
