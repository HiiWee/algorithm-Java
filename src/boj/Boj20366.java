package boj;

import java.io.*;
import java.util.*;

class Boj20366 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int l = 0;
                int r = n - 1;
                while (l < r) {
                    if (l == i || l == j) {
                        l++;
                    }
                    if (r == i || r == j) {
                        r--;
                    }
                    int distance = (arr[i] + arr[j]) - (arr[l] + arr[r]);
                    min = Math.min(min, Math.abs(distance));

                    if (distance > 0) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }

        bw.write(min + "");
        bw.flush();
        bw.close();
    }
}