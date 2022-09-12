package boj;

/*
    N개의 수가 주어질때(같은수 주어질 수 있음)
    M개의 수를 뽑아서 순열을 구성하는데 중복되는 순열은 제외하고 모든 순열을 구함
*/

import java.io.*;
import java.util.*;

class Boj15664 {
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

        permutation(new boolean[n], new int[m], n, m, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void permutation(boolean[] visited, int[] out, int n, int r, int depth) {
        if (depth == r) {
            append(out);
            return;
        }

        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && prev != arr[i]) {
                prev = arr[i];
                visited[i] = true;
                out[depth] = arr[i];
                permutation(visited, out, n, r, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void append(int[] out) {
        for (int i = 0; i < out.length; i++) {
            sb.append(out[i]).append(" ");
        }
        sb.append("\n");
    }
}