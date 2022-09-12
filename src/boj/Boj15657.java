package boj;

/*
    중복 조합
*/
import java.io.*;
import java.util.*;

class Boj15657 {
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

        combination(new int[m], n, m, 0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void combination(int[] out, int n, int r, int start, int depth) {
        if (depth == r) {
            append(out);
            return;
        }

        for (int i = start; i < n; i++) {
            out[depth] = arr[i];
            combination(out, n, r, i, depth + 1);
        }
    }

    public static void append(int[] out) {
        for (int i = 0; i < out.length; i++) {
            sb.append(out[i]).append(" ");
        }
        sb.append("\n");
    }
}