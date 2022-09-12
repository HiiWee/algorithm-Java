package boj;

/*
    중복 순열
*/

import java.io.*;
import java.util.*;

class Boj15656 {
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        permutation(new int[m], 0, n, m);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void permutation(int[] out, int depth, int n, int r) {
        if (depth == r) {
            append(out);
            return;
        }

        for (int i = 0; i < n; i++) {
            out[depth] = arr[i];
            permutation(out, depth + 1, n, r);
        }
    }

    public static void append(int[] out) {
        for (int i = 0; i < out.length; i++) {
            sb.append(out[i]).append(" ");
        }
        sb.append("\n");
    }
}