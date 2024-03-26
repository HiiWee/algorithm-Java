package boj;

import java.io.*;
import java.util.*;

class Boj17521 {

    static int n;
    static long w;
    static long coinCount;
    static int[] quotes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Long.parseLong(st.nextToken());
        quotes = new int[n];

        for (int i = 0; i < n; i++) {
            quotes[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n - 1; i++) {
            // 상향 그래프면 그때마다 최대한으로 구매하면 됨
            if (quotes[i] <= quotes[i + 1]) {
                long buyingCount = w / quotes[i];
                coinCount += buyingCount;
                w -= quotes[i] * buyingCount;
            } else {
                w += coinCount * quotes[i];
                coinCount = 0;
            }
        }
        w += coinCount * quotes[n - 1];

        bw.write(Long.toString(w));
        bw.flush();
        bw.close();
    }
}