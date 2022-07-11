package boj;

import java.util.*;
import java.io.*;

class Boj2473 {
    static long[] results = new long[3];
    static long min = Long.MAX_VALUE;
    static long[] arr;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        // 3개의 용액의 합이여야 하므로 고정된 용액은 n - 3까지만 설정해야 3개 용액의 합을 구할 수 있다.
        for (int i = 0; i < n - 2; i++) {
            twoPointersSearch(i);
        }
        Arrays.sort(results);

        bw.write(results[0] + " " + results[1] + " " + results[2] + "\n");
        bw.flush();
        bw.close();
    }

    public static void twoPointersSearch(int i) {
        int l = i + 1;
        int r = n - 1;
        while (l < r) {
            long sum = arr[l] + arr[r] + arr[i];

            if (Math.abs(min) > Math.abs(sum)) {
                min = sum;
                results[0] = arr[l];
                results[1] = arr[r];
                results[2] = arr[i];
            }

            if (sum > 0) {
                r--;
            } else if (sum < 0) {
                l++;
            } else {
                break;
            }
        }
    }
}