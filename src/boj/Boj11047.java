package boj;

import java.util.*;
import java.io.*;

class Boj11047 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        int coinCount = 0;

        // 동전은 오름차순으로 주어진다.
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int i = n - 1; i >= 0; i--) {
            if (k / coins[i] > 0) {
                coinCount += k / coins[i];
                k %= coins[i];
            }
        }
        bw.write(coinCount + "\n");
        bw.flush();
        bw.close();
    }
}