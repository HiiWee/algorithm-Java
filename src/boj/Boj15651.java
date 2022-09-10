package boj;

/*
    중복 순열
*/

import java.io.*;
import java.util.*;

class Boj15651 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        permutation(new int[m], n, m, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void permutation(int[] out, int n, int r, int depth) {
        if (depth == r) {
            append(out);
            return;
        }

        for (int i = 0; i < n; i++) {
            out[depth] = i + 1;
            permutation(out, n, r, depth + 1);
        }
    }

    public static void append(int[] out) {
        for (int i = 0; i < out.length; i++) {
            sb.append(out[i]).append(" ");
        }
        sb.append("\n");
    }
}