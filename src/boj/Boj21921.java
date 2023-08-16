package boj;

import java.io.*;
import java.util.*;

class Boj21921 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        long[] sum = new long[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + arr[i];
        }
        int left = 1;
        int right = x;
        long max = 0;
        int count = 0;
        while (right <= n) {
            long currentSum = sum[right] - sum[left - 1];
            if (currentSum > max) {
                count = 1;
                max = currentSum;
            } else if (currentSum == max) {
                count++;
            }
            left++;
            right++;
        }

        if (max == 0) {
            bw.write("SAD");
        } else {
            bw.write(max + "\n" + count);
        }
        bw.flush();
        bw.close();
    }
}