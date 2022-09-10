package boj;

/*
    중복 조합
*/

import java.io.*;
import java.util.*;

class Boj15652 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        combination(new int[m], n, m, 0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void combination(int[] out, int n, int r, int depth, int start) {
        if (depth == r) {
            append(out);
            return;
        }

        for (int i = start; i < n; i++) {
            out[depth] = i + 1;
            combination(out, n, r, depth + 1, i);
        }
    }

    public static void append(int[] out) {
        for (int num : out) {
            sb.append(num).append(" ");
        }
        sb.append("\n");
    }
}