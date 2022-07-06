package boj;

import java.util.*;
import java.io.*;

class Boj2512 {
    static int N;
    static int[] arr;
    static int totalMoney;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        long total = 0;
        int max = Integer.MIN_VALUE;
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            max = Math.max(max, num);
            total += num;
            arr[i] = num;
        }
        totalMoney = Integer.parseInt(br.readLine());
        if (total <= totalMoney) {
            bw.write(max + "\n");
        } else {
            bw.write((upperBound() - 1) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int upperBound() {
        int low = 1;
        int high = totalMoney + 1;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            long total = 0;
            for (int i = 0; i < N; i++) {
                if (mid > arr[i]) {
                    total += arr[i];
                } else {
                    total += mid;
                }
            }

            if (total > totalMoney) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
