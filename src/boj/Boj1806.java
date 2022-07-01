package boj;

import java.util.*;
import java.io.*;

class Main {
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int end = 0;
        int count = 0;
        int total = 0;
        while (end <= N) {
            if (total < S) {
                total += arr[end++];
                count++;
            } else if (total >= S) {
                total -= arr[start++];
                count--;
            }
            if (total >= S) {
                minCount = Math.min(minCount, count);
            }
        }
        if (minCount == Integer.MAX_VALUE) {
            minCount = 0;
        }
        bw.write(minCount + "\n");
        bw.flush();
        bw.close();
    }
}
