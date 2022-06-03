package boj;

import java.io.*;
import java.util.*;

class Boj2805 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 나무의 수
        int n = Integer.parseInt(st.nextToken());
        // 필요 나무 길이
        int m = Integer.parseInt(st.nextToken());
        long[] trees = new long[n];
        st = new StringTokenizer(br.readLine());
        long max = 0;
        for (int i = 0; i < n; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            max = Math.max(trees[i], max);
        }

        long min = 0;
        long mid;
        while (min < max) {
            mid = (min + max) / 2;
            long length = 0;
            for (int i = 0; i < n; i++) {
                if (trees[i] - mid >= 0) {
                    length += trees[i] - mid;
                }
            }

            if (length < m) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
}