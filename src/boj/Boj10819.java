package boj;

import java.io.*;
import java.util.*;

class Boj10819 {
    static int maxValue = Integer.MIN_VALUE;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        permutation(new int[N], 0, N, N);

        bw.write(maxValue + "\n");
        bw.flush();
        bw.close();
    }

    public static void permutation(int[] out, int depth, int n, int r) {
        if (depth == r) {
            calcValue(out);
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                permutation(out, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    public static void calcValue(int[] out) {
        int total = 0;
        for (int i = 0; i < out.length - 1; i++) {
            total += Math.abs(out[i] - out[i + 1]);
        }
        maxValue = Math.max(total, maxValue);
    }
}