package boj;

import java.io.*;
import java.util.*;

class Boj16208 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += (long) arr[i] * (sum - arr[i]);
            sum -= arr[i];
        }

        bw.write(Long.toString(answer));
        bw.flush();
        bw.close();
    }
}
