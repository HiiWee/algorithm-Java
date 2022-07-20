package boj;

import java.util.*;
import java.io.*;

class Boj2798 {
    static int[] arr;
    static int min = Integer.MAX_VALUE;
    static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        combination(new boolean[n], 0, n, 3);

        bw.write(min + "\n");
        bw.flush();
        bw.close();

    }

    public static void combination(boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            append(visited);
            return;
        }

        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combination(visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    public static void append(boolean[] visited) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                total += arr[i];
            }
        }
        if (Math.abs(total - m) < Math.abs(min - m)) {
            min = total;
        }
    }
}