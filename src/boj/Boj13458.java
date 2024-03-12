package boj;

import java.io.*;
import java.util.*;

class Boj13458 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int b;
        int c;
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long count = n;
        for (int i = 0; i < n; i++) {
            if (arr[i] > b) {
                if ((arr[i] - b) % c != 0) {
                    count += (arr[i] - b) / c + 1;
                } else {
                    count += (arr[i] - b) / c;
                }
            }
        }

        bw.write(Long.toString(count));
        bw.flush();
        bw.close();
    }
}