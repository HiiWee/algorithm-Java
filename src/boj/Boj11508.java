package boj;

import java.io.*;
import java.util.*;

// Greedy
class Boj11508 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Long[] arr = new Long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr, Collections.reverseOrder());

        long total = 0;
        for (int i = 0; i < n; i++) {
            if ((i + 1) % 3 == 0) {
                continue;
            }
            total += arr[i];
        }

        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}