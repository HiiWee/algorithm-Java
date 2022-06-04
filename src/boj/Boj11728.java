package boj;

import java.util.*;
import java.io.*;

class Boj11728 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (A[i] < B[j]) {
                bw.write(A[i] + " ");
                i++;
            } else {
                bw.write(B[j] + " ");
                j++;
            }
        }
        if (i < n) {
            while (i < n) {
                bw.write(A[i] + " ");
                i++;
            }
        } else if (j < m) {
            while (j < m) {
                bw.write(B[j] + " ");
                j++;
            }
        }
        bw.flush();
        bw.close();
    }
}