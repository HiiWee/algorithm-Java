package boj;

/**
 * 먼저 정렬하고, 순차적으로 탐색하며 현재 인덱스의 값 * (배열의 수 - 현재 인덱스)를 하며 최대값을 갱신해간다.
 */

import java.io.*;
import java.util.Arrays;

class Boj2217 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] lope = new int[n];
        for (int i = 0; i < n; i++) {
            lope[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lope);
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, lope[i] * (n - i));
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();

    }
}