package boj;

import java.io.*;
import java.util.*;

class Boj13422 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int count = 0;
            int l = 0;
            int r = m - 1;
            long sum = 0;
            for (int i = 0; i <= r; i++) {
                sum += arr[i];
            }
            for (int i = 0; i < n; i++) {
                if (sum < k) {
                    count++;
                }
                if (n == m) {
                    break;
                }
                sum -= arr[l++];
                sum += arr[(r + 1) % n];
                r++;
            }
            answer.append(count).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
