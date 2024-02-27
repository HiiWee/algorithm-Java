package boj;

import java.io.*;
import java.util.*;

class Boj18311 {

    static int n;
    static long k;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        long sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        int number = 0;
        if (sum < k) {
            k -= sum;
            long dist = 0;
            for (int i = n - 1; i > -1; i--) {
                if (dist <= k && arr[i] + dist > k) {
                    number = i + 1;
                    break;
                }
                dist += arr[i];
            }
        } else {
            long dist = 0;
            for (int i = 0; i < n; i++) {
                if (dist <= k && arr[i] + dist > k) {
                    number = i + 1;
                    break;
                }
                dist += arr[i];
            }
        }

        bw.write(Integer.toString(number));
        bw.flush();
        bw.close();
    }
}