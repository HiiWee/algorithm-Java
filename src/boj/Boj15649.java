package boj;

/*
    순열을 구한다.
*/

import java.io.*;
import java.util.*;

class Boj15649 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        permutation(new boolean[n + 1], new int[m + 1], n, m, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void permutation(boolean[] visited, int[] out, int n, int r, int depth) {
        if (depth == r) {
            append(out);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth + 1] = i;
                permutation(visited, out, n, r, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void append(int[] out) {
        for (int i = 1; i < out.length; i++) {
            sb.append(out[i]).append(" ");
        }
        sb.append("\n");
    }
}